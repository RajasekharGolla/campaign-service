package com.rajasekhar.campaign.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rajasekhar.campaign.repo.CampaignRepo;
import com.rajasekhar.campaign.resource.Campaign;

@Controller
public class CampaignController {

	@Autowired
	private CampaignRepo campaignRepo;
	
	
	/**
	 * Create Ad Campaign via HTTP POST
	 * @param campaign
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/campaign/save", method=RequestMethod.POST)
	public @ResponseBody Campaign saveCampaign(@RequestBody Campaign campaign) throws Exception{
		//Save to h2
		List<Campaign> existingCampaigns = campaignRepo.findByPartnerIdAndStatus(campaign.getPartnerId(), "Active");
		
		Date currentTime = new Date();
		if(existingCampaigns.size()> 0)
			if(currentTime.getTime() > existingCampaigns.get(0).getCreatedDate().getTime() + existingCampaigns.get(0).getDuration()){
				//Deactivate existing campaign
				existingCampaigns.get(0).setStatus("Inactive");
				campaignRepo.save(existingCampaigns.get(0));	
			}else{
				//Not Ok
				throw new Exception("An active campaign already exists for the given Partner Id");
			}
		//Save new campaign
		campaign.setStatus("Active");
		campaign.setCreatedDate(new Date());
		return campaignRepo.save(campaign);
		
	}
	
	/**
	 * Fetch Ad Campaign for a Partner
	 * @param partnerId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/campaign/show", method=RequestMethod.GET)
	public @ResponseBody Campaign viewActiveCampaignByPartnerId(@RequestParam(value="partnerId") Long partnerId) throws Exception{
		List<Campaign> existingCampaigns = campaignRepo.findByPartnerIdAndStatus(partnerId, "Active");
		

		Date currentTime = new Date();
		if(existingCampaigns.size()> 0)
			if(currentTime.getTime() > existingCampaigns.get(0).getCreatedDate().getTime() + existingCampaigns.get(0).getDuration()){
				//Deactivate existing campaign
				existingCampaigns.get(0).setStatus("Inactive");//TODO
				campaignRepo.save(existingCampaigns.get(0));
				throw new Exception("There are no active campaign available");	
			}				
				
		return existingCampaigns.get(0);
		
	}
	
	/**
	 * URL to return a list of all campaigns as JSON.
	 * @return
	 */
	@RequestMapping(value="/campaign/all", method=RequestMethod.GET)
	public @ResponseBody List<Campaign> viewAllCampaigns(){
		//Retrieve all from memory
		return campaignRepo.findAll();
		
	}
}
