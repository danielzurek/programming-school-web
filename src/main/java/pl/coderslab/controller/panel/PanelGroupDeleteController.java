package pl.coderslab.controller.panel;

import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/panel/groups/delete")
public class PanelGroupDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Group.findById(id).ifPresent(group -> {
                try {
                    group.delete();
                } catch (SQLException ignored) {
                }
            });
        } catch (NumberFormatException | SQLException ignored) {
        }

        response.sendRedirect("/panel/groups");
    }
}