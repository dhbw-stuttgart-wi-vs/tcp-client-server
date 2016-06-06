package de.dbhw.vs.tcp;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.uniluebeck.itm.util.logging.LogLevel;
import de.uniluebeck.itm.util.logging.Logging;

public class TcpServer {

	static {
		Logging.setLoggingDefaults(LogLevel.DEBUG, "[%-5p; %c{1}::%M] %m%n");
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Logger log = LoggerFactory.getLogger(TcpServer.class);

		ServerSocket serverSocket = new ServerSocket(11111);
		Socket clientSocket = null;

		int connectionCount = 0;

		// Accept connections forever
		while (true) {

			// Wait for a client to connect
			log.debug("Waiting for a connection from a client");
			clientSocket = serverSocket.accept();
			log.debug("Client #{} connected from {}", ++connectionCount, clientSocket.getRemoteSocketAddress());

			// We have a new connection, now read until no data is available anymore
			InputStream inputStream = clientSocket.getInputStream();
			LinkedList<Byte> requestBytes = new LinkedList<>();
			for (int b = inputStream.read(); b > -1; b = inputStream.read()) {
				requestBytes.addLast((byte) b);
			}

			String request = requestBytes.stream().map(b -> "" + ((char) b.byteValue())).reduce((a, b) -> a + b).get();
			log.debug("Received request: {}", request);

			Thread.sleep(4000);

			// Send response
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			writer.write("Anfrage verarbeitet\n");
			writer.flush();

			clientSocket.close();
		}

	}

}
