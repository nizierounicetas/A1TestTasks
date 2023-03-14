import java.util.Random;
import java.util.random.RandomGenerator;

public class IPv4Generator {
    public static String generateIPv4String()
    {
        return String.format("%d.%d.%d.%d", generateOctet(), generateOctet(), generateOctet(), generateOctet());
    }

    private static int generateOctet()
    {
        return new Random().nextInt( 256);
    }
}
