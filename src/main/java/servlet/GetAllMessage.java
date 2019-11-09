package servlet;

import dao.MessageDAO;
import entity.Message;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author Tresaresa
 * @Date 2019/11/6 18:17
 */
@WebServlet("/GetAllMessage")
public class GetAllMessage extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        System.out.println("start to get all message");

        MessageDAO messageDAO = new MessageDAO();
        ArrayList<Message> allMessage = messageDAO.getAllDesc();
        JSONArray jsonArray = JSONArray.fromObject(allMessage);
        System.out.println(jsonArray);
        response.getWriter().write(jsonArray.toString());
    }
}
