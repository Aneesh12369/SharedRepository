package com.me.trademetrics;

public class TradeDetails {

	private String client;
	private Product product;
	private String companyName;
	private Income income;
	private long execuitonTimeInMillis;

	public TradeDetails(String client, Product product, String companyName, Income income, long execuitonTimeInMillis) {
		this.client = client;
		this.product = product;
		this.companyName = companyName;
		this.income = income;
		this.execuitonTimeInMillis = execuitonTimeInMillis;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public long getExecuitonTimeInMillis() {
		return execuitonTimeInMillis;
	}

	public void setExecuitonTimeInMillis(long execuitonTimeInMillis) {
		this.execuitonTimeInMillis = execuitonTimeInMillis;
	}

	@Override
	public String toString() {
		return "TradeDetails [client=" + client + ", product=" + product + ", companyName=" + companyName + ", income="
				+ income + ", execuitonTimeInMillis=" + execuitonTimeInMillis + "]";
	}
	
	

}
