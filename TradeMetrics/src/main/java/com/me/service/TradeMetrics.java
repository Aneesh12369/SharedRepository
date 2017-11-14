package com.me.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.event.TreeWillExpandListener;

import com.me.trademetrics.TradeDetails;
import com.me.trademetrics.TradeMetricsService;
import com.me.trademetrics.TradeMetricsSummary;
import com.me.trademetrics.TradeSearchCriteria;

public class TradeMetrics implements TradeMetricsService {

	private List<TradeDetails> tradeData = new ArrayList<TradeDetails>();

	@Override
	public void Record(TradeDetails details) {

		if (details != null) {

			tradeData.add(details);
		}

	}

	@Override
	public TradeMetricsSummary calculateSummary(TradeSearchCriteria criteria) {

		System.out.println(" Request calculate ===========  " + tradeData.size());
		// new TradeSearchCriteria.Builder().companyName(Optional.of(""));

		// criteria.getClient();

		//System.out.println("  " + criteria.getClient().get());
		boolean criteriaFlag = false;
		boolean productFlag = false;
		boolean incomeFlag = false;
		boolean companyNameFlag = false;

		boolean searchcriteriaFlag = false;
		boolean searchproductFlag = false;
		boolean searchincomeFlag = false;
		boolean searchcompanyNameFlag = false;

		//System.out.println(criteria.getClient());
		//System.out.println(criteria.getCompanyName() + "- --- " + criteria.getCompanyName().isPresent());
		if (criteria.getClient().isPresent()) {
			criteriaFlag = true;
		}
		if (criteria.getCompanyName().isPresent()) {
			companyNameFlag = true;
		}
		if (criteria.getProduct().isPresent()) {
			productFlag = true;
		}
		if (criteria.getIncome().isPresent()) {
			incomeFlag = true;
		}
		List<Long> exeTime = new ArrayList<Long>();
		// System.out.println("companyNameFlag " + companyNameFlag);

		for (int i = 0; i < tradeData.size(); i++) {

			TradeDetails searchObj = tradeData.get(i);
		/*	System.out.println(searchObj.getClient() + " == " + criteria.getClient());
			System.out.println(searchObj.getCompanyName() + " == " + criteria.getCompanyName());
			System.out.println(searchObj.getProduct() + " == " + criteria.getProduct());
			System.out.println(searchObj.getIncome() + " == " + criteria.getIncome());
*/
			if (criteriaFlag) {
				if (searchObj.getClient().equals(criteria.getClient().get())) {
					searchcriteriaFlag = true;
				}
			}
			if (companyNameFlag) {
				if (searchObj.getCompanyName().equals(criteria.getCompanyName().get())) {
					searchcompanyNameFlag = true;
				}
			}
			if (productFlag) {
				if (searchObj.getProduct().equals(criteria.getProduct().get())) {
					searchproductFlag = true;
				}
			}
			if (incomeFlag) {
				if (searchObj.getIncome().equals(criteria.getIncome().get())) {
					searchincomeFlag = true;
				}
			}
			//System.out.println(" \n\n" + criteriaFlag + (searchcriteriaFlag) + companyNameFlag + searchcompanyNameFlag);
			if ((criteriaFlag == searchcriteriaFlag) && (companyNameFlag == searchcompanyNameFlag)
					&& (searchincomeFlag == incomeFlag) && (searchproductFlag == productFlag)) {
				exeTime.add(tradeData.get(i).getExecuitonTimeInMillis());
				//System.out.println(" 1  ---- ");
			}
			searchcriteriaFlag = false;
			searchcompanyNameFlag = false;
			searchproductFlag = false;
			searchincomeFlag = false;
		}
		float sum = 0.0f;
		float avg = 0.0f;
		Collections.sort(exeTime);
		for (int i = 0; i < exeTime.size(); i++) {
			//System.out.println(" Exe Time :: "+exeTime.get(i));
			sum += exeTime.get(i);
		}
		System.out.println(" Size :: "+ exeTime.size()+" Avg "+sum+"   Avg :: "+ sum / exeTime.size());
		TradeMetricsSummary summary = null;
		avg = sum / exeTime.size();
		if (exeTime.size() > 0) {
			System.out.println(" Min : " + exeTime.get(0) + " Avg : " + sum / exeTime.size() + " Max : "
					+ exeTime.get(exeTime.size() - 1));
			TradeMetricsSummary.Builder buil = new TradeMetricsSummary.Builder();

			buil.min(Optional.of(exeTime.get(0)));
			buil.avg(Optional.of(new BigDecimal(avg)));
			buil.max(Optional.of(exeTime.get(exeTime.size() - 1)));
			summary = buil.build();
		} else
			System.out.println(" No Mathcing Result Found");

		return summary;
	}

}
