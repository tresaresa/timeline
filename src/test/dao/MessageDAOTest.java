package dao;

import entity.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.verification.Times;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @Author Tresaresa
 * @Date 2019/11/8 13:05
 */
class MessageDAOTest {

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);

    class MessageDAOFake extends MessageDAO {

        @Override
        protected void loadDriver() {

        }

        @Override
        protected Connection getConnection() {
            return connection;
        }
    }

    private MessageDAO messageDAO;

    @BeforeEach
    public void init() {
        messageDAO = new MessageDAOFake();
    }

    @Test
    void getAllMessageDesc() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(mock(ResultSet.class));

        ArrayList<Message> allMessage = messageDAO.getAllDesc();
        assertNotNull(allMessage);

        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
    }

    @ParameterizedTest
    @MethodSource("provideMessageWithList")
    void addOneMessage(String content, String author) throws Exception{
        // stubbing
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        // calling actual method and test state
        boolean result = messageDAO.addOneMessage(content, author);
        assertTrue(result);
        // creating argument captors
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        // verifying the mocked prepared-statement's setString is invoked 2 times
        verify(preparedStatement, new Times(2)).setString(integerArgumentCaptor.capture(), stringArgumentCaptor.capture());
        // verifying the arguments passed to the statement object
        assertEquals(content, stringArgumentCaptor.getAllValues().get(0));
        assertEquals(author, stringArgumentCaptor.getAllValues().get(1));
        // verifying the mock resources were used and closed
        verify(connection).commit();
        verify(preparedStatement).executeUpdate();
        verify(connection).close();
        verify(preparedStatement).close();
    }

    static List<Arguments> provideMessageWithList() {
        return Arrays.asList(Arguments.of("test content", "test author"),
                Arguments.of("中文测试 内容", "中文测试 作者"));
    }
}