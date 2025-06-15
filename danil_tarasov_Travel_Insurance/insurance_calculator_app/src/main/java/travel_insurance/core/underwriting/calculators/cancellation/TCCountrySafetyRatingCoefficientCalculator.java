package travel_insurance.core.underwriting.calculators.cancellation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.domain.TCCountrySafetyRatingCoefficient;
import travel_insurance.core.repositories.TCCountrySafetyRatingCoefficientRepository;

import java.math.BigDecimal;

@Component
class TCCountrySafetyRatingCoefficientCalculator {

    @Autowired private TCCountrySafetyRatingCoefficientRepository countrySafetyRatingCoefficientRepository;

    BigDecimal calculate(AgreementDTO agreement) {
        return countrySafetyRatingCoefficientRepository.findByCountryIc(agreement.getCountry())
                .map(TCCountrySafetyRatingCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Country safety rating coefficient not found by country id = " + agreement.getCountry()));
    }

}
