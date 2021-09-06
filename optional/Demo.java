public class Demo{
	
	public void claimWarranty(Article article){
		
		LocalDate today = LocalDate.now();

		article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);

		article.getExpressWarranty().on(today).claim(this::offerRepair);

		article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);

		System.out.println("=================");

	}

	private void offerMoneyBack(){
		System.out.println("Offer money back.");
	}

	private void offerRepair(){
		System.out.println("Offer repair.");
	}

	private void offerSensorRepair(){
		System.out.println("Offer sensor repair.");
	}

	public void run(){
		LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
		Warranty moneyBack1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(60));
		Warranty warranty1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(365));

		Part sensor = new Part(sellingDate);
		Warranty sensorWarranty = new TimeLimitedWarranty(sellingDate, Duration.ofDays(90));

		Article item1 = new Article(moneyBack1, warranty1).install(sensor, sensorWarranty);

		this.claimWarranty(item1);
		//1,0
		this.claimWarranty(item1.withVisibleDamage());
		//0,0
		this.claimWarranty(item1.notOperational().withVisibleDamage());
		//0,1
		this.claimWarranty(item1.notOperational());
		//1,1

		LocalDate sensorExamined = LocalDate.now().minus(2, ChronoUnit.DAYS);
		this.claimWarranty(item1.sensorNotOperational(sensorExamined));
		//1,0,1
		this.claimWarranty(item1.notOperational().sensorNotOperational(sensorExamined));
		//1,1,1

		Article item2 = new Article(Warranty.VOID, Warranty.lifetime(sellingDate));
		this.claimWarranty(item2);
		//0,0
		this.claimWarranty(item2.withVisibleDamage().notOperational());
		//0,1
		
	}
}