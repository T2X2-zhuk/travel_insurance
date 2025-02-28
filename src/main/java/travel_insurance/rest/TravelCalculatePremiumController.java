package travel_insurance.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel_insurance.core.jpa.TravelCalculatePremiumService;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.TravelCalculatePremiumResponse;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		return calculatePremiumService.calculatePremium(request);
	}

}