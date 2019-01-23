package pl.coderslab.controller.panel;

import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/panel/groups/addedit")
public class PanelGroupAddEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("group", getGroup(id));
        } catch (NumberFormatException ignored) {
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/panel-group.jsp").forward(request, response);
    }

    private Group getGroup(int id) {
        try {
            return Group.findById(id).orElse(null);
        } catch (SQLException e) {
            return null;
        }
    }
}
