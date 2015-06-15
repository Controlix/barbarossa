package marc;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

@Path("test")
public class Test {

	private int timeFrame = 10;
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();

	@GET
	@Path("frame/{time}")
	@Produces(MediaType.TEXT_PLAIN)
	public String setTimeFrame(@PathParam("time") final int time) {
		this.timeFrame = time;
		return "OK";
	}

	@GET
	@Path("plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String plainJava() {
		return test(new JavaClient(new JavaServer()));
	}

	private String test(final Client client) {
		int loop = client.loop();
		stopwatch.start();
		while (stopwatch.elapsed(TimeUnit.SECONDS) < timeFrame) {
			for (int i=0; i<loop; i++) {
				client.send("hello");
			}
		}
		stopwatch.stop();

		return String.format("Time elapsed = %s%nCalls made = %,10d%n", stopwatch.toString(), client.count());
	}

	@GET
	@Path("http")
	@Produces(MediaType.TEXT_PLAIN)
	public String inJvmHttp() {
		return test(new HttpClient());
	}

	@GET
	@Path("remote")
	@Produces(MediaType.TEXT_PLAIN)
	public String externalJvmHttp() {
		return test(new HttpClient(8090));
	}
}
