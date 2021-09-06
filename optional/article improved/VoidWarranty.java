//NULL OBJECT
public class VoidWarranty implements Warranty{
	
	@Override
	public void claim(Runnable action){}

	@Override
	public Warranty on(LocalDate date){ return this; }

	@Override
    public Optional<Warranty> filter(LocalDate date) { return Optional.empty(); }
}