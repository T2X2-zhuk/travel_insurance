package blacklist.core.services;

import blacklist.core.api.BlackListedPersonCoreCommand;
import blacklist.core.api.BlackListedPersonCoreResult;
import blacklist.core.api.BlackListedPersonDTO;
import blacklist.core.api.ValidationErrorDTO;
import blacklist.core.repositories.BlackListedPersonEntityRepository;
import blacklist.core.validations.BlackListedPersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
class BlackListedPersonServiceImpl implements BlackListedPersonService {

    @Autowired private BlackListedPersonValidator personValidator;

    @Autowired private BlackListedPersonEntityRepository repository;

    @Override
    public BlackListedPersonCoreResult check(BlackListedPersonCoreCommand command) {
        List<ValidationErrorDTO> errors = personValidator.validate(command.getPersonDTO());
        if (errors.isEmpty()) {
            BlackListedPersonDTO personDTO = command.getPersonDTO();
            boolean isBlacklisted = repository.findBy(
                    personDTO.getPersonFirstName(),
                    personDTO.getPersonLastName(),
                    personDTO.getPersonCode()
            ).isPresent();
            personDTO.setBlackListed(isBlacklisted);
            return new BlackListedPersonCoreResult(personDTO);
        } else {
            return new BlackListedPersonCoreResult(errors);
        }
    }

}
