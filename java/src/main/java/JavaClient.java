package marc;

public class JavaClient implements Client {

	private static final int LOOP_SIZE = 1000 * 1000;

	private long calls = 0;
	private final JavaServer server;

	public JavaClient(final JavaServer server) {
		this.server = server;
	}

	@Override
	public String send(final String msg) {
		calls++;
		return server.echo(msg);
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
