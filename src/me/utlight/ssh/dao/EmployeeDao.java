package me.utlight.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import me.utlight.ssh.entities.Employee;

public class EmployeeDao extends BaseDao{
	
	public List<Employee> getAllEmployees(){
		//解决view页面出现懒加载异常
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department ORDER BY e.id";
		return getSession().createQuery(hql).list();
	}
	
	public void delete(Integer id){
		String hql = "DELETE FROM Employee e WHERE e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	public void saveOrUpdate(Employee employee){
		 getSession().saveOrUpdate(employee);
	}
	
	public Employee getEntityById(Integer id){
		String hql = "FROM Employee e WHERE e.id = ?";
		return (Employee) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
	
	public Employee getEntityByName(String name){
		String hql = "FROM Employee e WHERE e.lastName = ?";
		return (Employee) getSession().createQuery(hql).setString(0, name).uniqueResult();
	}
}
