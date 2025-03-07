package travel_Insurance_Test.validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.TravelCalculatePremiumValidator;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.TravelRequestValidation;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumValidatorTest {


    @InjectMocks private TravelCalculatePremiumValidator requestValidator;
    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.execute(request)).thenReturn(Optional.empty());
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.execute(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationMistake> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.execute(request)).thenReturn(Optional.of(new ValidationMistake()));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.execute(request)).thenReturn(Optional.of(new ValidationMistake()));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationMistake> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }
}
