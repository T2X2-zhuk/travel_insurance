package travel_Insurance_Test.validator.validations;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidateDateFromLessThenDateTo;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DateFromLessThenDateToValidationTest {

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.10.2025","01.01.2025");
        ValidateDateFromLessThenDateTo validatorAgreementDateTo = new ValidateDateFromLessThenDateTo();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateTo.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Agreement Date From");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must be less then agreementDateTo!");
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.01.2025","01.01.2025");
        ValidateDateFromLessThenDateTo validatorAgreementDateTo = new ValidateDateFromLessThenDateTo();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateTo.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Agreement Date From");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must be less then agreementDateTo!");
    }

    @Test
    public void shouldNotReturnErrorWhenDateFromIsLessDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.02.2025","01.10.2025");
        ValidateDateFromLessThenDateTo validatorAgreementDateTo = new ValidateDateFromLessThenDateTo();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateTo.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}