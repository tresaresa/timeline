package servlet;

import dao.MessageDAO;
import entity.Message;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author Tresaresa
 * @Date 2019/11/14 10:46
 */
@WebServlet("/GetNewMessage")
public class getNewMessage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        System.out.println("start to get new message");

        int latestId = Integer.parseInt(request.getParameter("latest_id"));
        System.out.println(latestId);
        MessageDAO messageDAO = new MessageDAO();
        ArrayList<Message> allMessage = messageDAO.getAllDesc();

        ArrayList<Message> newMessage = new ArrayList<>();
        for(int i = 0; i < allMessage.size(); i ++) {
            Message message = allMessage.get(i);
            if (message.getId() > latestId) {
                newMessage.add(message);
            }
            else {
                break;
            }
        }

        JSONArray jsonArray = JSONArray.fromObject(newMessage);
        response.getWriter().write(jsonArray.toString());
    }

}
