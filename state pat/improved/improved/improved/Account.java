public class Account{

	private BigDecimal balance;
	private AccountState state;

	public Account(AccountUnfrozen onUnfreeze){
		this.balance = BigDecimal.ZERO;
		this.state = new Active(onUnfrozen);
	}

	public void holderVerified(){
		this.state = this.state.holderVerified(); 
	}

	public void closeAccount(){
		this.state = this.state.closeAccount();
	}

	public void freezeAccount(){
		this.state = this.state.freezeAccount();
	}

	public void deposite(BigDecimal amount){
		this.state = this.state.deposite(amount, this::addToBalance);
	}

	public void addToBalance(BigDecimal amount){
		this.balance = this.balance.add(amount);
	}

	public void withdraw(BigDecimal amount){
		this.state = this.state.withdraw(this.balance, amount, this::subtractFromBalance);
	}

	public void subtractFromBalance(BigDecimal amount){
		this.balance = this.balance.subtract(amount);
	}
}