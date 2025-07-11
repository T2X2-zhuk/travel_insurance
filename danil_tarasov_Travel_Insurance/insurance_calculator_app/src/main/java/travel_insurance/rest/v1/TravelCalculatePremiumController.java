package travel_insurance.rest.v1;

import com.google.common.base.Stopwatch;
import travel_insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import travel_insurance.core.api.command.TravelCalculatePremiumCoreResult;
import travel_insurance.core.services.TravelCalculatePremiumService;
import travel_insurance.dto.v1.DtoV1Converter;
import travel_insurance.dto.v1.TravelCalculatePremiumRequestV1;
import travel_insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel_insurance.rest.common.TravelRestRequestExecutionTimeLogger;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumRequestLogger requestLogger;
	@Autowired private TravelCalculatePremiumResponseLogger responseLogger;
	@Autowired private TravelRestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private DtoV1Converter dtoV1Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV1Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV1 response = dtoV1Converter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}
}