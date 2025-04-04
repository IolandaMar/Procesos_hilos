private void manejarComunicacion(Socket socket) throws IOException {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

        String clientMessage, serverMessage;
        boolean continuar = true;

        while (continuar) {
            clientMessage = in.readLine();
            if (clientMessage == null) {
                continuar = false;
            } else {
                System.out.println("#Rebut del client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase(EXIT_KEYWORD)) {
                    System.out.println("\n> Client keyword detected!\n");
                    System.out.println("> Closing chat... OK\n");
                    continuar = false;
                } else {
                    System.out.print("#Enviar al client: ");
                    serverMessage = userInput.readLine();
                    out.println(serverMessage);
                }
            }
        }
    }
}


