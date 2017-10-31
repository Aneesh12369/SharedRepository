package com.pyro.hlr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HlrDao {

	private static Logger log = LoggerFactory.getLogger(HlrDao.class);

	@Autowired
	private JdbcTemplate template;
	private static final String STATUS_CHECK_QUERY = "select renewal_status from tbl_subscription_details where msisdn = ?";
	private static final String STATUS_UPDATE_QUERY = "update tbl_subscription_details set renewal_status = 30 where msisdn = ?";
	private static final String STATUS_DISABLE_QUERY = "update tbl_subscription_details set renewal_status = 21 where msisdn = ?";

	public boolean checkStatus(String msisdn) {
		try {
			Integer status = template.queryForObject(STATUS_CHECK_QUERY, Integer.class, msisdn);
			return status == 1 ? true : false;
		} catch (DataAccessException e) {
			log.error("exception in checking ....", e);
			return false;
		}

	}

	public boolean updateStatus(final String msisdn) {
		try {
			Integer status = template.update(STATUS_UPDATE_QUERY, msisdn);
			return status >= 1 ? true : false;
		} catch (DataAccessException e) {
			log.error("exception in updating status ....", e);
			return false;
		}

	}

	public boolean disableStatus(final String msisdn) {
		try {
			Integer status = template.update(STATUS_DISABLE_QUERY, msisdn);
			return status >= 1 ? true : false;
		} catch (DataAccessException e) {
			log.error("exception in disabling status ....", e);
			return false;
		}

	}
	
	
	
	public boolean insertSubscription(final String msisdn) {
		try {
			Integer status = template.update(STATUS_DISABLE_QUERY, msisdn);
			return status >= 1 ? true : false;
		} catch (DataAccessException e) {
			log.error("exception in disabling status ....", e);
			return false;
		}

	}
	
	
	
	
	
	public boolean insertContentSubscription(final String msisdn) {
		try {
			Integer status = template.update(STATUS_DISABLE_QUERY, msisdn);
			return status >= 1 ? true : false;
		} catch (DataAccessException e) {
			log.error("exception in disabling status ....", e);
			return false;
		}

	}
	
	
	
	
	
	
}
