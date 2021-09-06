public class OptionalsDemo{

	private static abstract class Maybe<T>{

		public abstract <TResult> Maybe<TResult> map(Function<T, TResult> transform);
		//returns optional object itself
		public abstract <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform);
		public abstract T orElse(T substitute);
		public abstract boolean isPresent();
		public abstract T get();

	}

	private static class Some<T> extends Maybe<T>{
		private T content;

		public Some(T content){
			this.content = content;
		}

		@Override
		public <TResult> Maybe<TResult> map(Function<T, TResult> transform){
			return new Some(transform.apply(this.content));
		}

		@Override
		public <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform){
			return transform.apply(this.content);
		}

		@Override
		public T orElse(T substitute){
			return this.content;
		}

		@Override
		public boolean isPresent(){ return true; }

		@Override
		public T get() {return this.content;}
	}

	//replacement of null case
	private static class None<T> extends Maybe<T>{
		public None(){}

		@Override
		public <TResult> Maybe<TResult> map(Function<T, TResult> transform){
			return new None<TResult>();
		}

		@Override
		public <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform){
			return new None<TResult>();
		}

		@Override
		public T orElse(T substitute){
			return substitute;
		}

		@Override
		public boolean isPresent(){ return false; }

		@Override
		public T get() { throw new IllegalStateException(); }
	} 

	private void display(Maybe<String> value){

		System.out.println(value.map(String::toUpperCase).orElse("Nothing to show..."));
	}

	public void run(){
		this.display(new None());
		this.display(new Some("Something"));
		this.display(new Some("This is a sentence."));
	}
}