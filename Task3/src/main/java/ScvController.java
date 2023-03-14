import CsvModels.RawCsvLogin;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ScvController<T> {
    public List<T> readInstances(String fileName, Class<T> type, char separator, CsvToBeanFilter filter) throws IOException, CsvException {

        List<T> rows = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(type).withFilter(filter)
                .withSeparator(separator)
                .withIgnoreEmptyLine(true)
                .build()
                .parse();

        return rows;
    }
}
