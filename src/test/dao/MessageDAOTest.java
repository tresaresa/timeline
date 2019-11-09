package dao;

import entity.Message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Tresaresa
 * @Date 2019/11/8 13:05
 */
class MessageDAOTest {

    private MessageDAO messageDAO = new MessageDAO();

    @Test
    void getAllMessageDesc() {
        messageDAO = new MessageDAO();
        ArrayList<Message> allMessage = messageDAO.getAllDesc();
        assertNotNull(allMessage);
        // the test database has 5 rows at all
        assertEquals(5, allMessage.size());
        // the first row has the largest id
        assertTrue(allMessage.get(0).getId()>=allMessage.get(allMessage.size() - 1).getId());
    }

    @ParameterizedTest
    @MethodSource("provideMessageWithList")
    void addOneMessage(String content, String author) {
        int affectedRows = messageDAO.addOneMessage(content, author);
        assertEquals(1, affectedRows);
        Message newMessage = messageDAO.getAllDesc().get(0);
        // delete the test message
        messageDAO.deleteById(newMessage.getId());
        // content and author shouldn't be empty
        assertEquals(content, newMessage.getContent());
        assertEquals(author, newMessage.getAuthor());
    }

    static List<Arguments> provideMessageWithList() {
        return Arrays.asList(Arguments.of("test content", "test author"),
                Arguments.of("中文测试 内容", "中文测试 作者"));
    }
}