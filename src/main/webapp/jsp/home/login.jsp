<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="form-container">
    <h2>Choose a Student to Log In</h2>

    <form id="loginForm">
        <div class="form-group">
            <label for="username">Student:</label>
            <select name="username" id="username" class="styled-select" required>
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentName}" data-id="${student.studentID}">
                        ${student.studentName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="submit-btn">Log In</button>
    </form>
</div>
