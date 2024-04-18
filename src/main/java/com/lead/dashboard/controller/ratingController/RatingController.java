package com.lead.dashboard.controller.ratingController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.dto.RatingDto;
import com.lead.dashboard.service.RatingService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@PostMapping(UrlsMapping.ADD_USER_RATING)
	public Ratings addUserRating(@RequestBody RatingDto ratingDto)
	{
		Ratings rating=ratingService.addUserRating(ratingDto);
		
		return rating;		 

	}
	@GetMapping(UrlsMapping.GET_ALL_USER_RATING)
	public List<Ratings> getAllUserRating()
	{
		List<Ratings> rating=ratingService.getAllUserRating();
		
		return rating;		 

	}
	@GetMapping(UrlsMapping.GET_ALL_RATING_BY_SERVICE_ID)
	public List<Ratings> getAllRatingByServiceId(@RequestParam Long serviceId)
	{
		List<Ratings> rating=ratingService.getAllRatingByServiceId(serviceId);
		
		return rating;		 

	}
}
