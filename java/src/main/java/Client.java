public class Client {

	private long calls = 0;
	private final Server server;

	public Client(final Server server) {
		this.server = server;
	}

	public String send(final String msg) {
		calls++;
		return server.echo(msg);
	}


	public long count() {
		return calls;
	}

	public void reset() {
		calls = 0;
	}
}
