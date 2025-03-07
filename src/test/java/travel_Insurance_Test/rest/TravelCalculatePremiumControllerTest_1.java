package travel_Insurance_Test.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import travel_insurance.InsuranceApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InsuranceApplication.class)
@AutoConfigureMockMvc

public class TravelCalculatePremiumControllerTest_1 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    public void successRequest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }

   /* @Test
    public void firstNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_firstName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_firstName_not_provided.json"
        );
    }

    @Test
    public void lastNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_lastName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_lastName_not_provided.json"
        );
    }

    @Test
    public void agreementDateFromNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    public void agreementDateToNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }*/


    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }
}
