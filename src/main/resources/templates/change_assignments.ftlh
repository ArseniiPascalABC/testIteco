<!DOCTYPE html>
<html lang="en">
<head>
    <title>Change Assignments</title>
</head>
<body>
<h1>Change Assignment for ${employee.lastName} ${employee.firstName}</h1>
<form action="/assignments/change/${employee.id}" method="post">
    <label for="departmentId">Department:</label>
    <select id="departmentId" name="departmentId">
        <option value="none">None</option>
        <#list departments as department>
            <option value="${department.id}">${department.name}</option>
        </#list>
    </select><br>

    <button type="submit">Save</button>
</form>

<a href="/assignments">Back to Assignments</a>
</body>
</html>