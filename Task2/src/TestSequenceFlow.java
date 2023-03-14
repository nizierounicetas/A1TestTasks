import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSequenceFlow implements AutoCloseable {
    private final BufferedReader bufferedReader;

    private Sequence sequence;
    public TestSequenceFlow()
    {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        sequence = new Sequence();
    }

    public void entrypoint() throws IOException {
        while (true)
        {
            System.out.print("Enter n(q to quit): ");
            try {
                String userInput = bufferedReader.readLine().trim();
                if (userInput.equals("q"))
                    return;
                else {
                    int n = Integer.parseInt(userInput);

                    if (n <= 0)
                        throw new NumberFormatException("n should be positive");

                    testSequence(n);
                }

            }  catch (NumberFormatException ex)
            {
                System.err.println("Wrong input: " + ex.getMessage());
            }
        }
    }

    private void testSequence(int n)
    {
        for(int i = 0; i < n; ++i)
            sequence.next();

        System.out.println(sequence);
        sequence.reset();
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }
}
