<!DOCTYPE html>
<html lang="en">
<head>
    <title>Departments</title>
</head>
<body>
<h1>Departments</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Parent Department</th>
    </tr>
    <#list departments as department>
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
            <td>${department.dateOfCreation}</td>
            <td>${department.dateOfClosing!}</td>
            <td>
                <#if department.parentDepartment??>
                    ${department.parentDepartment.name}
                <#else>
                    None
                </#if>
            </td>
        </tr>
    </#list>
</table>
<a href="/departments/new">Add New Department</a>
</body>
</html>
