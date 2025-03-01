package travel_Insurance_Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.service.DateTimeService;
import travel_insurance.core.service.TravelCalculatePremiumServiceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceTest {

    @Mock private DateTimeService dateTimeService;

    @InjectMocks private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
       request = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(request)).thenReturn(0L);
    }
        @org.junit.jupiter.api.Test
    public void shouldPopulatePersonFirstName() {
        assertEquals(service.calculatePremium(request).getPersonFirstName(),request.getPersonFirstName());
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulatePersonLastName() {
        assertEquals(service.calculatePremium(request).getPersonLastName(), request.getPersonLastName());
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulateAgreementDateFrom() {
        assertEquals(service.calculatePremium(request).getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulateAgreementDateTo() {
        assertEquals(service.calculatePremium(request).getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
        assertNotNull(service.calculatePremium(request).getAgreementPrice());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Peterson");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        return request;
    }

}
