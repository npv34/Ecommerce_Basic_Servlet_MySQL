package org.example.ecommert.controllers;
import org.example.ecommert.enties.User;
import org.example.ecommert.models.UserModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "authServlet", urlPatterns = {"/auth/*"})
public class AuthServlet extends HttpServlet {
    private UserModel userModel;
    @Override
    public void init() throws ServletException {
        this.userModel = new UserModel();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
             String url = req.getPathInfo();
             switch (url) {
                 case "/login":
                     renderPageLogin(req, resp);
                     break;
                 case "/register":
                     renderPageRegister(req, resp);
                     break;
                 default:
                     resp.sendRedirect("/login");
                     break;
             }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String url = req.getPathInfo();
            switch (url) {
                case "/login":
                    login(req, resp);
                    break;
                case "/register":
                    renderPageRegister(req, resp);
                    break;
                default:
                    resp.sendRedirect("/login");
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void renderPageLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/login.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void renderPageRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/register.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user =  this.userModel.findUser(username, password);
            if (user != null) {
                resp.sendRedirect("/home");
            } else {
                resp.sendRedirect("/auth/login");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
