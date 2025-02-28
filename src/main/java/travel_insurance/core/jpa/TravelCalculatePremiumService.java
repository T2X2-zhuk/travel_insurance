package travel_insurance.core.jpa;

import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
