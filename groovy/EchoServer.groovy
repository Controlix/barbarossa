public class SimpleEchoServer implements Runnable {
	private int port;

	private LinkedList<Socket> openClients = new LinkedList<Socket>();
	private boolean cleaningUp = false;
	
	public SimpleEchoServer(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			println("Echo Server started on port: " + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not listen on port: " + port);
			return;
		}
		
		while (! Thread.interrupted()) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				println("Got new client connection: " + clientSocket.inetAddress.toString());
				openClients.add(clientSocket);
				(new Thread(new ConnectionWorker(clientSocket))).start();
			} catch (IOException e) {
				System.err.println("Accept failed.");
			}
		}
		try {
			println("Closing all open connections...");
			cleaningUp = true;
			for (Socket s : openClients) {
				s.close();
			}
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		println("Echo Server stopped on port: " + port);
	}
	
	protected void removeClient(Socket s) {
		// If we are cleaning up, removal will cause a ConcurrentModificationException.
		if (! cleaningUp) {
			openClients.remove(s);
		}
	}
	
	private class ConnectionWorker implements Runnable {
		private Socket client;
		
		public ConnectionWorker(Socket s) {
			this.client = s;
		}
		
		@Override
		public void run() {
			try {
				PrintWriter outWriter = new PrintWriter(client.getOutputStream(), true);
				BufferedReader inReader = new BufferedReader(
					new InputStreamReader(
						client.getInputStream()));
				String inputLine;
				
				// must just send last part of url back
				// e.g. http://localhost:8050/grooy/goodmorning => goodmorning
				while ((inputLine = inReader.readLine()) != null) {
					outWriter.println(inputLine);
					if (inputLine.equals("Bye.")){
						break;
					}
				}
				outWriter.close();
				inReader.close();
				client.close();
			} catch (IOException e) {
				// Just Terminate the connection.
				System.err.println(e.getMessage());
			}
			println("Ended client connection: " + client.inetAddress.toString());
			SimpleEchoServer.this.removeClient(client);
		}
	}
	
	
	private static Thread echoServer;
	public static void start() {
		println("Starting SimpleEchoServer");
		echoServer = new Thread(new SimpleEchoServer(4444));
		echoServer.start();
	}
	public static void stop() {
		println("Stopping SimpleEchoServer");
		echoServer.interrupt();
		echoServer = null;
	}
}

Runtime.runtime.addShutdownHook {
	SimpleEchoServer.stop()
}

SimpleEchoServer.start()

