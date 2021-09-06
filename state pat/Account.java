public class Account{

	private boolean isVerified;
	private boolean isClosed;
	private boolean isFrozen;
	private BigDecimal balance;

	private AccountUnfrozen onUnfrozen;

	public Account(AccountUnfrozen onUnfrozen){
		this.balance = BigDecimal.ZERO;
		this.onUnfrozen = onUnfrozen;
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
		this.isFrozen = true;
	}

	public void deposite(BigDecimal amount){
		if(this.isClosed)
			return;
		if(this.isFrozen){
			this.isFrozen = false;
			this.onUnfrozen.handle();
		}
		this.balance = this.balance.add(amount);
	}

	public void withdraw(BigDecimal amount){

		if(!this.isVerified)
			return;
		if(this.isClosed)
			return;
		if(this.isFrozen){
			this.isFrozen = false;
			this.onUnfrozen.handle();
		}
		this.balance = this.balance.subtract(amount);
	}
}