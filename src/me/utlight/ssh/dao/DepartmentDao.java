package me.utlight.ssh.dao;

import java.util.List;

import me.utlight.ssh.entities.Department;

public class DepartmentDao extends BaseDao{
	
	public List<Department> getAllDepartment(){
		String hql = "FROM Department";
		return getSession().createQuery(hql).list();
	}
	
}
