package A1.service;

import A1.data.dbManagement.EntityManagerFactorySingleton;
import A1.data.models.dbEntities.Posting;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class PostingServiceImpl implements PostingService {

    private EntityManagerFactory managerFactory;

    public enum Period {

        Day("day"),
        Month("month"),
        Quarter("quarter"),
        Year("year");
        private String title;

        Period(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public PostingServiceImpl() {

        this.managerFactory = EntityManagerFactorySingleton.getInstance();
    }
    @Override
    public List<Posting> getAll() {
        EntityManager manager = null;
        List<Posting> postings = null;
        try {
            manager =
                    managerFactory.createEntityManager();
            Query query = manager.createNamedQuery("Posting.selectAll");

            postings = query.getResultList();
        }
        catch(Exception ex)
        {
            return null;
        }
        finally {
            if (manager != null)
                manager.close();
        }

        return postings;
    }

    @Override
    public List<Posting> getByIsAuthorized(boolean isAuthorized)
    {
        EntityManager manager = null;
        List<Posting> postings = null;
        try {
            manager =
                    managerFactory.createEntityManager();
            Query query = manager.createNamedQuery("Posting.selectByIsAuthorized");
            query.setParameter("isAuthorized", isAuthorized);

            postings = query.getResultList();
        }
        catch(Exception ex)
        {
            return null;
        }
        finally {
            if (manager != null)
                manager.close();
        }

        return postings;
    }


    @Override
    public List<Posting> getByPeriod(Date startDate, String periodStr) {
        EntityManager manager = null;
        List<Posting> postings = null;
        Period period;
        try
        {
            period = Period.valueOf(periodStr);
            manager =
                    managerFactory.createEntityManager();
            Query query = manager.createNamedQuery("Posting.selectByPeriod");
            query.setParameter("startDate", startDate);

            switch (period){
                case Day -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                case Month -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                case Quarter -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                case Year -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            postings = query.getResultList();
        }
        catch(Exception ex)
        {
            return null;
        }
        finally {
            if (manager != null)
                manager.close();
        }

        return postings;
    }

    @Override
    public List<Posting> getByPeriodAndIsAuthorized(Date startDate, String periodStr, boolean isAuthorized) {
        EntityManager manager = null;
        List<Posting> postings = null;
        Period period;
        try
        {
            period = Period.valueOf(periodStr);
            manager =
                    managerFactory.createEntityManager();
            Query query = manager.createNamedQuery("Posting.selectByPeriodAndIsAuthorized");
            query.setParameter("startDate", startDate);
            query.setParameter("isAuthorized", isAuthorized);

            switch (period){
                case Day -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                case Month -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                case Quarter -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                case Year -> query.setParameter("endDate", Date.from(startDate.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            postings = query.getResultList();
        }
        catch(Exception ex)
        {
            return null;
        }
        finally {
            if (manager != null)
                manager.close();
        }

        return postings;
    }
}

