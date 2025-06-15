package travel_insurance.core.underwriting.calculators.medical;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.domain.TMCountryDefaultDayRate;
import travel_insurance.core.repositories.TMCountryDefaultDayRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TMCountryDefaultDayRateCalculatorTest {

    @Mock private TMCountryDefaultDayRateRepository TMCountryDefaultDayRateRepository;

    @InjectMocks
    private CountryDefaultDayRateCalculator calculator;

    private AgreementDTO agreement;

    @BeforeEach
    void setUp() {
        agreement = new AgreementDTO();
        agreement.setCountry("US");
    }

    @Test
    void shouldCalculateDayRateWhenCountryDayRateExists() {
        BigDecimal expectedDayRate = BigDecimal.valueOf(10.0);
        TMCountryDefaultDayRate TMCountryDefaultDayRate = mock(TMCountryDefaultDayRate.class);
        when(TMCountryDefaultDayRate.getDefaultDayRate()).thenReturn(expectedDayRate);
        when(TMCountryDefaultDayRateRepository.findByCountryIc(agreement.getCountry()))
                .thenReturn(Optional.of(TMCountryDefaultDayRate));
        BigDecimal result = calculator.calculate(agreement);
        assertEquals(expectedDayRate, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        when(TMCountryDefaultDayRateRepository.findByCountryIc(agreement.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(agreement));
        assertEquals("Country day rate not found by country id = " + agreement.getCountry(), exception.getMessage());
    }

}