public class Article{
	private Warranty moneyBackGuarantee;
	private Warranty expressWarranty;
	private Warranty effectiveExpressWarranty = Warranty.VOID;

	public Article(Warranty moneyBackGuarantee, Warranty expressWarranty){
		this(moneyBackGuarantee, expressWarranty, Warranty.VOID)
	}

	private Article(Warranty moneyBackGuarantee, Warranty expressWarranty, Warranty effectiveExpressWarranty){
		
		if(moneyBackGuarantee == null) throw new IllegalArgumentException();
		if(expressWarranty == null) throw new IllegalArgumentException();

		this.moneyBackGuarantee = moneyBackGuarantee;
		this.expressWarranty = expressWarranty;
		this.effectiveExpressWarranty = effectiveExpressWarranty;
	}

	public Warranty getMoneyBackGuarantee(){
		return moneyBackGuarantee;
	}

	public Warranty getExpressWarranty(){
		return effectiveExpressWarranty;
	}

	//changes just the state of Article
	public Article withVisibleDamange(){
		return new Article(Warranty.VOID, this.expressWarranty, this.effectiveExpressWarranty);
	}

	//changes just the state of Article
	public Article notOperational(){
		return new Article(this.moneyBackGuarantee, this.expressWarranty, this.expressWarranty)
	}
}