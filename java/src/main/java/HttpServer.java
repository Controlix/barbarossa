package marc;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

@Path("echo")
public class HttpServer {

	@GET
	@Path("{msg}")
	@Produces(MediaType.TEXT_PLAIN)
	public String echo(@PathParam("msg") final String msg) {
		return msg;
	}
}
