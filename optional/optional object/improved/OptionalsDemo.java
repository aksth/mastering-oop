public class OptionalsDemo{

	private static abstract class MaybeString{

		public abstract MaybeString toUpperCase();
		public abstract String orElse(String substitute);

	}

	private static class Some extends MaybeString{
		private String content;

		public Some(String content){
			this.content = content;
		}

		@Override
		public MaybeString toUpperCase(){
			return new Some(this.content.toUpperCase());
		}

		@Override
		public String orElse(String substitute){
			return this.content;
		}
	}

	//replacement of null case
	private static class None extends MaybeString{
		public None(){}

		@Override
		public MaybeString toUpperCase(){
			return this;
		}

		@Override
		public String orElse(String substitute){
			return substitute;
		}
	} 

	private void display(MaybeString value){
		MaybeString uppercase = value.toUpperCase()
		String printout = uppercase.orElse("Nothing to show...");

		System.out.println(printout);
	}

	public void run(){
		this.display(new None());
		this.display(new Some("Something"));
		this.display(new Some("This is a sentence."));
	}
}