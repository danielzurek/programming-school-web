package pl.coderslab.controller.panel;

import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/panel/groups/save")
public class PanelGroupSaveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        Group group = getOrCreateGroup(id);
        group.setName(name);

        try {
            group.save();
        } catch (SQLException ignored) {
        }

        response.sendRedirect("/panel/groups");
    }

    private Group getOrCreateGroup(String textualId) {
        try {
            int id = Integer.parseInt(textualId);
            Group group = Group.findById(id).orElse(null);
            if (group != null) {
                return group;
            }
        } catch (NumberFormatException | SQLException ignored) {
        }
        return new Group();
    }
}