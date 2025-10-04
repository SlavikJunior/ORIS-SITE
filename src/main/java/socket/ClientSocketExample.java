package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketExample {

    public static void main(String[] args) throws Exception {
        initClient();
    }

    private static void initClient() throws Exception {
        try (
                var socket = new Socket("localhost", 8888);
                var input = new Scanner(System.in);
                var output = new PrintWriter(socket.getOutputStream());
        ) {
            System.out.print("Enter text for server: ");
            output.println(input.nextLine());
            System.out.print("Server said: ");
            new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
