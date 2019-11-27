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
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement("select * from message order by id desc");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Message> allMessage = new ArrayList<Message>();
            while(resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setAuthor(resultSet.getString("author"));
                message.setContent(resultSet.getString("content"));
                message.setTimestamp(resultSet.getTimestamp("timestamp"));
                message.setImage(resultSet.getString("image"));
                allMessage.add(message);
            }

            // if database is empty, then add a notice
            if (allMessage.isEmpty()) {
                Message message = new Message();
                message.setId(-1);
                message.setAuthor("提醒：");
                message.setContent("目前还没有人发言");
                message.setTimestamp(new Timestamp(System.currentTimeMillis()));
                allMessage.add(message);
            }
            return allMessage;
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
        return null;
    }

    /**
     * add one new message
     */
    public boolean addOneMessage(String content, String author) {
        if (content == null || author == null) {
            throw new IllegalArgumentException("Invalid inputs content=[" + content + "], author=[" + author + "]");
        }

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

}
