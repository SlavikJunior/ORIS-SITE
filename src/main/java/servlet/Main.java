package servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = 8082;
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        context.addServlet(JettyServlet.class, "/contact");
        context.addServlet(ThanksServlet.class, "/thanks");

        server.setHandler(context);

        server.start();
        System.out.println("Server started at http://localhost:" + port);
        server.join();
    }
}
