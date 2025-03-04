package travel_insurance.core.service;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.IContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class DateTimeService {

    public long getDaysBetween(String dateFromRequest, String dateToRequest) {
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(dateFromRequest);
            dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(dateToRequest);
        } catch (ParseException e) {
           throw new RuntimeException("Неправильный формат времени который вы передали . Пример правильного формата : '10.01.2023'.") ;
        }

        long diff = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public Date getDateAfterFormatting(String stringDate){
        Date date;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return date;
    }

}
