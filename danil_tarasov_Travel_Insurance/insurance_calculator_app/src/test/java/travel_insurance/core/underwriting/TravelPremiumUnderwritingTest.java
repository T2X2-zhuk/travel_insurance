package travel_insurance.core.underwriting;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @Mock private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    void shouldCalculateTotalPremiumAsSumOfRiskPremiums() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        List<RiskDTO> risks = List.of(
                new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE),
                new RiskDTO("TRAVEL_EVACUATION", BigDecimal.ONE)
        );
        when(selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person)).thenReturn(risks);
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwriting.calculatePremium(agreement, person);
        assertEquals(premiumCalculationResult.getTotalPremium(), new BigDecimal(2));
    }

}
