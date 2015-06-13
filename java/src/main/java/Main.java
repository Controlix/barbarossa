import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String args[]) {
		new Main().run(5);
	}

	private final Client client = new Client(new Server());
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();
	private final Spinner spinner = new Spinner();

	public void run(final int seconds) {
		spinner.start();

		stopwatch.start();
		while (stopwatch.elapsed(TimeUnit.SECONDS) < seconds) {
			for (int i=0; i<2000000; i++) {
				client.send("hello");
			}
			spinner.spin();
		}
		stopwatch.stop();
		System.out.println("\nTime elapsed = " + stopwatch.toString());
	}
}
