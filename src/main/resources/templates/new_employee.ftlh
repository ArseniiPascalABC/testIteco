<!DOCTYPE html>
<html lang="en">
<head>
    <title>New Employee</title>
</head>
<body>
<h1>New Employee</h1>
<form action="/employees/save" method="post">
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br>

    <label for="middleName">Middle Name:</label>
    <input type="text" id="middleName" name="middleName"><br>

    <label for="department">Department:</label>
    <select id="department" name="departmentId" required>
        <#list departments as department>
            <option value="${department.id}">${department.name}</option>
        </#list>
    </select><br>

    <button type="submit">Save</button>
</form>
<a href="/employees">Back to Employees</a>
</body>
</html>
