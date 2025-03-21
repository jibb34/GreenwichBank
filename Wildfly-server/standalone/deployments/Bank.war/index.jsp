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
        <h2>Welcome, ${userName}!</h2>
        <p>Your financial data at a glance.</p>
        
        <!-- Optionally display a list of accounts if provided -->
        <c:if test="${not empty accounts}">
            <h3>Your Accounts:</h3>
            <ul>
                <c:forEach var="account" items="${accounts}">
                    <li>${account.alias} - Balance: ${account.balance}</li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
    
    <footer>
        <p>&copy; 2025 Greenwich Bank Co. Your money safe with us.</p>
    </footer>
</body>
</html>
