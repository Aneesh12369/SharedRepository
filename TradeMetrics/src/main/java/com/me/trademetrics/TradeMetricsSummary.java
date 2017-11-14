package com.me.trademetrics;

import java.math.BigDecimal;
import java.util.Optional;

public class TradeMetricsSummary {

	private Optional<Long> max;
	private Optional<Long> min;
	private Optional<BigDecimal> avg;

	public static class Builder {
		private Optional<Long> max;
		private Optional<Long> min;
		private Optional<BigDecimal> avg;

		public Builder max(Optional<Long> max) {
			this.max = max;
			return this;
		}

		public Builder min(Optional<Long> min) {
			this.min = min;
			return this;
		}

		public Builder avg(Optional<BigDecimal> avg) {
			this.avg = avg;
			return this;
		}

		public TradeMetricsSummary build() {
			return new TradeMetricsSummary(this);
		}
	}

	private TradeMetricsSummary(Builder builder) {
		this.max = builder.max;
		this.min = builder.min;
		this.avg = builder.avg;
	}

	@Override
	public String toString() {
		return "TradeMetricsSummary [max=" + max + ", min=" + min + ", avg=" + avg + "]";
	}

	public Optional<Long> getMax() {
		return max;
	}

	public void setMax(Optional<Long> max) {
		this.max = max;
	}

	public Optional<Long> getMin() {
		return min;
	}

	public void setMin(Optional<Long> min) {
		this.min = min;
	}

	public Optional<BigDecimal> getAvg() {
		return avg;
	}

	public void setAvg(Optional<BigDecimal> avg) {
		this.avg = avg;
	}
	
	
}
