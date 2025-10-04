package socket;

import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class ServerSocketExample {

    public static void main(String[] args) throws Exception {
        initServer();
    }

    private static void initServer() throws Exception {
        try (
                var server = new ServerSocket(8888);
                var socket = server.accept();
                var input = new Scanner(socket.getInputStream());
                var output = new PrintWriter(socket.getOutputStream());
        ) {
            var inputText = input.nextLine();
            var outputText = "User said: ".concat(inputText);
            output.println(outputText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
