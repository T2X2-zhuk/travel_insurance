package travel_Insurance_Test;

import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.service.DateTimeService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldDaysBetweenBeZero() {
        String date1 = "01.01.2023";
        String date2 = "01.01.2023";
        var request = new TravelCalculatePremiumRequest("John","SILVER",date1,date2);
        var daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo());
        assertEquals(daysBetween, 0L);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        String date1 = "01.01.2023";
        String date2 = "10.01.2023";
        var request = new TravelCalculatePremiumRequest("John","SILVER",date1,date2);
        var daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo());
        assertEquals(daysBetween, 9L);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        String date1 = "10.01.2023";
        String date2 = "01.01.2023";
        var request = new TravelCalculatePremiumRequest("John","SILVER",date1,date2);
        var daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo());
        assertEquals(daysBetween, -9L);
    }
}
