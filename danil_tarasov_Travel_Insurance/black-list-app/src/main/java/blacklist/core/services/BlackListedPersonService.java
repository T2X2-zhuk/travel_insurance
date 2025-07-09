package blacklist.core.services;

import blacklist.core.api.BlackListedPersonCoreCommand;
import blacklist.core.api.BlackListedPersonCoreResult;

public interface BlackListedPersonService {

    BlackListedPersonCoreResult check(BlackListedPersonCoreCommand command);

}
