package pl.coderslab.controller;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            User user = User.findById(id).orElse(null);
            List<Solution> solutions = Solution.findByUserId(id);

            request.setAttribute("user", user);
            request.setAttribute("solutions", solutions);

        } catch (NumberFormatException | SQLException ignored) {
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
    }
}