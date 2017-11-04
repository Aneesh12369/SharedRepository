package com.me.trademetrics;

import java.util.Optional;

public class TradeSearchCriteria {

	private Optional<String> client;
	private Optional<Product> product;
	private Optional<Income> income;
	private Optional<String> companyName;

	public static class Builder {
		private Optional<String> client;
		private Optional<Product> product;
		private Optional<Income> income;
		private Optional<String> companyName;

		public Builder client(Optional<String> client) {
			this.client = client;
			return this;
		}

		public Builder product(Optional<Product> product) {
			this.product = product;
			return this;
		}

		public Builder income(Optional<Income> income) {
			this.income = income;
			return this;
		}

		public Builder companyName(Optional<String> companyName) {
			this.companyName = companyName;
			return this;
		}

		public TradeSearchCriteria build() {
			return new TradeSearchCriteria(this);
		}
	}

	private TradeSearchCriteria(Builder builder) {
		this.client = builder.client;
		this.product = builder.product;
		this.income = builder.income;
		this.companyName = builder.companyName;
	}

	public Optional<String> getClient() {
		return client;
	}

	public Optional<Product> getProduct() {
		return product;
	}

	public Optional<Income> getIncome() {
		return income;
	}

	public Optional<String> getCompanyName() {
		return companyName;
	}
}
