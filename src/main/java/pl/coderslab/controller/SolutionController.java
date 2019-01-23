package pl.coderslab.controller;

import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/solution")
public class SolutionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        try {
            int userId = Integer.parseInt(request.getParameter("uid"));
            int exerciseId = Integer.parseInt(request.getParameter("eid"));
            boolean backHome = request.getParameter("home") != null;

            Solution solution = Solution.findByUserIdAndExerciseId(userId, exerciseId).orElse(null);
            request.setAttribute("solution", solution);

            if (backHome) {
                request.setAttribute("back", "/");
            } else {
                request.setAttribute("back", "/user?id=" + userId);
            }

        } catch (NumberFormatException | SQLException ignored) {
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/solution.jsp").forward(request, response);
    }
}