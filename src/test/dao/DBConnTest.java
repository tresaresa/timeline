package dao;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Tresaresa
 * @Date 2019/11/8 12:44
 */
class DBConnTest {

    @Test
    void initDBConn() {
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.init();
        assertNotNull(conn);
    }

}