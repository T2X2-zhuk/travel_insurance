package travel_insurance.loadtesting;

import com.google.common.base.Stopwatch;
import travel_insurance.restTest.common.JsonFileReader;


class V2Call extends CommonCall implements Runnable {

    private static final String CALCULATE_PREMIUM_V2_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v2/";
    private JsonFileReader jsonFileReader = new JsonFileReader();
    private LoadTestingStatistic statistic;

    public V2Call(LoadTestingStatistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public void run() {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/v2/risks/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION/request.json");
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/v2/risks/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION/response.json");
        Stopwatch stopwatch = Stopwatch.createStarted();
        executeRestCallAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V2_LOCAL_URL);
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        System.out.println("Request processing time (ms): " + elapsedMillis);
    }

}
