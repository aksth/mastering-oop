public class Frozen implements Active{
	
	private AccountUnfrozen onUnfrozen;

	public FreezableFrozen(AccountUnfrozen onUnfrozen){
		this.onUnfrozen = onUnfrozen;
	}

	@Override
	public Active deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance){
		addToBalance.accept(amount);
		this.unfreeze();
	}

	@Override
	public Active withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance){
		if(balance.compareTo(amount) >= 0){
			subtractFromBalance.accept(amount);
		}
		this.unfreeze();
	}

	private Active unfreeze(){
		this.onUnfrozen.handle();
		return new Active(this.onUnfrozen);
	}

	@Override
	public Active freezeAccount(this.onUnfrozen){
		return this;
	}

	@Override
	public AccountState holderVerified(){
		return this;
	}

	@Override
	public AccountState closeAccount(){
		return this;
	}
}