package travel_Insurance_Test.validator.validations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidateDateFromInFuture;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorAgreementDateFrom;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AgreementDateFromValidationTest {


    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna",null,"01.05.2025");
        ValidatorAgreementDateFrom validatorAgreementDateFrom = new ValidatorAgreementDateFrom();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateFrom.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Agreement Date From");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must not be empty");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromIsPresent() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.05.2025","01.09.2025");
        ValidatorAgreementDateFrom validatorAgreementDateFrom = new ValidatorAgreementDateFrom();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateFrom.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}