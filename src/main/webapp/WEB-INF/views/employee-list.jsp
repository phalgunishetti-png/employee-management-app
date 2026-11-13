<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management System</title>
    <style>
        :root {
            --primary: #2563eb;
            --primary-dark: #1d4ed8;
            --danger: #dc2626;
            --danger-dark: #b91c1c;
            --bg-color: #f8fafc;
            --card-bg: #ffffff;
            --text-main: #1e293b;
            --text-muted: #64748b;
            --border-color: #cbd5e1;
        }

        body {
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-main);
            margin: 0;
            padding: 40px 20px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            background: var(--card-bg);
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05), 0 8px 10px -6px rgba(0, 0, 0, 0.05);
        }

        .header-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #e2e8f0;
            padding-bottom: 15px;
            margin-bottom: 25px;
        }

        h2 {
            margin: 0;
            color: var(--text-main);
            font-size: 24px;
            font-weight: 600;
        }

        .form-card {
            background: #f1f5f9;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 15px;
            margin-bottom: 20px;
        }

        input, select {
            width: 100%;
            padding: 11px 14px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            font-size: 14px;
            background-color: #fff;
            box-sizing: border-box;
            transition: border-color 0.2s, box-shadow 0.2s;
        }

        input:focus, select:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }

        .btn-primary {
            background-color: var(--primary);
            color: white;
            padding: 10px 22px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 14px;
            font-weight: 600;
            transition: background-color 0.2s;
        }

        .btn-primary:hover {
            background-color: var(--primary-dark);
        }

        .btn-cancel {
            color: var(--text-muted);
            text-decoration: none;
            margin-left: 15px;
            font-size: 14px;
            font-weight: 500;
        }

        .btn-cancel:hover {
            color: var(--text-main);
            text-decoration: underline;
        }

        .table-responsive {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            border: 1px solid #e2e8f0;
        }

        th, td {
            padding: 14px 16px;
            text-align: left;
            font-size: 14px;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background-color: #f8fafc;
            color: var(--text-muted);
            font-weight: 600;
            text-transform: uppercase;
            font-size: 12px;
            letter-spacing: 0.05em;
        }

        tr:last-child td {
            border-bottom: none;
        }

        tr:hover {
            background-color: #f8fafc;
        }

        .action-links a {
            text-decoration: none;
            font-weight: 600;
            font-size: 13px;
            margin-right: 12px;
        }

        .action-edit {
            color: var(--primary);
        }

        .action-edit:hover {
            color: var(--primary-dark);
            text-decoration: underline;
        }

        .action-delete {
            color: var(--danger);
        }

        .action-delete:hover {
            color: var(--danger-dark);
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header-section">
            <h2>Employee Management System</h2>
        </div>

        <!-- Add / Edit Employee Form Card -->
        <div class="form-card">
            <form action="employees" method="post">
                <input type="hidden" name="empId" value="${employee != null ? employee.empId : 0}">
                <div class="form-grid">
                    <input type="text" name="firstName" placeholder="First Name" value="${employee != null ? employee.firstName : ''}" required>
                    <input type="text" name="lastName" placeholder="Last Name" value="${employee != null ? employee.lastName : ''}" required>
                    <input type="email" name="email" placeholder="Email Address" value="${employee != null ? employee.email : ''}" required>
                    <input type="text" name="phone" placeholder="Phone Number" value="${employee != null ? employee.phone : ''}">
                    <input type="number" step="0.01" name="salary" placeholder="Salary (₹)" value="${employee != null ? employee.salary : ''}" required>
                    <select name="deptId" required>
                        <option value="">Select Department</option>
                        <option value="1" ${employee != null && employee.deptId == 1 ? 'selected' : ''}>Engineering</option>
                        <option value="2" ${employee != null && employee.deptId == 2 ? 'selected' : ''}>Human Resources</option>
                        <option value="3" ${employee != null && employee.deptId == 3 ? 'selected' : ''}>Finance</option>
                    </select>
                    <input type="date" name="joiningDate" value="${employee != null ? employee.joiningDate : ''}" required>
                </div>
                <div>
                    <button type="submit" class="btn-primary">${employee != null ? 'Update Employee' : 'Add Employee'}</button>
                    <c:if test="${employee != null}">
                        <a href="employees" class="btn-cancel">Cancel</a>
                    </c:if>
                </div>
            </form>
        </div>

        <!-- Employee Table -->
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Salary</th>
                        <th>Department</th>
                        <th>Joining Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${employees}">
                        <tr>
                            <td>${emp.empId}</td>
                            <td>${emp.firstName} ${emp.lastName}</td>
                            <td>${emp.email}</td>
                            <td>${emp.phone}</td>
                            <td>₹${emp.salary}</td>
                            <td>${emp.deptName}</td>
                            <td>${emp.joiningDate}</td>
                            <td class="action-links">
                                <a href="employees?action=edit&id=${emp.empId}" class="action-edit">Edit</a>
                                <a href="employees?action=delete&id=${emp.empId}" class="action-delete" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>