package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        List<String> companies = getCompanies();
        String search = request.getParameter("search");
        if (search != null && !search.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (String company : companies) {
                if (company.contains(search)) {
                    result.append(company).append("\n");
                }
            }
            if (result.isEmpty()) {
                pw.println("Companies not found");
            } else {
                pw.println(result);
            }
        } else {
            StringBuilder result = new StringBuilder();
            for (String company : companies) {
                result.append(company).append("\n");
            }
            pw.println(result);
        }
        pw.close();
        // END
    }
}
