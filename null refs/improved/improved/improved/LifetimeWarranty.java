public class LifetimeWarranty implements Warranty{
	private LocalDate issuedOn;

	public LifetimeWarranty(LocalDate issuedOn){
		this.issuedOn = issuedOn;
	}

	@Override
	public boolean isValidOn(LocalDate date){
		return this.issuedOn.compareTo(date) <= 0;
	}
}