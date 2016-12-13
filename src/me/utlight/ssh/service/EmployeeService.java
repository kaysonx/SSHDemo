package me.utlight.ssh.service;

import java.util.List;

import me.utlight.ssh.dao.EmployeeDao;
import me.utlight.ssh.entities.Employee;

public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public List<Employee> getAll(){
		return employeeDao.getAllEmployees();
	}
	
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	public void saveOrUpdate(Employee employee){
		employeeDao.saveOrUpdate(employee);
	}
	
	public Employee getEmployeeById(Integer id){
		return employeeDao.getEntityById(id);
	}
	
	public boolean isLasnameValidate(String name){
		return employeeDao.getEntityByName(name) == null;
	}
}
