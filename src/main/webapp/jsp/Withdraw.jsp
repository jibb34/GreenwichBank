<div class="form-container">
    <h2>Transfer Funds</h2>
    <form action="Controller" method="post">
        <input type="hidden" name="action" value="transfer">

        <div class="form-group">
            <label for="fromAccount">From Account ID:</label>
            <input type="number" name="fromAccount" id="fromAccount" required>
        </div>

        <div class="form-group">
            <label for="toAccount">To Account ID:</label>
            <input type="number" name="toAccount" id="toAccount" required>
        </div>

        <div class="form-group">
            <label for="amount">Amount ($):</label>
            <input type="number" name="amount" id="amount" step="0.01" required>
        </div>

        <button type="submit" class="submit-btn">Transfer</button>
    </form>
</div>