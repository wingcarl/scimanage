package com.wang.sci.common.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.github.springtestdbunit.DbUnitTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
@TestExecutionListeners({DbUnitTestExecutionListener.class, 
	DependencyInjectionTestExecutionListener.class})
public class TestUserDao extends AbstractDbUnitTestCase{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() throws DataSetException, SQLException, IOException{
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		this.backupAllTable();
	}
	
	/**测试01
	 * BaseDao get()
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testGet() throws DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		
		User u = userDao.get(1);
		EntitiesHelper.assertUser(u);
	}
	
	/**
	 * BaseDao getByHql(String hql)
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testGetByHql1() throws DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		
		User u = userDao.getByHql("from User u where u.id=1");
		EntitiesHelper.assertUser(u);
	}
	
	/**
	 * BaseDao getByHql(String hql,Parameter parameter)测试通过
	 * Parameter(String arg0) 测试通过
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testGetByHql2() throws DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		
		User u = userDao.getByHql("from User u where u.id=:p1",new Parameter("1"));
		EntitiesHelper.assertUser(u);
	}
	
	/**
	 * BaseDao update(String arg0,Parameter p)测试通过
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testUpdate() throws DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		
		int i = userDao.update("update User set name=:p1 where id=:p2",new Parameter("adm",1));
		User u = userDao.get(i);
		User actual = new User(1,"adm");
		EntitiesHelper.assertUser(u,actual);
	}
	/**
	 * BaseDao update(String arg0) 测试通过
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testUpdate01() throws DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		
		int i = userDao.update("update User set name='adm' where id=1");
		User u = userDao.get(i);
		User actual = new User(1,"adm");
		EntitiesHelper.assertUser(u,actual);
	}
	
	@Test
	public void testDeleteById() throws DatabaseUnitException, SQLException{
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		
		int i = userDao.update("update User set name='adm' where id=1");
		User u = userDao.get(i);
		User actual = new User(1,"adm");
		EntitiesHelper.assertUser(u,actual);
	}
	
	@After
	public void tearDown() throws FileNotFoundException, DatabaseUnitException, SQLException{
		this.resumeTable();SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		this.resumeTable();
	}
}
