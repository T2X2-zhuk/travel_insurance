package travel_Insurance_Test.validator.validations;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidateDateToInFuture;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorAgreementDateFrom;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorAgreementDateTo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgreementDateToInFutureValidationTest {


    @Test
    public void shouldReturnErrorWhenAgreementDateToInThePast() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","05.05.2025","12.05.2024");
        ValidateDateToInFuture validatorAgreementDateFrom = new ValidateDateToInFuture();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateFrom.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Agreement Date To");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must be in the future!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToInTheFuture() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Jopa" , "Govna","05.05.2025","12.05.2025");
        ValidateDateToInFuture validatorAgreementDateFrom = new ValidateDateToInFuture();
        Optional<ValidationMistake> errorOpt = validatorAgreementDateFrom.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}