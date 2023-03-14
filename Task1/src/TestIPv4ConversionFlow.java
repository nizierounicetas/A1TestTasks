import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class TestIPv4ConversionFlow implements AutoCloseable {
    private final BufferedReader bufferedReader;

    public TestIPv4ConversionFlow() {
        this.bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    public void entrypoint() {
        while (true) {
            System.out.print("""
                    Choose the action:
                    1) test generated IPv4 conversion;
                    2) exit
                    input>""");

            try {
                switch (Integer.parseInt(bufferedReader.readLine().trim())) {
                    case 1 -> testConversion();
                    case 2 -> { return; }
                    default ->  System.err.println("Wrong input");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Wrong input: " + ex.getMessage());
            } catch (IOException | IPv4Converter.IPv4ConverterException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private static void testConversion() throws IPv4Converter.IPv4ConverterException {
        String generatedIpv4Str = IPv4Generator.generateIPv4String();
        int convertedIpv4Num = IPv4Converter.convertStringToInt32(generatedIpv4Str);
        String convertedIpv4Str = IPv4Converter.convertInt32ToString(convertedIpv4Num);

        System.out.printf("""
                ======[ TEST ]==========================
                Generated IPv4:           %s
                Converted IPv4 to Int32:  %d
                Converted Int32 to IPv4:  %s
                Test passed:              %b
                ========================================
                """, generatedIpv4Str, convertedIpv4Num, convertedIpv4Str, generatedIpv4Str.equals(convertedIpv4Str));
    }
}
