package A1.data.migrations;

import A1.data.CsvModelsReader;
import A1.data.models.csvModels.RawCsvLogin;
import A1.data.models.csvModels.RawCsvPosting;
import A1.data.models.dbEntities.*;
import com.opencsv.bean.CsvToBeanFilter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CompanyCsvToSqlDbMigrator {
    public static final String LOGINS_SCV_PATH = "CSVs\\logins.csv";
    public static final String POSTINGS_SCV_PATH = "CSVs\\postings.csv";
    public static final String DATE_FORMAT = "dd.mm.yyyy";
    public static void migrate(EntityManager manager) throws IOException, MigrationException {


        List<RawCsvLogin> rawLogins = CsvModelsReader.readInstances(LOGINS_SCV_PATH, RawCsvLogin.class,
                ',',  null);
        List<RawCsvPosting> rawPostings = CsvModelsReader.readInstances(POSTINGS_SCV_PATH, RawCsvPosting.class,
                ';', new CsvToBeanFilter() {
                    @Override
                    public boolean allowLine(String[] row) {
                        return row[0] != null && row[0].trim().length() != 0;
                    }
                });

        // rawPostings.forEach(p -> p.setIsAuthorized(rawLogins));
        try {
            for(var p : rawPostings)
                p.setIsAuthorized(rawLogins);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }

        ArrayList<Account> accounts = parseLogins(rawLogins);
        ArrayList<PostedProduct> postedProducts = parsePostings(rawPostings);

        EntityTransaction transaction = manager.getTransaction();
        try {

            transaction.begin();
            for (var account : accounts) {
                manager.persist(account);
            }

            for (var postedProduct : postedProducts)
                manager.persist(postedProduct);

            transaction.commit();
        }
        catch (Exception ex)
        {
            if (transaction.isActive())
                transaction.rollback();
            throw new MigrationException(ex);
        }
        finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
    }

    private static ArrayList<Account> parseLogins(List<RawCsvLogin> rawCsvLogins) {

        HashMap<String, Job> jobsMap = new HashMap<>();
        HashMap<String, Application> appsMap = new HashMap<>();
        HashMap<String, Department> departmentsMap = new HashMap<>();
        for(RawCsvLogin login : rawCsvLogins)
        {
            String currentJobTitle = login.getJobTitle().trim(),
                    currentAppTitle = login.getApplication().trim(),
                    currentDepartmentTitle = login.getDepartment().trim();

            if (!jobsMap.containsKey(currentJobTitle))
            {
                var job = new Job();
                job.setTitle(currentJobTitle);

                jobsMap.put(currentJobTitle, job);
            }

            if (!appsMap.containsKey(currentAppTitle))
            {
                var app = new Application();
                app.setTitle(currentAppTitle);

                appsMap.put(currentAppTitle, app);
            }

            if (!departmentsMap.containsKey(currentDepartmentTitle))
            {
                var department = new Department();
                department.setTitle(currentDepartmentTitle);

                departmentsMap.put(currentDepartmentTitle, department);
            }
        }

        ArrayList<Account> accounts = new ArrayList<>();

        for (RawCsvLogin l : rawCsvLogins) {
            try {
                Account account = new Account();
                account.setIsActive(Boolean.parseBoolean(l.getIsActive().trim()));
                account.setName(l.getAppAccountName().trim());
                account.setJob(jobsMap.get(l.getJobTitle().trim()));
                account.setApplication(appsMap.get(l.getApplication().trim()));
                account.setDepartment(departmentsMap.get(l.getDepartment().trim()));

                accounts.add(account);
            }
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }

        return accounts;
    }

    private static ArrayList<PostedProduct> parsePostings(List<RawCsvPosting> rawPostings) {

        HashMap<String, Product> productsMap = new HashMap<>();
        for(RawCsvPosting p : rawPostings)
        {
            String currentDescription = p.getMaterialDescription().trim();
            if (!productsMap.containsKey(currentDescription))
            {
                var product = new Product();
                product.setDescription(currentDescription);

                productsMap.put(currentDescription, product);
            }
        }

        HashMap<Long, Posting> postingsMap = new HashMap<>();
        for(RawCsvPosting rp : rawPostings)
        {
            try
            {
                long currentNumber = Long.parseLong(rp.getMatDoc().trim());
                if (!postingsMap.containsKey(currentNumber))
                {
                    Posting posting = new Posting();
                    posting.setNumber(currentNumber);
                    posting.setDocDate(new SimpleDateFormat(DATE_FORMAT).parse(rp.getDocDate().trim()));
                    posting.setPostingDate(new SimpleDateFormat(DATE_FORMAT).parse(rp.getPostingDate().trim()));
                    posting.setAccountName(rp.getUserName().trim());
                    posting.setIsAuthorized(rp.getIsAuthorized());

                    postingsMap.put(currentNumber, posting);
                }
            }
            catch(Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }

        ArrayList<PostedProduct> postedProducts = new ArrayList<>();
        for (RawCsvPosting rp : rawPostings)
        {
            try {
                PostedProduct postedProduct = new PostedProduct();
                postedProduct.setItem(Integer.parseInt(rp.getItem().trim()));
                postedProduct.setCurrency(rp.getCurrency().trim());
                postedProduct.setQuantity(Integer.parseInt(rp.getQuantity().trim()));
                postedProduct.setAmountLc(NumberFormat.getInstance(Locale.getDefault()).parse(rp.getAmountLc().trim())
                        .doubleValue());
                postedProduct.setUnit(rp.getBun().trim());
                postedProduct.setCurrency(rp.getCurrency().trim());
                postedProduct.setProduct(productsMap.get(rp.getMaterialDescription().trim()));
                postedProduct.setPosting(postingsMap.get(Long.parseLong(rp.getMatDoc().trim())));

                postedProducts.add(postedProduct);
            }
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }

        return postedProducts;
    }

    public static class MigrationException extends Exception
    {
        MigrationException(Throwable cause) {
            super("Migration failed, probably, it's been done already",cause);
        }
    }
}
