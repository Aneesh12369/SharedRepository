package com.me.trademetrics;

public interface TradeMetricsService {

	void Record(TradeDetails details);

	TradeMetricsSummary calculateSummary(TradeSearchCriteria criteria);

}
