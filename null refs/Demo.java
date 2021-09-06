public class Demo{

	//!!! POOR CODE - NULL REFS
	//!!! BOOLEAN CONDITIONS
	//!!! BOOLEAN PARAMS
	public void claimWarranty(Article article, boolean isInGoodCondition, boolean isNonOperational){
		LocalDate today = LocalDate.now();

		if(isInGoodCondition && isNonOperational &&
			article.getMoneyBackGuarantee() != null &&
			article.getMoneyBackGuarantee().isValidOn(today)){
			System.out.println("Offer money back.");
		}

		if(isNonOperational &&
			article.getExpressWarranty() != null &&
			article.getExpressWarranty().isValidOn(today)){
			System.out.println("Offer repair.");
		}

		System.out.println("=================");

	}

	public void run(){

	}
}