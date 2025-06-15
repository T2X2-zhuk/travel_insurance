package travel_insurance.restTest.v1.risk_travel_medical;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import travel_insurance.restTest.v1.TravelCalculatePremiumControllerV1TestCase;

public class TravelMedicalRiskV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_medical";

    @Test
    @DisplayName("Success case with TRAVEL_MEDICAL risk only")
    public void executeTestCase1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_1", true);
    }

    // TODO Add tests for medicalRiskLimitLevel field

}
