<!-- Add / Edit Employee Form -->
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
            <button type="submit" class="btn-primary">${employee != null ? 'Update Employee' : 'Add Employee'}</button>
            <c:if test="${employee != null}">
                <a href="employees" style="margin-left: 10px; text-decoration: none; color: #666;">Cancel</a>
            </c:if>
        </form>