package de.dbhw.vs.tcp;

public class RunTcpExample {
	public static void main(String[] args) throws Exception {

		// Start the server in a new thread
		new Thread(() -> {

			try {
				TcpServer.main(null);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).start();

		// Wait a bit to let the server startup finish
		Thread.sleep(200);

		// Start the client in a new thread
		new Thread(() -> {
			try {
				TcpClient.main(null);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).start();

		Thread.sleep(3 * 1000);
		System.exit(0);
	}
}
