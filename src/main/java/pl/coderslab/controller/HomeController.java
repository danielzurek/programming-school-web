package pl.coderslab.controller;
import pl.coderslab.model.Solution;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {

    private int numberSolutions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        numberSolutions = Integer.parseInt(context.getInitParameter("number-solutions"));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("solutions", getSolutions());
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    private List<Solution> getSolutions() {
        try {
            return Solution.findAll(numberSolutions);
        } catch (SQLException ex) {
            return Collections.emptyList();
        }
    }
}