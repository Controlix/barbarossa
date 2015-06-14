import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Client {

	private long calls = 0;
	private Invocation invocation;
	private URI uri = UriBuilder.fromUri("http://localhost:8080/server/rest/echo").build();

	public Client() {
		ClientConfig config = new ClientConfig();
		javax.ws.rs.client.Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri).path("hello");
		invocation = target.request().accept(MediaType.TEXT_PLAIN).buildGet();
	}

	public String send(final String msg) {
		calls++;
		return invocation.invoke(String.class);
	}


	public long count() {
		return calls;
	}

	public void reset() {
		calls = 0;
	}
}
