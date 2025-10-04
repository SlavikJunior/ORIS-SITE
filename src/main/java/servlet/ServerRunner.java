package servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ServerRunner {

    public static void main(String[] args) throws Exception {

        var server = new Server(7777);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        context.addServlet(MainServlet.class, "/");
        context.addServlet(IndexServlet.class, "/index");
        context.addServlet(FeedBackServlet.class, "/feedback");

        server.setHandler(context);

        server.start();
        System.out.println("Server started at http://localhost:7777/");
        server.join();
    }
}
