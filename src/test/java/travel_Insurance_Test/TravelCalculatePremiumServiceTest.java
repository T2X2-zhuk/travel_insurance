package travel_Insurance_Test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.TravelCalculatePremiumServiceImpl;
import travel_insurance.core.service.TravelPremiumUnderwriting;
import travel_insurance.core.service.validatorMistakes.TravelCalculatePremiumValidator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceTest {

   @Mock private TravelPremiumUnderwriting underwriting;
    @Mock private TravelCalculatePremiumValidator validator;

   @InjectMocks private TravelCalculatePremiumServiceImpl service;

   @Test
    public void shouldPopulatePersonFirstNameIsNotValid() {
       TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null,"Gareb","05.10.2050","01.01.2100");

        var validationError = new ValidationMistake("field", "message");

       when(validator.validate(request)).thenReturn(List.of(validationError));
       var response = service.calculatePremium(request);
       assertTrue(response.hasErrors());
    }

    @Test
    public void shouldPopulatePersonLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("gsrreea",null,"05.10.2050","01.01.2100");;

        var validationError = new ValidationMistake("field", "message");

        when(validator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null,null,"12.01.2090","10.01.2090");;

        var validationError = new ValidationMistake("field", "message");
        var validationError2 = new ValidationMistake("field", "message");
        var validationError3 = new ValidationMistake("field", "message");
        when(validator.validate(request)).thenReturn(List.of(validationError,validationError2,validationError3));
        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

}
