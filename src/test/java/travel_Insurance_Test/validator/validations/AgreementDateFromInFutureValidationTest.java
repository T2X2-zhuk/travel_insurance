package travel_Insurance_Test.validator.validations;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.DateTimeService;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidateDateFromInFuture;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


class AgreementDateFromInFutureValidationTest {


    @Test
    public void shouldReturnErrorWhenAgreementDateFromInThePast() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.05.2023","01.05.2025");
        ValidateDateFromInFuture fromInFuture = new ValidateDateFromInFuture();
        Optional<ValidationMistake> errorOpt = fromInFuture.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Agreement Date From");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must be in the future!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromInFuture() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","01.05.2025","01.09.2025");
        ValidateDateFromInFuture fromInFuture = new ValidateDateFromInFuture();
        Optional<ValidationMistake> errorOpt = fromInFuture.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}