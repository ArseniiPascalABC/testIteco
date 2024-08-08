<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employees</title>
</head>
<body>
<h1>Employees</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Last Name</th>
        <th>First Name</th>
        <th>Middle Name</th>
    </tr>
    <#list employees as employee>
        <tr>
            <td>${employee.id}</td>
            <td>${employee.lastName}</td>
            <td>${employee.firstName}</td>
            <td>${employee.middleName}</td>
        </tr>
    </#list>
</table>
<a href="/employees/new">Add New Employee</a>
</body>
</html>