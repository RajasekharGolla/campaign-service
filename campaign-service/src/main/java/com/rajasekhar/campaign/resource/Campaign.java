package com.rajasekhar.campaign.resource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Campaign {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long partnerId;
	private Long duration;
	private String adContent;
	private Date createdDate;
	private String status;
	
	//	"partner_id": "unique_string_representing_partner',
	//	"duration": "int_representing_campaign_duration_in_seconds_from_now"
	//	"ad_content": "string_of_content_to_display_as_ad"
	
	public Long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
