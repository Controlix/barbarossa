public class Client {

	private final Server server;

	public Client(final Server server) {
		this.server = server;
	}

	public String send(final String msg) {
		return server.echo(msg);
	}
}
