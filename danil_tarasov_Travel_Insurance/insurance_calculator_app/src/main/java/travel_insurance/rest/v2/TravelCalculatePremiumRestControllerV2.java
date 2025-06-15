package travel_insurance.rest.v2;

import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel_insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import travel_insurance.core.api.command.TravelCalculatePremiumCoreResult;
import travel_insurance.core.services.TravelCalculatePremiumService;
import travel_insurance.dto.v2.DtoV2Converter;
import travel_insurance.dto.v2.TravelCalculatePremiumRequestV2;
import travel_insurance.dto.v2.TravelCalculatePremiumResponseV2;
import travel_insurance.rest.common.TravelRestRequestExecutionTimeLogger;

@RestController
@RequestMapping("/insurance/travel/api/v2")
public class TravelCalculatePremiumRestControllerV2 {

	@Autowired private TravelCalculatePremiumRequestLoggerV2 requestLogger;
	@Autowired private TravelCalculatePremiumResponseLoggerV2 responseLogger;
	@Autowired private TravelRestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private DtoV2Converter dtoV2Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV2 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV2 processRequest(TravelCalculatePremiumRequestV2 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}