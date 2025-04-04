private void manejarComunicacion(Socket socket) throws IOException {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

        String clientMessage, serverResponse;
        boolean continuar = true;

        while (continuar) {
            System.out.print("#Enviar al servidor: ");
            clientMessage = userInput.readLine();
            out.println(clientMessage);

            if (clientMessage.equalsIgnoreCase(EXIT_KEYWORD)) {
                System.out.println("\n> Client keyword detected!\n");
                System.out.println("> Closing chat... OK\n");
                continuar = false;
            } else {
                serverResponse = in.readLine();
                if (serverResponse == null) {
                    continuar = false;
                } else {
                    System.out.println("#Rebut del servidor: " + serverResponse);
                }
            }
        }
    }
}

