<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employee Assignments</title>
</head>
<body>
<h1>Employee Assignments</h1>
<#if departments??>
    <form action="/assignments/byPeriod" method="get">
        <label for="departmentId">Department:</label>
        <select id="departmentId" name="departmentId" required>
            <#list departments as department>
                <option value="${department.id}">${department.name}</option>
            </#list>
        </select><br>
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br>
        <button type="submit">Search</button>
    </form>
<#else>
    <p>No departments available</p>
</#if>
<table>
    <tr>
        <th>ID</th>
        <th>Employee</th>
        <th>Department</th>
        <th>Start Date</th>
        <th>End Date</th>
    </tr>
    <#list assignments as assignment>
        <tr>
            <td>${assignment.id}</td>
            <td>${assignment.employee.lastName} ${assignment.employee.firstName}</td>
            <td>${assignment.department.name}</td>
            <td>${assignment.employmentDate}</td>
            <td>${assignment.dismissalDate!}</td>
        </tr>
    </#list>
</table>
</body>
</html>
