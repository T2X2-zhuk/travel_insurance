package travel_Insurance_Test.validator;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.TravelCalculatePremiumValidator;

import java.util.List;

public class TravelCalculatePremiumValidatorTest {


    @Test
    public void test1NotValidPersonFirstName(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null,null,null,null);
        TravelCalculatePremiumValidator validator = new TravelCalculatePremiumValidator();
        List<ValidationMistake> mistakes = validator.getAllMistakes(request);
        Assertions.assertEquals(mistakes.get(0).getField(), "Person first name");
        Assertions.assertEquals(mistakes.get(0).getMessage(), "Must not be empty");
        Assertions.assertEquals(mistakes.get(1).getField(), "Person last name");
        Assertions.assertEquals(mistakes.get(1).getMessage(), "Must not be empty");
        Assertions.assertEquals(mistakes.get(2).getField(), "Agreement Date From");
        Assertions.assertEquals(mistakes.get(2).getMessage(), "Must not be empty");
        Assertions.assertEquals(mistakes.get(3).getField(), "Agreement Date To");
        Assertions.assertEquals(mistakes.get(3).getMessage(), "Must not be empty");
    }
    @Test
    public void dateFromLessThenDateToMistake(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Haper","Jocker","10.12.2023","01.12.2023");
        TravelCalculatePremiumValidator validator = new TravelCalculatePremiumValidator();
        List<ValidationMistake> mistakes = validator.getAllMistakes(request);
        Assertions.assertEquals(mistakes.get(0).getField(), "Agreement Date From");
        Assertions.assertEquals(mistakes.get(0).getMessage(), "Must be less then agreementDateTo!");
        Assert.assertTrue(!mistakes.isEmpty());
    }
    @Test
    public void NotMistakes(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Haper","Jocker","01.12.2023","03.12.2023");
        TravelCalculatePremiumValidator validator = new TravelCalculatePremiumValidator();
        List<ValidationMistake> mistakes = validator.getAllMistakes(request);
        Assert.assertTrue(mistakes.isEmpty());
    }

}
