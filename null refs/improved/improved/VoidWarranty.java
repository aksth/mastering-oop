public class VoidWarranty implements Warranty{
	
	@Override
	publc boolean isValidOn(LocalDate date){
		return false;
	}
}