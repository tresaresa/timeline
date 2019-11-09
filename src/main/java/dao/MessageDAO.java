package dao;

import entity.Message;

import java.sql.*;
import java.util.ArrayList;

/**
 * @Author Tresaresa
 * @Date 2019/11/6 18:09
 */
public class MessageDAO {
    /**
     * select all messages in descending order
     */
    public ArrayList<Message> getAllDesc() {
        ArrayList<Message> allMessage = new ArrayList<Message>();
        try {
            Connection conn = DBConn.init();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from message order by id desc");
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

    /**
     * add one new message
     */
    public int addOneMessage(String content, String author) {
        int affectedRows = -1;
        try {
            Connection conn = DBConn.init();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into message value (0, ?, ?, now())");
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, author);
            affectedRows = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    /**
     * delete one message by id
     */
    public int deleteById(int id) {
        int affectedRows = -1;
        try {
            Connection conn = DBConn.init();
            PreparedStatement preparedStatement = conn.prepareStatement("delete from message where id=?");
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }
}
