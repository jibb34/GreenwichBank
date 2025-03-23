<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
<c:choose>
	<c:when test="${not empty cookie.user.value}">
    <h2>List of ${cookie.user.value}'s Accounts</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Alias</th>
            <th>Account Balance</th>
        </tr>
        <c:forEach var="account" items="${Accounts}">
            <tr>
                <td>${account.accountID}</td>
                <td>${account.accountAlias}</td>
                <td>${account.accountBalance}</td>
            </tr>
        </c:forEach>
    </table>
	<button type="submit" class="action-btn" data-action="addAccount">Create Account</button>
	</c:when>
	<c:otherwise>
    <h2>List of All Accounts</h2>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Alias</th>
				<th>Account Balance</th>
				<th>Associated Student</th>
			</tr>
			<c:forEach var="account" items="${Accounts}">
				<tr>
					<td>${account.accountID}</td>
					<td>${account.accountAlias}</td>
					<td>${account.accountBalance}</td>
					<td>${account.student.studentName}, ${account.student.studentID}</td>
				</tr>
			</c:forEach>
		</table>
    </c:otherwise>
    </c:choose>
</div>
