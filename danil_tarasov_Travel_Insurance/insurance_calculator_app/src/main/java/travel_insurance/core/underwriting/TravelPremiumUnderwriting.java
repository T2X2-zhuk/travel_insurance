package travel_insurance.core.underwriting;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);

}
