package A1.data;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvModelsReader {
    public static <T> List<T> readInstances(String fileName, Class<T> type, char separator, CsvToBeanFilter filter) throws IOException {
        try(FileReader reader = new FileReader(fileName)) {
            List<T> rows = new CsvToBeanBuilder(reader)
                    .withType(type).withFilter(filter)
                    .withSeparator(separator)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();

            return rows;
        }
    }
}
