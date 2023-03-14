public class Main {
    public static void main(String[] args) {
        try(TestIPv4ConversionFlow flow = new TestIPv4ConversionFlow()) {
            flow.entrypoint();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}