package travel_Insurance_Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.service.DateTimeService;
import travel_insurance.core.service.TravelCalculatePremiumServiceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TravelCalculatePremiumServiceTest {

    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulatePersonFirstName() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulatePersonLastName() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulateAgreementDateFrom() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @org.junit.jupiter.api.Test
    public void shouldPopulateAgreementDateTo() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertNotNull(response.getAgreementPrice());
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
