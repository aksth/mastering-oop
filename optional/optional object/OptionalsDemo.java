public class OptionalsDemo{

	private void display(String value){
		String printout = value == null ? "Nothing to show..."
						: value.toUpperCase();

		System.out.println(printout);
	}

	public void run(){
		this.display(null);
		this.display("Something");
		this.display("This is a sentence.");
	}
}