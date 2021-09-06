public class Article{
	private Warranty moneyBackGuarantee;
	private Warranty expressWarranty;
	private Warranty effectiveExpressWarranty = Warranty.VOID;
	private Part sensor;
	private Warranty extendedWarranty;

	public Article(Warranty moneyBackGuarantee, Warranty expressWarranty){
		this(moneyBackGuarantee, expressWarranty, Warranty.VOID, null, Warranty.VOID)
	}

	private Article(Warranty moneyBackGuarantee, 
					Warranty expressWarranty, 
					Warranty effectiveExpressWarranty,
					Part sensor,
					Warranty extendedWarranty){

		this.moneyBackGuarantee = moneyBackGuarantee;
		this.expressWarranty = expressWarranty;
		this.effectiveExpressWarranty = effectiveExpressWarranty;
		this.sensor = sensor;
		this.extendedWarranty = extendedWarranty;
	}

	public Warranty getMoneyBackGuarantee(){
		return moneyBackGuarantee;
	}

	public Warranty getExpressWarranty(){
		return effectiveExpressWarranty;
	}

	public Warranty getExtendedWarranty(){
		//VERY BAD CODE
		/*if (sensor == null) return Warranty.VOID;
		LocalDate detectedOn = this.sensor.getDefectDetectedOn();
		if (detectedOn == null) return Warranty.VOID;
		return this.extendedWarranty.on(this.sensor.getDefectDetectedOn());*/

		//BAD CODE
		return this.sensor == null ? Warranty.VOID
				: this.sensor.apply(this.extendedWarranty);

	}

	//changes just the state of Article
	public Article withVisibleDamange(){
		return new Article(Warranty.VOID, this.expressWarranty, this.effectiveExpressWarranty, this.sensor, this.extendedWarranty);
	}

	//changes just the state of Article
	public Article notOperational(){
		return new Article(this.moneyBackGuarantee, this.expressWarranty, this.expressWarranty, this.sensor, this.extendedWarranty)
	}

	public Article install(Part sensor, Warranty extendedWarranty){
		return new Article(this.moneyBackGuarantee, this.expressWarranty, this.effectiveExpressWarranty,
			sensor, extendedWarranty)
	}

	//sensor has been detected to be defective. so install new sensor with 
	public Article sensorNotOperational(LocalDate detectedOn){
		return this.install(this.sensor.defective(detectedOn), this.extendedWarranty);	
	}
}