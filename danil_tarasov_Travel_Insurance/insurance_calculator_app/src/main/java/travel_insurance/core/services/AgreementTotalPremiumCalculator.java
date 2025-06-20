package travel_insurance.core.services;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.Collection;

@Component
class AgreementTotalPremiumCalculator {

    BigDecimal calculate(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
