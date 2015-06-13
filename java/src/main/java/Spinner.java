public class Spinner {
	private static final String[] phases = {"|", "/", "-", "\\"};
	private short phase;

	public void start() {
		phase = 0;
		System.out.printf(phases[phase]);
	}

	public void spin() {
		phase++;
		phase %= 4;
		System.out.printf("\r" + phases[phase]);
	}
}
