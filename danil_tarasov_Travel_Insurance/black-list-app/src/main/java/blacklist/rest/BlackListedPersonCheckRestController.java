package blacklist.rest;

import blacklist.core.api.BlackListedPersonCoreCommand;
import blacklist.core.api.BlackListedPersonCoreResult;
import blacklist.core.services.BlackListedPersonService;
import blacklist.dto.BlackListedPersonCheckRequest;
import blacklist.dto.BlackListedPersonCheckResponse;
import com.google.common.base.Stopwatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blacklist/person/check")
public class BlackListedPersonCheckRestController {

    @Autowired private BlackListedPersonRequestLogger requestLogger;
    @Autowired private BlackListedPersonResponseLogger responseLogger;
    @Autowired private RestRequestExecutionTimeLogger executionTimeLogger;
    @Autowired private DtoConverter dtoConverter;
    @Autowired private BlackListedPersonService blackListedPersonService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public BlackListedPersonCheckResponse calculatePremium(@RequestBody BlackListedPersonCheckRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        BlackListedPersonCheckResponse response = processRequest(request);
        executionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }

    private BlackListedPersonCheckResponse processRequest(BlackListedPersonCheckRequest request) {
        requestLogger.log(request);
        BlackListedPersonCoreCommand coreCommand = dtoConverter.buildCoreCommand(request);
        BlackListedPersonCoreResult coreResult = blackListedPersonService.check(coreCommand);
        BlackListedPersonCheckResponse response = dtoConverter.buildResponse(coreResult);
        responseLogger.log(response);
        return response;
    }

}
