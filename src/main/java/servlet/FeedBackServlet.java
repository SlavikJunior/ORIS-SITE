package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/feedback")
public class FeedBackServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var path = "src/main/resources/webapp/feedback.html";
        var file = Paths.get(path);
        if (Files.exists(file)) {
            resp.getOutputStream().write(Files.readAllBytes(file));
            resp.setStatus(200);
        } else
            resp.setStatus(404);
    }
}
