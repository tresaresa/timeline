package dao;

import entity.Message;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Tresaresa
 * @Date 2019/11/8 13:05
 */
class MessageDAOTest {

    @Test
    void getAllMessage() {
        MessageDAO messageDAO = new MessageDAO();
        ArrayList<Message> allMessage = messageDAO.getAll();
        assertNotNull(allMessage);
    }
}