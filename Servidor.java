import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PORT = 4999;
    private static final String EXIT_KEYWORD = "Kilometro";

    public static void main(String[] args) {
        new Servidor().iniciarServidor();
    }

    public void iniciarServidor() {
        System.out.println("PORT_SERVIDOR: " + PORT);
        System.out.println("PARAULA_CLAU_SERVIDOR: \"" + EXIT_KEYWORD + "\"\n");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("> Server chat at port " + PORT);
            System.out.println("> Initializing server... OK");
            System.out.println("> Connection from client... OK\n");
            System.out.println("> Initializing chat... OK\n");

            try (Socket socket = serverSocket.accept()) {
                manejarComunicacion(socket);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }

        System.out.println("> Closing server... OK");
        System.out.println("> Bye!");
    }

    private void manejarComunicacion(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String clientMessage, serverMessage;

            while (true) {
                clientMessage = in.readLine();
                if (clientMessage == null) break;

                System.out.println("#Rebut del client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase(EXIT_KEYWORD)) {
                    System.out.println("\n> Client keyword detected!\n");
                    System.out.println("> Closing chat... OK\n");
                    break;
                }

                System.out.print("#Enviar al client: ");
                serverMessage = userInput.readLine();
                out.println(serverMessage);
            }
        }
    }
}


