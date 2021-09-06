public interface AccountState{
	AccountState deposite(BigDecimal amount, Consumer<BigDecimal> addToBalance);
	AccountState withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance);
	AccountState freezeAccount();
	AccountState holderVerified();
	AccountState closeAccount();
}