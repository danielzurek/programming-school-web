package pl.coderslab.controller.panel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/panel")
public class PanelController extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/panel.jsp").forward(request, response);
    }
}