public class Main {
    public static void main(String[] args) {
       try(TestSequenceFlow flow = new TestSequenceFlow()) {
           flow.entrypoint();
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
    }
}