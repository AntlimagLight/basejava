package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
        storage.clear();
        storage.save(ResumeTestData.createFullResume(UUID.randomUUID().toString(), "Evan Chaplin"));
        storage.save(ResumeTestData.createFullResume(UUID.randomUUID().toString(), "Alise Marlow"));
        storage.save(ResumeTestData.createFullResume(UUID.randomUUID().toString(), "Igor Raven"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=UTF-8\">\n" +
                        "    <title>Список резюме</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<section>\n" +
                        "<table border=\"1\">\n" +
                        "    <tr>\n" +
                        "        <th>ID</th>\n" +
                        "        <th>Имя</th>\n" +
                        "        <th>Телефон</th>\n" +
                        "    </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "     <td>" + resume.getUuid() + "</td>\n" +
                            "     <td>" + resume.getFullName() + "</td>\n" +
                            "     <td>" + resume.getContact(ContactType.PHONE) + "</td>\n" +
                            "</tr>\n");
        }
        writer.write("</table>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
