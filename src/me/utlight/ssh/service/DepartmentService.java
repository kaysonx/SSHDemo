package me.utlight.ssh.service;

import java.util.List;

import me.utlight.ssh.dao.DepartmentDao;
import me.utlight.ssh.entities.Department;

public class DepartmentService {
	
	private DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> getAllDepartment(){
		return departmentDao.getAllDepartment();
	}
}
