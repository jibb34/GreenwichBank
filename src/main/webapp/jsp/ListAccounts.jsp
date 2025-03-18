<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Greenwich Bank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
    <header>
        <h1>Greenwich Bank Co.</h1>
        <nav>
            <ul>
                <li><a href=Controller?action=home>Home</a></li>
                <li><a href=Controller?action=listStudents>Students</a></li>
                <li><a href=Controller?action=listAccounts>Accounts</a></li>
                <li><a href=Controller?action=transfer>Transfer</a></li>
            </ul>
        </nav>
    </header>
    
    <div class="container">
  <body>
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
  </body>
</div>
    <footer>
        <p>&copy; 2025 Greenwich Bank Co. Your money safe with us.</p>
    </footer>
</body>
</html>
