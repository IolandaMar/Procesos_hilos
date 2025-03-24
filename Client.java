import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 4999;
    private static final String EXIT_KEYWORD = "Iman";

    public static void main(String[] args) {
        new Client().iniciarCliente();
    }

    public void iniciarCliente() {
        System.out.println("PORT_SERVIDOR: " + PORT);
        System.out.println("PARAULA_CLAU_CLIENT: \"" + EXIT_KEYWORD + "\"\n");

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("> Client chat to port " + PORT);
            System.out.println("> Initializing client... OK\n");
            System.out.println("> Initializing chat... OK\n");

            manejarComunicacion(socket);
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }

        System.out.println("> Closing client... OK");
        System.out.println("> Bye!");
    }

    private void manejarComunicacion(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String clientMessage, serverResponse;

            while (true) {
                System.out.print("#Enviar al servidor: ");
                clientMessage = userInput.readLine();
                out.println(clientMessage);

                if (clientMessage.equalsIgnoreCase(EXIT_KEYWORD)) {
                    System.out.println("\n> Client keyword detected!\n");
                    System.out.println("> Closing chat... OK\n");
                    break;
                }

                serverResponse = in.readLine();
                if (serverResponse == null) break;

                System.out.println("#Rebut del servidor: " + serverResponse);
            }
        }
    }
}
