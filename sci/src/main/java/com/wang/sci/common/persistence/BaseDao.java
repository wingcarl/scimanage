package com.wang.sci.common.persistence;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wang.sci.common.utils.Reflections;

public class BaseDao<T> {
	
	private SessionFactory sessionFactory;
	private Class<?> entityClass;
	
	public BaseDao(){
		entityClass = Reflections.getClassGenricType(getClass());
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void flush(){
		getSession().flush();
	}
	
	public void clear(){
		getSession().clear();
	}
	
	public <E> List<E> find(String qlString){
		return find(qlString,null);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return  getSession().createCriteria(entityClass).list();
	}
	@SuppressWarnings("unchecked")
	public <E> List<E> find(String qlString, Parameter parameter){
		Query query = createQuery(qlString,parameter);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id){
		return (T)getSession().get(entityClass, id);
	}
	
	public T getByHql(String qlString){
		return this.getByHql(qlString, null);
	}
	
	@SuppressWarnings("unchecked")
	public T getByHql(String qlString,Parameter parameter){
		Query query = createQuery(qlString,parameter);
		return (T)query.uniqueResult();
	}
	/**
	 * 反射的机制的应用先提取所有的方法
	 * 并依次判断该方法是否属于某个注解下，若属于@ID注解下，则通过method.invoke获取该ID
	 * 判断id是否赋值，若未赋值则执行Prepersist给ID赋值，若赋值则执行PreUpdate，更新ID值
	 * @param entity
	 */
	public void save(T entity){
		try {
		Object id = null;
		for(Method method : entity.getClass().getMethods()){
			Id idAnn = method.getAnnotation(Id.class);
			if(idAnn != null){
					id = method.invoke(entity);
					break;
			}
		}
		//插入前执行方法
		if(StringUtils.isBlank((String)id)){
			for(Method method : entity.getClass().getMethods()){
				PrePersist pp = method.getAnnotation(PrePersist.class);
				if(pp != null){
					method.invoke(entity);
					break;
				}
			}
		}
		//更新前执行方法
		else{
			for (Method method : entity.getClass().getMethods()){
				PreUpdate pu = method.getAnnotation(PreUpdate.class);
				if(pu != null){
					method.invoke(entity);
					break;
				}
			}
		}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			getSession().saveOrUpdate(entity);
		}
	
	/**
	 * 保存实体列表，传入多个实体
	 * @param entityList
	 */
	public void save(List<T> entityList){
		for(T entity : entityList){
			save(entity);
		}
	}
	
	public int update(String qlString,Parameter parameter){
		return createQuery(qlString,parameter).executeUpdate();
	}
	
	public int update(String qlString){
		return this.update(qlString,null);
	}
	
	public int deleteById(Serializable id){
		return update("update "+entityClass.getSimpleName()+" set delFlag='" + BaseEntity.DEL_FLAG_DELETE +"' where id = :p1",
				new Parameter(id));
	}
	
	public int updateDelFlag(Serializable id, String delFlag){
		return update("update "+entityClass.getSimpleName()+" set delFlag=:p2 where id=:p1",
				new Parameter(id,delFlag));
	}
	public Query createQuery(String qlString, Parameter parameter){
		Query query = getSession().createQuery(qlString);
		setParameter(query, parameter);
		return query;
	}
	
	/**
	 * 设置查询参数
	 * @param query
	 * @param parameter
	 */
	private void setParameter(Query query, Parameter parameter){
		if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(value instanceof Collection<?>){
                    query.setParameterList(string, (Collection<?>)value); 
                    //用于sql in..的语句下，传入一个集合
                    //另一种方法setParameters() 是用于顺序组装
                }else if(value instanceof Object[]){
                    query.setParameterList(string, (Object[])value);
                }else{
                    query.setParameter(string, value);
                }
            }
        }
	}
}


