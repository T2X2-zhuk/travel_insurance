package travel_insurance.core.underwriting.calculators.medical;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public
class DayCountCalculator {

    @Autowired private DateTimeUtil dateTimeUtil;

    public BigDecimal calculate(AgreementDTO agreement) {
        var daysBetween = dateTimeUtil.getDaysBetween(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

}
