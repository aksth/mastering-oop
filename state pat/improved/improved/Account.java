public class Account{

	private boolean isVerified;
	private boolean isClosed;
	private BigDecimal balance;
	private Freezable freezable;

	public Account(AccountUnfrozen onUnfreeze){
		this.balance = BigDecimal.ZERO;
		this.freezable = new FreezableActive(onUnfrozen);
	}

	public void holderVerified(){
		this.isVerified = true;
	}

	public void closeAccount(){
		this.isClosed = true;
	}

	public void freezeAccount(){
		if(this.isClosed)
			return;
		if(!this.isVerified)
			return;
		this.freezable = this.freezable.freezeAccount();
	}

	public void deposite(BigDecimal amount){
		if(this.isClosed)
			return;
		this.freezable = this.freezable.deposite();
		this.balance = this.balance.add(amount);
	}

	public void withdraw(BigDecimal amount){

		if(!this.isVerified)
			return;
		if(this.isClosed)
			return;
		this.freezable = this.freezable.withdraw();
		this.balance = this.balance.subtract(amount);
	}

}