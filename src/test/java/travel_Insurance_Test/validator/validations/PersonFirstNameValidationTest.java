package travel_Insurance_Test.validator.validations;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidateDateFromLessThenDateTo;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorPersonFirstName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonFirstNameValidationTest {


    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null , "Govna","01.10.2025","01.01.2025");
        ValidatorPersonFirstName validator = new ValidatorPersonFirstName();
        Optional<ValidationMistake> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Person first name");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "Govna","01.10.2025","01.01.2025");
        ValidatorPersonFirstName validator = new ValidatorPersonFirstName();
        Optional<ValidationMistake> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Person first name");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must not be empty");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Kalon", "Govna","01.10.2025","01.01.2025");
        ValidatorPersonFirstName validator = new ValidatorPersonFirstName();
        Optional<ValidationMistake> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}