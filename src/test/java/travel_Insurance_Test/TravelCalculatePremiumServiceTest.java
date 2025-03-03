package travel_Insurance_Test;


import liquibase.pro.packaged.W;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.TravelCalculatePremiumResponse;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.DateTimeService;
import travel_insurance.core.service.TravelCalculatePremiumServiceImpl;
import travel_insurance.core.service.validatorMistakes.TravelCalculatePremiumValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceTest {

   @Mock private DateTimeService dateTimeService;
    @Mock private TravelCalculatePremiumValidator validator;

   @InjectMocks private TravelCalculatePremiumServiceImpl service;

   @Test
    public void shouldPopulatePersonFirstNameIsNotValid() {
       TravelCalculatePremiumRequest request = createRequestWithAllFields(null,"Gareb","01.01.2023","01.01.2023");

        var validationError = new ValidationMistake("field", "message");

       when(validator.getAllMistakes(request)).thenReturn(List.of(validationError));
       var response = service.calculatePremium(request);
       assertTrue(response.hasErrors());
    }

    @Test
    public void shouldPopulatePersonLastName() {
        TravelCalculatePremiumRequest request = createRequestWithAllFields("gsrreea",null,"01.01.2023","01.01.2023");;

        var validationError = new ValidationMistake("field", "message");

        when(validator.getAllMistakes(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {

        TravelCalculatePremiumRequest request = createRequestWithAllFields(null,null,"01.01.2023","10.01.2023");;

        var validationError = new ValidationMistake("field", "message");
        var validationError2 = new ValidationMistake("field", "message");
        when(validator.getAllMistakes(request)).thenReturn(List.of(validationError,validationError2));
        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields(String person1Name, String person2Name, String dateFrom,String dateTo)  {
        Date formatFrom = null;
        try {
            formatFrom = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date formatTo = null;
        try {
            formatTo = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new TravelCalculatePremiumRequest(person1Name,person2Name,formatFrom,formatTo);
    }

}
