package travel_insurance.core.blacklist;


import travel_insurance.core.api.dto.PersonDTO;

public interface BlackListPersonCheckService {

    boolean isPersonBlacklisted(PersonDTO personDTO);

}
