<!-- TODO: Implement Submisison results  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<div class="result-container">
    <c:choose>
        <c:when test="${not empty error}">
            <h2>❌ Transaction Failed</h2>
            <p>${error}</p>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${param.transaction == 'deposit'}">
                    <h2>✅ Deposit Successful</h2>
                    <p><strong>Account:</strong> ${account.accountAlias} (ID: ${account.accountID})</p>
                    <p>New Balance: £${account.accountBalance}</p>
                </c:when>

                <c:when test="${param.transaction == 'withdraw'}">
                    <h2>✅ Withdraw Successful</h2>
                    <p><strong>Account:</strong> ${account.accountAlias} (ID: ${account.accountID})</p>
                    <p>New Balance: £${account.accountBalance}</p>
                </c:when>

                <c:when test="${param.transaction == 'transfer'}">
                    <h2>✅ Transfer Successful</h2>

                    <div class="transfer-box">
                        <div class="account-section">
                            <h3>From Account</h3>
                            <p><strong>${fromAccount.accountAlias} (ID: ${fromAccount.accountID})</strong></p>
                            <p class="negative">– £${amount}</p>
                            <p>New Balance: £${fromAccount.accountBalance}</p>
                        </div>

                        <div class="account-section">
                            <h3>To Account</h3>
                            <p><strong>${toAccount.accountAlias} (ID: ${toAccount.accountID})</strong></p>
                            <p class="positive">+ £${amount}</p>
                            <p>New Balance: £${toAccount.accountBalance}</p>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <br>
    <form action="${pageContext.request.contextPath}/">
        <button type="submit" class="btn">Back to Home</button>
    </form>
</div>