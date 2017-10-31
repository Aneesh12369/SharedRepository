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
}
