public interface Warranty{
	boolean isValidOn(LocalDate date);

	Warranty VOID = new VoidWarranty();

	static Warranty lifetime(LocalDate issuedOn){
		return new LifetimeWarranty(issuedOn);
	}
}