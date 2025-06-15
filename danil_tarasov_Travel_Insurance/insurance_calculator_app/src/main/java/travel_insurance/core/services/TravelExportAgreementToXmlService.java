package travel_insurance.core.services;


import travel_insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import travel_insurance.core.api.command.TravelExportAgreementToXmlCoreResult;

public interface TravelExportAgreementToXmlService {

    TravelExportAgreementToXmlCoreResult export(TravelExportAgreementToXmlCoreCommand command);

}
