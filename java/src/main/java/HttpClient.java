package marc;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class HttpClient implements Client {

	private static final int LOOP_SIZE = 100;

	private long calls = 0;
	private Invocation invocation;

	public HttpClient(String root) {
		this(8080, root);
	}

	public HttpClient(int port, String root) {
		ClientConfig config = new ClientConfig();
		javax.ws.rs.client.Client client = ClientBuilder.newClient(config);

		URI uri = UriBuilder.fromUri("http://ip6-localhost:" + port + "/" + root + "/rest/echo").build();
		WebTarget target = client.target(uri).path("hello");
		invocation = target.request().accept(MediaType.TEXT_PLAIN).buildGet();
	}

	@Override
	public String send(final String msg) {
		calls++;
		return invocation.invoke(String.class);
	}


	@Override
	public long count() {
		return calls;
	}

	@Override
	public int loop() {
		return LOOP_SIZE;
	}
}
