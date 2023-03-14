import java.util.Arrays;
import java.util.regex.Pattern;

public class IPv4Converter {
    private static final String IPV4_REGEX = "^(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$";
    public static boolean validateStringIpv4(String ipv4Str)
    {
        return Pattern.compile(IPV4_REGEX).matcher(ipv4Str).find();
    }

    public static int convertStringToInt32(String ipv4Str) throws IPv4ConverterException
    {
        if (!validateStringIpv4(ipv4Str))
            throw new IPv4ConverterException(ipv4Str);

        int[] octets = Arrays.stream(ipv4Str.split("\\.")).mapToInt(Integer::parseInt).toArray();
        return (octets[0] << 24) + (octets[1] << 16) + (octets[2] << 8) + octets[3];
    }

    public static String convertInt32ToString(int ipv4Num)
    {
        return String.format("%d.%d.%d.%d", (ipv4Num >> 24) & 255, (ipv4Num >> 16) & 255, (ipv4Num >> 8) & 255, ipv4Num & 255);
    }

    public static class IPv4ConverterException extends Exception
    {
        public IPv4ConverterException(String ipv4Str) {
            super(String.format("String is not valid for IPv4 format: %s", ipv4Str));
        }
    }
}
