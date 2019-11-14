package dao;

import entity.Message;

import java.sql.*;
import java.util.ArrayList;

/**
 * @Author Tresaresa
 * @Date 2019/11/6 18:09
 */
public class MessageDAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/timeline?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
    private String username = "root";
    private String password = "123456";

    public MessageDAO() {
        loadDriver();
    }

    protected void loadDriver() {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /**
     * select all messages in descending order
     */
    public ArrayList<Message> getAllDesc() {
        ArrayList<Message> allMessage = new ArrayList<Message>();
        try {
            Connection conn = getConnection();
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
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(allMessage);
        return allMessage;
    }

    /**
     * add one new message
     */
    public boolean addOneMessage(String content, String author) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement("insert into message value (0, ?, ?, now())");
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, author);
            int i = preparedStatement.executeUpdate();
            conn.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

    /**
     * delete one message by id
     */
    public int deleteById(int id) {
        int affectedRows = -1;
        try {
            Connection conn = getConnection();
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
