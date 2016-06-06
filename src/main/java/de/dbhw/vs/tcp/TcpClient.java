package de.dbhw.vs.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.uniluebeck.itm.util.logging.LogLevel;
import de.uniluebeck.itm.util.logging.Logging;

public class TcpClient {

	static {
		Logging.setLoggingDefaults(LogLevel.DEBUG, "[%-5p; %c{1}::%M] %m%n");
	}

	public static void main(String[] args) throws Exception {
		Logger log = LoggerFactory.getLogger(TcpClient.class);

		// The helper to format the date and time according to ISO 8601
		SimpleDateFormat iso8601Formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");

		// Connect to the server
		long requestStartTime = System.currentTimeMillis();
		Socket clientSocket = new Socket("localhost", 11111);

		log.debug("Connection established");

		// Send the request
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		writer.write("Hallo Welt @ " + iso8601Formatter.format(new Date()) + "\n");
		writer.flush();

		// Close the output stream so that the server is informed
		clientSocket.shutdownOutput();

		// Receive the response from the server
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// Read response from server
		String response = reader.readLine();
		log.debug("Received response (duration: {}ms): {}", System.currentTimeMillis() - requestStartTime, response);

		clientSocket.close();
	}
}
