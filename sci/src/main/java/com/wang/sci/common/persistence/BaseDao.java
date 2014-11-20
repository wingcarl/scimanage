package com.wang.sci.common.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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


