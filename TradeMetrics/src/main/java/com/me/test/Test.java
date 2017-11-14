package com.me.test;

import java.util.Optional;

import com.me.service.TradeMetrics;
import com.me.trademetrics.Income;
import com.me.trademetrics.Product;
import com.me.trademetrics.TradeDetails;
import com.me.trademetrics.TradeMetricsSummary;
import com.me.trademetrics.TradeSearchCriteria;

public class Test {
	public static void main(String[] args) {
		TradeDetails details = new TradeDetails("Foo", Product.EQUITY, "IBM", Income.LOSS, 24l);
		TradeDetails details1 = new TradeDetails("Bar", Product.EQUITY, "TCS", Income.SUB, 34l);
		TradeDetails details2 = new TradeDetails("Bar", Product.STOCK, "ERRICSON", Income.LOSS, 51l);
		TradeDetails details3 = new TradeDetails("Foo", Product.STOCK, "IBM", Income.PRO, 16l);
		TradeDetails details4 = new TradeDetails("Foo", Product.DEBIT, "TCS", Income.SUB, 12l);
		TradeDetails details5 = new TradeDetails("Foo", Product.EQUITY, "IBM", Income.LOSS, 12l);
		TradeMetrics metrics = new TradeMetrics();
		metrics.Record(details);
		metrics.Record(details1);
		metrics.Record(details2);
		metrics.Record(details3);
		metrics.Record(details4);
		metrics.Record(details5);
		// TradeSearchCriteria criteria=

		TradeSearchCriteria.Builder builder = new TradeSearchCriteria.Builder();
		builder.client(Optional.of("Foo"));
		builder.product(Optional.of(Product.EQUITY));
		builder.companyName(Optional.of("IBM"));
		builder.income(Optional.empty());
		TradeMetricsSummary summary = metrics.calculateSummary(builder.build());
		if (summary != null)
			System.out.println(summary.toString());

	}
}
