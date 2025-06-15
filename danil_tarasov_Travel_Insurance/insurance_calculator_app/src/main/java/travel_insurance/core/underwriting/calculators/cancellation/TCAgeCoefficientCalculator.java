package travel_insurance.core.underwriting.calculators.cancellation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.domain.TCAgeCoefficient;
import travel_insurance.core.repositories.TCAgeCoefficientRepository;
import travel_insurance.core.util.DateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
class TCAgeCoefficientCalculator {

    @Autowired private DateTimeUtil dateTimeUtil;
    @Autowired private TCAgeCoefficientRepository ageCoefficientRepository;

    BigDecimal calculate(PersonDTO person) {
        int age = calculateAge(person);
        return ageCoefficientRepository.findCoefficient(age)
                .map(TCAgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));    }

    private Integer calculateAge(PersonDTO person) {
        LocalDate personBirthDate = toLocalDate(person.getPersonBirthDate());
        LocalDate currentDate = toLocalDate(dateTimeUtil.getCurrentDateTime());
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
