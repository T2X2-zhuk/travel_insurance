package travel_insurance.rest.internal;

import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel_insurance.core.api.command.TravelGetAgreementCoreCommand;
import travel_insurance.core.api.command.TravelGetAgreementCoreResult;
import travel_insurance.core.services.TravelGetAgreementService;
import travel_insurance.dto.internal.GetAgreementDtoConverter;
import travel_insurance.dto.internal.TravelGetAgreementResponse;
import travel_insurance.rest.common.TravelRestRequestExecutionTimeLogger;

import java.util.Date;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementRestController {

	@Autowired private TravelGetAgreementRequestLogger requestLogger;
	@Autowired private TravelGetAgreementResponseLogger responseLogger;
	@Autowired private TravelRestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelGetAgreementService getAgreementService;
	@Autowired private GetAgreementDtoConverter dtoConverter;

	@GetMapping(path = "/{uuid}",
			produces = "application/json")
	public TravelGetAgreementResponse getAgreement(@PathVariable("uuid") String uuid) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelGetAgreementResponse response = processRequest(uuid);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelGetAgreementResponse processRequest(String uuid) {
		requestLogger.log(uuid);
		TravelGetAgreementCoreCommand coreCommand = dtoConverter.buildCoreCommand(uuid);
		TravelGetAgreementCoreResult coreResult = getAgreementService.getAgreement(coreCommand);
		TravelGetAgreementResponse response = dtoConverter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}