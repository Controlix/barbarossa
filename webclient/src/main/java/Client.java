package marc;

public interface Client {
	long count();
	int loop();
	String send(String msg);
}
