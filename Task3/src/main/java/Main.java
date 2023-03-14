import CsvModels.RawCsvLogin;
import CsvModels.RawCsvPosting;
import com.opencsv.bean.CsvToBeanFilter;

import java.util.List;

public class Main {
    public static final String LOGINS_SCV_PATH = "CSVs\\logins.csv";
    public static final String POSTINGS_SCV_PATH = "CSVs\\postings.csv";
    public static void main(String[] args) {
        try
        {
            ScvController<RawCsvPosting> postingScvController = new ScvController<>();
            ScvController<RawCsvLogin> loginScvController = new ScvController<>();

            List<RawCsvLogin> logins = loginScvController.readInstances(LOGINS_SCV_PATH, RawCsvLogin.class, ',',  null);
          /*  List<RawCsvPosting> postings = postingScvController.readInstances(POSTINGS_SCV_PATH, RawCsvPosting.class, ';', new CsvToBeanFilter() {
                @Override
                public boolean allowLine(String[] row) {
                   return row[0] != null && row[0].trim().length() != 0;
                }
            });*/

            logins.forEach(System.out::println);
        //    postings.forEach(System.out::println);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}