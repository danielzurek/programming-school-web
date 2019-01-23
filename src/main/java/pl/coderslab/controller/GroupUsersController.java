package pl.coderslab.controller;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/group")
public class GroupUsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Group group = Group.findById(id).orElse(null);
            List<User> groupUsers = group.getUsers();

            request.setAttribute("group", group);
            request.setAttribute("users", groupUsers);

        } catch (NumberFormatException | SQLException ignored) {
            response.getWriter().append("ex: ").append(ignored.getMessage());
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/groupUsers.jsp").forward(request, response);
    }
}