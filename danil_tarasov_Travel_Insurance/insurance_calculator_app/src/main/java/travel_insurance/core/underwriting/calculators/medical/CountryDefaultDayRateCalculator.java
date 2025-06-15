package travel_insurance.core.underwriting.calculators.medical;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.repositories.TMCountryDefaultDayRateRepository;
import travel_insurance.core.domain.TMCountryDefaultDayRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public
class CountryDefaultDayRateCalculator {

    @Autowired private TMCountryDefaultDayRateRepository TMCountryDefaultDayRateRepository;

    public BigDecimal calculate(AgreementDTO agreement) {
        return TMCountryDefaultDayRateRepository.findByCountryIc(agreement.getCountry())
                .map(TMCountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + agreement.getCountry()));
    }

}
