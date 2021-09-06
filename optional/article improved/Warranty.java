public interface Warranty{

	Warranty on(LocalDate date);

	Optional<Warranty> filter(LocalDate date);

	default void claim(Runnable action){ action.run(); }

	//NULL OBJECT PATTERN
	Warranty VOID = new VoidWarranty();

	//SPECIAL CASE PATTERN
	static Warranty lifetime(LocalDate issuedOn){
		return new LifetimeWarranty(issuedOn);
	}
}