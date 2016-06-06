package livedemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo {

	public static void main(String[] args) throws IOException {

		// Soll auf Port 11111 hören und in einer Endlosschleife Verbindungen akzeptieren
		// Server Socket erstellen: ServerSocket(int port, int backlog)

		ServerSocket serverSocket = new ServerSocket(11111);

		while (true) {
			// Neuen Client akzeptieren: public Socket accept() throws IOException
			Socket clientSocket = serverSocket.accept();

			// InputStream zum Lesen vom Client erhalten: clientSocket.getInputStream()
			InputStream clientInputStream = clientSocket.getInputStream();

			// Ende des Streams erkennen: InputStream.read(...) liefert -1 zurück
			StringBuilder builder = new StringBuilder();
			for (int readResult = clientInputStream.read(); readResult >= 0; readResult = clientInputStream.read()) {
				builder.append((char) readResult);
			}
			String request = builder.toString();

			// Ausgabe des empfangenen Payloads und der Absenderdaten via log.info(...)
			System.out.println("Request vom Client empfangen: " + request);

			// Senden des Textes „Anfrage verarbeitet\n“ an den Client
			OutputStream clientOutputStream = clientSocket.getOutputStream();
			clientOutputStream.write("Anfrage verarbeitet\n".getBytes());
			clientOutputStream.flush();
			
			clientSocket.close();

		}

		// Zum Anfang der Endlosschleife zurückkehren, um auf den nächsten Client zu warten

	}
}
