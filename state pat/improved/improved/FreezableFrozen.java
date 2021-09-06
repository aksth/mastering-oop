public class FreezableFrozen implements Freezable{
	
	private AccountUnfrozen onUnfrozen;

	public FreezableFrozen(AccountUnfrozen onUnfrozen){
		this.onUnfrozen = onUnfrozen;
	}

	@Override
	public Freezable deposit(){
		this.unfreeze();
	}

	@Override
	public Freezable withdraw(){
		this.unfreeze();
	}

	private Freezable unfreeze(){
		this.onUnfrozen.handle();
		return new FreezableActive(this.onUnfrozen);
	}

	@Override
	public Freezable freezeAccount(this.onUnfrozen){
		return this;
	}
}