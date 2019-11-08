package dao;

import entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Tresaresa
 * @Date 2019/11/6 18:09
 */
public class MessageDAO {
    public ArrayList<Message> getAll() {
        ArrayList<Message> allMessage = new ArrayList<Message>();
        try {
            Connection conn = DBConn.init();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from message");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setAuthor(resultSet.getString("author"));
                message.setContent(resultSet.getString("content"));
                message.setTimestamp(resultSet.getTimestamp("timestamp"));
                allMessage.add(message);
            }
            DBConn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return allMessage;
    }
}
