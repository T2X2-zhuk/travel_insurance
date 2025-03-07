package travel_Insurance_Test.validator.validations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorPersonFirstName;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.ValidatorPersonLastName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonLastNameValidationTest {


    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("ksald", null,"01.10.2025","01.01.2025");
        ValidatorPersonLastName validator = new ValidatorPersonLastName();
        Optional<ValidationMistake> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Person last name");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("ksald", "","01.10.2025","01.01.2025");
        ValidatorPersonLastName validator = new ValidatorPersonLastName();
        Optional<ValidationMistake> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        Assert.assertEquals(errorOpt.get().getField(), "Person last name");
        Assert.assertEquals(errorOpt.get().getMessage(), "Must not be empty");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("ksald", "hatrr","01.10.2025","01.01.2025");
        ValidatorPersonLastName validator = new ValidatorPersonLastName();
        Optional<ValidationMistake> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}