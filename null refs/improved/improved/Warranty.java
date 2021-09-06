public interface Warranty{
	boolean isValidOn(LocalDate date);

	Warranty VOID = new VoidWarranty();
}