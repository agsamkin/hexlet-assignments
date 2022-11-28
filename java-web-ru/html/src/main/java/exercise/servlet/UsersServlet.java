package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String content = readFile("src/main/resources/users.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> users = objectMapper.readValue(content, List.class);
        return users.stream().sorted((o1, o2) -> {
            int id1 = Integer.parseInt(String.valueOf(o1.get("id")));
            int id2 = Integer.parseInt(String.valueOf(o2.get("id")));
            return Integer.compare(id1, id2);
        }).collect(Collectors.toList());
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, Object>> users = getUsers();
        StringBuilder sb = new StringBuilder("<html>");

        sb.append("<head></head>");
        sb.append("<body>");
        sb.append("<table>");
        for (Map user : users) {
            String id = (String) user.get("id");

            sb.append("<tr>");
            sb.append("<td>").append(id).append("</td>");
            sb.append("<td>")
                    .append("<a href=\"/users/").append(id).append("\">")
                    .append(user.get("firstName"))
                    .append(" ")
                    .append(user.get("lastName"))
                    .append("</a>")
                    .append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        sb.append("</body>");
        sb.append("</html>");

        PrintWriter pw = response.getWriter();
        pw.println(sb.toString());
        pw.close();
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, Object>> users = getUsers();
        Optional<Map<String, Object>> opt = users.stream().filter(e -> id.equals(e.get("id"))).findFirst();
        if (opt.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
        } else {
            Map<String, Object> user = opt.get();

            StringBuilder sb  = new StringBuilder();
            sb.append(user.get("firstName"))
                    .append(" ")
                    .append(user.get("lastName"))
                    .append(" ")
                    .append(user.get("email"));

            PrintWriter pw = response.getWriter();
            pw.println(sb.toString());
            pw.close();
        }
        // END
    }

    public static String readFile(String filePath) {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
