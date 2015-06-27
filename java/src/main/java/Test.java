package marc;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.servlet.ServletContext;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

@Path("test")
public class Test {

	private int timeFrame = 10;
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();

	@Context private ServletContext ctx;

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
	@Path("http/{port:(\\d{4})}/{root:(\\w*)}")
	@Produces(MediaType.TEXT_PLAIN)
	public String http(@PathParam("port") final int port, @PathParam("root") String root) {
		if (root.isEmpty()) root = ctx.getContextPath().replaceFirst("/", "");
		return test(new HttpClient(Integer.valueOf(port), root));
	}
}
