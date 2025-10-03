package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/contact")
public class JettyServlet extends HttpServlet {

    private static final String FILE_PATH = "src/main/resources/data.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=\"utf-8\"");
        var path = "src/main/resources/webapp/feedback.html";
        var file = Paths.get(path);
        if (Files.exists(file)) {
            resp.getOutputStream().write(Files.readAllBytes(file));
            resp.setStatus(200);
        } else {
            resp.setStatus(404);
            resp.getWriter().write("<h1>404 — feedback.html not found</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String message = req.getParameter("message");

        // базовая валидация
        if (isEmpty(name) || isEmpty(email) || isEmpty(message)) {
            req.setAttribute("error", "Все поля обязательны!");
            doGet(req, resp);
            return;
        }

        if (!email.contains("@")) {
            req.setAttribute("error", "Некорректный email!");
            doGet(req, resp);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.write("Message: " + message);
            writer.newLine();
            writer.write("------");
            writer.newLine();
        }

        resp.sendRedirect("/thanks");
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty() || s.isBlank();
    }
}
