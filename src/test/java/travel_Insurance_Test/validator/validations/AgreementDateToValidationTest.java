package travel_Insurance_Test.validator.validations;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorAgreementDateFrom;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorAgreementDateTo;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AgreementDateToValidationTest {


    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.12.2025",null);
        ValidatorAgreementDateTo validatorAgreementDateTo = new ValidatorAgreementDateTo();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateTo.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Agreement Date To");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must not be empty");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToIsPresent() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.12.2025","01.05.2026");
        ValidatorAgreementDateTo validatorAgreementDateTo = new ValidatorAgreementDateTo();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateTo.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}