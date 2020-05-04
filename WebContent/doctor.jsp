<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Doctor"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
<meta charset="ISO-8859-1">


</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Doctor Management</h1>
				<!--  			<form id="formItem" name="formItem">
					Item code: 
					<input id="itemCode" name="itemCode" type="text" class="form-control form-control-sm">
					
					 <br> Item name:
					<input id="itemName" name="itemName" type="text" class="form-control form-control-sm">
					
					 <br> Item price:
					  <input id="itemPrice" name="itemPrice" type="text" class="form-control form-control-sm"> <br> 
					  
					  Item description:
					   <input id="itemDesc" name="itemDesc" type="text" class="form-control form-control-sm">
						 <br> 
						 
						 <input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> <input type="hidden"id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				-->

				<form id="formDoctor" name="formDoctor">			
					Doctor Id :
					<input id="did" name="did" type="text" class="form-control form-control-sm"> 
					
					<br> Doctor Name :
					<input id="dname" name="dname" type="text" class="form-control form-control-sm">
					
					<br> Doctor Address : 
					<input id="daddress" name="daddress" type="text" class="form-control form-control-sm"> 
					
					<br> Doctor Specialty : 
					<input id="dspecialty" name="dspecialty" type="text" class="form-control form-control-sm"> 
					
					<br> Doctor Mobile : 
					<input id="dmobile" name="dmobile" type="text" class="form-control form-control-sm"> 
					
					<br> <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
					
				</form>


				<div id="alertSuccess" class="alert alert-success"></div>

				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divItemsGrid">
					<%
						Doctor itemobj = new Doctor();
						out.print(itemobj.viewDoctors());
					%>
				</div>

			</div>
		</div>
	</div>


</body>
</html>