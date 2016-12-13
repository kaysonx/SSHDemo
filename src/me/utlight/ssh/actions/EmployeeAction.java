package me.utlight.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import me.utlight.ssh.entities.Employee;
import me.utlight.ssh.service.DepartmentService;
import me.utlight.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> request;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
		
	}
	
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String list(){
		request.put("employees", employeeService.getAll());
		return "list";
	}
	
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String delete(){
		try {
			employeeService.delete(id);
			try {
				inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		
		return "ajax-success";
	}
	public void prepareInput(){
		if(id != null){
			model = employeeService.getEmployeeById(id);
		}
	}
	
	public String input(){
		this.request.put("departments", departmentService.getAllDepartment());
		return INPUT;
	}

	@Override
	public void prepare() throws Exception {
		//默认在所有方法执行前执行
	}
	
	private Employee model;
	
	@Override
	public Employee getModel() {
		return model;
	}
	
	public void prepareSave(){
		if(id == null){
			this.model = new Employee();
		}else{
			model = employeeService.getEmployeeById(id);
		}
	}

	public String save(){
		
		if(id == null){
			this.model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		
		return SUCCESS;
	}
	
	private String lastName;
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String validateLastName() throws UnsupportedEncodingException{
		if(employeeService.isLasnameValidate(lastName)){
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		
		return "ajax-success";
	}
}
