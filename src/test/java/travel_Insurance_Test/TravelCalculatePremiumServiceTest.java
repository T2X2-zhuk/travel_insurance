package travel_Insurance_Test;


import org.junit.Assert;
import org.junit.Test;
import travel_insurance.core.jpa.TravelCalculatePremiumService;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.TravelCalculatePremiumResponse;
import travel_insurance.core.service.TravelCalculatePremiumServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TravelCalculatePremiumServiceTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    public void  test1(){
        Date dateFrom = createDate("01.10.2023");
        Date dateTo = createDate("01.10.2023");

        TravelCalculatePremiumResponse response = service.calculatePremium(new TravelCalculatePremiumRequest("Jacon","lORENSO",dateFrom,dateTo));

        Assert.assertEquals(response.getAgreementPrice(),new BigDecimal(0));
    }

    @Test
    public void  test2Positive(){
        Date dateFrom = createDate("10.10.2023");
        Date dateTo = createDate("01.10.2023");

        TravelCalculatePremiumResponse response = service.calculatePremium(new TravelCalculatePremiumRequest("Jacon","lORENSO",dateFrom,dateTo));

        Assert.assertEquals(response.getAgreementPrice(),new BigDecimal(9));
    }

    @Test
    public void  test2Negative(){
        Date dateFrom = createDate("01.10.2023");
        Date dateTo = createDate("10.10.2023");

        TravelCalculatePremiumResponse response = service.calculatePremium(new TravelCalculatePremiumRequest("Jacon","lORENSO",dateFrom,dateTo));

        Assert.assertEquals(response.getAgreementPrice(),new BigDecimal(-9));
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
