package travel_Insurance_Test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.service.DateTimeService;
import travel_insurance.core.service.TravelPremiumUnderwriting;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

   @Mock private DateTimeService dateTimeService;

   @InjectMocks private TravelPremiumUnderwriting underwriting;

   @Test
   public void Test(){
       TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Harpon","Jacon","01.12.2020","03.12.2020");
       when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(2L);
       BigDecimal decimal = underwriting.calculatePremium(request);

       org.junit.Assert.assertEquals(BigDecimal.valueOf(2L),decimal);
   }

    @Test
    public void Test2(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Harpon","Jacon","10.12.2020","03.12.2020");
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(-7L);
        BigDecimal decimal = underwriting.calculatePremium(request);

        org.junit.Assert.assertEquals(BigDecimal.valueOf(-7L),decimal);
    }
}
