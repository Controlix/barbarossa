import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

public class Main {

	private static long CALLS = 1000 * 1000;

	public static void main(String args[]) {
		new Main().run(2);
	}

	private final Client client = new Client(new Server());
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();

	public void run(final int seconds) {
		stopwatch.start();
		while (stopwatch.elapsed(TimeUnit.SECONDS) < seconds) {
			for (int i=0; i<CALLS; i++) {
				client.send("hello");
			}
		}
		stopwatch.stop();
		System.out.println("Time elapsed = " + stopwatch.toString());
		System.out.format("Calls made = %,10d%n", client.count());
	}
}
