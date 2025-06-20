package travel_insurance.core.underwriting.calculators.cancellation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.domain.TCTravelCostCoefficient;
import travel_insurance.core.repositories.TCTravelCostCoefficientRepository;

import java.math.BigDecimal;

@Component
class TCTravelCostCoefficientCalculator {

    @Autowired private TCTravelCostCoefficientRepository TCTravelCostCoefficientRepository;

    BigDecimal calculate(PersonDTO person) {
        return TCTravelCostCoefficientRepository.findCoefficient(person.getTravelCost())
                .map(TCTravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + person.getTravelCost()));
    }

}
