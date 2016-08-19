package com.rajasekhar.campaign.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajasekhar.campaign.resource.Campaign;

public interface CampaignRepo extends JpaRepository<Campaign, Long> {
	public List<Campaign> findByPartnerIdAndStatus(Long partnerId, String status); 
}
