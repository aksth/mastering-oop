public class Closed implements AccountState{

	private AccountUnfrozen onUnfrozen;

	public Closed(AccountUnfrozen onUnfrozen){
		this.onUnfrozen = onUnfrozen;
	}
	
	@Override
	public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance){
		return this;
	}

	@Override
	public AccountState withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance){
		return this;
	}

	@Override
	public AccountState freezeAccount(){
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