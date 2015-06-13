public class Spin
{
    public static void main(String[] args) throws InterruptedException
    {
        String[] phases = {"|", "/", "-", "\\"};
         
        System.out.printf("Spinning... |");
         
        while (true)
        {
            for (String phase : phases)
            {
                System.out.printf("\b"+phase);
                Thread.sleep(100);
            }
        }
    }
}
