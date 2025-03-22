<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <h2>List of Accounts</h2>
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
</div>
