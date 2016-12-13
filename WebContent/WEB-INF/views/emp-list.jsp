<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-3.0.0.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var lastName = $(this).next(":hidden").val();
			var flag = confirm("确定要删除" + lastName + "的信息吗？");
			if(flag){
				var $tr = $(this).parent().parent();
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					if(data == "1"){
						alert("删除成功！");
						$tr.remove();
					}else{
						alert("删除失败!");
					}
					
				});
			}
			//取消点击事件的默认响应
			return false;
		});
	});
</script>
</head>
<body>
	<s:if
		test="#request.employees == null || #request.employees.size() == 0">
		<h4>暂无员工信息！</h4>
	</s:if>
	<s:else>
		<table border="1" cellpadding="10" cellspacing="0">
			<thead>
				<tr>
					<th>Id</th>
					<th>Last_Name</th>
					<th>Email</th>
					<th>Birth</th>
					<th>CreateTime</th>
					<th>DepartmentName</th>
					<th>Delete</th>
					<th>Edit</th>
				</tr>
			</thead>

			<tbody>
				<s:iterator value="#request.employees">
					<tr>
						<td>${id }</td>
						<td>${lastName }</td>
						<td>${email }</td>
						<td>
							<s:date name="birth" format="yyyy-MM-dd"/>
						</td>
						<td>
							<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/>
						</td>
						<td>${department.departmentName }</td>
						<td>
							<a class="delete"  href="emp-delete?id=${id}">Delete</a>
							<input type="hidden" value="${lastName }"/>
						</td>
						<td>
							<a href="emp-input?id=${id }">Edit</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:else>
</body>
</html>