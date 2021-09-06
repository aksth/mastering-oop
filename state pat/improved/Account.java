public class Account{

	private boolean isVerified;
	private boolean isClosed;
	private BigDecimal balance;

	private AccountUnfrozen onUnfreeze;
	private UnfreezeAccount unfreezeAccount; //like a flag which points to whether freeze or unfreeze when unfreezeAccount is executed

	public Account(AccountUnfrozen onUnfreeze){
		this.balance = BigDecimal.ZERO;
		this.onUnfreeze = onUnfreeze;
		this.unfreezeAccount = this::stayUnfrozen;
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
		this.unfreezeAccount = this::unfreeze; // when unfreezeAccount will be executed, unfreezeAccount will unfreeze account
	}

	public void deposite(BigDecimal amount){
		if(this.isClosed)
			return;
		this.unfreezeAccount.execute();
		this.balance = this.balance.add(amount);
	}

	public void withdraw(BigDecimal amount){

		if(!this.isVerified)
			return;
		if(this.isClosed)
			return;
		this.unfreezeAccount.execute();
		this.balance = this.balance.subtract(amount);
	}

	private void unfreeze(){
		this.onUnfreeze.handle();
		this.unfreezeAccount = this::stayUnfrozen;
	}

	private void stayUnfrozen(){
		//Do nothing
	}
}