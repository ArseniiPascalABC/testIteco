<!DOCTYPE html>
<html lang="en">
<head>
    <title>New Department</title>
</head>
<body>
<h1>New Department</h1>
<form action="/departments/save" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="parentDepartment">Parent Department:</label>
    <select id="parentDepartment" name="parentDepartmentId">
        <option value="">None</option>
        <#list departments as department>
            <option value="${department.id}">${department.name}</option>
        </#list>
    </select><br>

    <button type="submit">Save</button>
</form>
<a href="/departments">Back to Departments</a>
</body>
</html>
