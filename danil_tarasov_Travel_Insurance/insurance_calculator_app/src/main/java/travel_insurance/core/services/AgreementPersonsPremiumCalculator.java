package travel_insurance.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.underwriting.TravelPremiumCalculationResult;
import travel_insurance.core.underwriting.TravelPremiumUnderwriting;

@Component
class AgreementPersonsPremiumCalculator {

    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;

    void calculateRiskPremiums(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            person.setRisks(calculationResult.getRisks());
        });
    }

}
