package A1.service;

import A1.data.models.dbEntities.Posting;

import java.util.Date;
import java.util.List;

public interface PostingService {
    List<Posting> getAll();
    List<Posting> getByIsAuthorized(boolean isAuthorized);
    List<Posting> getByPeriod(Date startDate, String period);
    List<Posting> getByPeriodAndIsAuthorized(Date startDate, String period, boolean isAuthorized);
}

