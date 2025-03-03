package travel_insurance.core.service;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class DateTimeService {

    public long getDaysBetween(Date dateFrom, Date dateTo) {
        long diff =dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
