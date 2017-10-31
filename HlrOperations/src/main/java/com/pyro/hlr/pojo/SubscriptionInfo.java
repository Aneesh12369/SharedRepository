/**
 * 
 */
package com.pyro.hlr.pojo;

/**
 * @author sravan
 * 
 */
public class SubscriptionInfo {

	private long scp_id=0L;
	private int cp_id;
	private String subscription_id;
	private String msisdn;
	private String service_id;
	private String service_name;
	private String first_subscribed_date;
	private String last_renewal_date;
	private String subscription_active_date;
	private String next_renewal_date;
	private int renewal_count;
	private int transection_id;
	private int status;
	private int renewal_status;
	private int sub_renewal_status;
	private String billing_date;
	private int billing_retry_count;
	private int plan_type;
	private int insufficient_count;
    private int service_channel_id;
    private int callbackType;

    public int getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(int callbackType) {
		this.callbackType = callbackType;
	}

	public int getService_channel_id() {
        return service_channel_id;
    }

    public void setService_channel_id(int service_channel_id) {
        this.service_channel_id = service_channel_id;
    }

	public String renewal_SMSDate;
	public String service_name_temp;
	public int renewal_task_type;
	public int renewal_list_id;
	
	

	public int getInsufficient_count() {
		return insufficient_count;
	}

	public void setInsufficient_count(int insufficient_count) {
		this.insufficient_count = insufficient_count;
	}
	
	public long getScp_id() {
		return scp_id;
	}

	public void setScp_id(long scp_id) {
		this.scp_id = scp_id;
	}

	public int getCp_id() {
		return cp_id;
	}

	public void setCp_id(int cp_id) {
		this.cp_id = cp_id;
	}

	public String getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getFirst_subscribed_date() {
		return first_subscribed_date;
	}

	public void setFirst_subscribed_date(String first_subscribed_date) {
		this.first_subscribed_date = first_subscribed_date;
	}

	public String getLast_renewal_date() {
		return last_renewal_date;
	}

	public void setLast_renewal_date(String last_renewal_date) {
		this.last_renewal_date = last_renewal_date;
	}

	public String getSubscription_active_date() {
		return subscription_active_date;
	}

	public void setSubscription_active_date(String subscription_active_date) {
		this.subscription_active_date = subscription_active_date;
	}

	public String getNext_renewal_date() {
		return next_renewal_date;
	}

	public void setNext_renewal_date(String next_renewal_date) {
		this.next_renewal_date = next_renewal_date;
	}

	public int getRenewal_count() {
		return renewal_count;
	}

	public void setRenewal_count(int renewal_count) {
		this.renewal_count = renewal_count;
	}

	public int getTransection_id() {
		return transection_id;
	}

	public void setTransection_id(int transection_id) {
		this.transection_id = transection_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRenewal_status() {
		return renewal_status;
	}

	public void setRenewal_status(int renewal_status) {
		this.renewal_status = renewal_status;
	}

	public int getSub_renewal_status() {
		return sub_renewal_status;
	}

	public void setSub_renewal_status(int sub_renewal_status) {
		this.sub_renewal_status = sub_renewal_status;
	}

	public String getBilling_date() {
		return billing_date;
	}

	public void setBilling_date(String billing_date) {
		this.billing_date = billing_date;
	}

	public int getBilling_retry_count() {
		return billing_retry_count;
	}

	public void setBilling_retry_count(int billing_retry_count) {
		this.billing_retry_count = billing_retry_count;
	}

	public int getPlan_type() {
		return plan_type;
	}

	public void setPlan_type(int plan_type) {
		this.plan_type = plan_type;
	}

	

	public SubscriptionInfo getCloneObject() {

		SubscriptionInfo sInfo = new SubscriptionInfo();
		sInfo.scp_id = this.scp_id;
		sInfo.cp_id = this.cp_id;
		sInfo.subscription_id = this.subscription_id;
		sInfo.msisdn = this.msisdn;
		sInfo.service_id = this.service_id;
		sInfo.first_subscribed_date = this.first_subscribed_date;
		sInfo.last_renewal_date = this.last_renewal_date;
		sInfo.subscription_active_date = this.subscription_active_date;
		sInfo.renewal_count = this.renewal_count;
		sInfo.transection_id = this.transection_id;
		sInfo.status = this.status;
		sInfo.next_renewal_date = this.next_renewal_date;

		return sInfo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text = "subscriptioninfo?";
		
		text = text + "scp_id="+scp_id;
		text = text + "&cp_id="+cp_id;
		text = text + "&subscription_id="+subscription_id;
		text = text + "&msisdn="+msisdn;
		text = text + "&service_id="+service_id;
		text = text + "&service_name="+service_name;
		text = text + "&first_subscribed_date="+first_subscribed_date;
		text = text + "&last_renewal_date="+last_renewal_date;
		text = text + "&subscription_active_date="+subscription_active_date;
		text = text + "&next_renewal_date="+next_renewal_date;
		text = text + "&renewal_count="+renewal_count;
		text = text + "&transection_id="+transection_id;
		text = text + "&status="+status;
		text = text + "&renewal_status="+renewal_status;
		text = text + "&sub_renewal_status="+sub_renewal_status;
		text = text + "&billing_date="+billing_date;
		text = text + "&billing_retry_count="+billing_retry_count;
		text = text + "&plan_type="+plan_type;
		
		return text;
	}

}
