package org.gameoflife.model;

import org.gameoflife.model.exceptions.DaoException;
import org.gameoflife.model.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcGOLBoardDaoTest {

    @Test
    void read() throws DaoException {
        try(JdbcGOLBoardDao dao = new JdbcGOLBoardDao("asdfg"))
        {
            PlainGameOfLifeSimulator pgols = new PlainGameOfLifeSimulator();
            GameOfLifeBoard testowa = new GameOfLifeBoard(4,4,pgols,4);

            dao.write(testowa);
            GameOfLifeBoard testowa1 = dao.read();
            assertEquals(testowa1, testowa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void write() throws DaoException {
        try(JdbcGOLBoardDao dao = new JdbcGOLBoardDao("test2"))
        {
            PlainGameOfLifeSimulator pgols = new PlainGameOfLifeSimulator();
            GameOfLifeBoard testowa = new GameOfLifeBoard(4,4,pgols,4);

            dao.write(testowa);
            GameOfLifeBoard testowa1 = dao.read();
            assertEquals(testowa1, testowa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void close() throws Exception {

        try(JdbcGOLBoardDao dao = new JdbcGOLBoardDao("test1");)
        {

        } catch (Exception e) {
            throw new RuntimeException("");
        }

    }

    @Test
    void connectExceptionTest()
    {
        assertThrows(DatabaseException.class, () -> {
            JdbcGOLBoardDao dao = new JdbcGOLBoardDao("adfj;;h");
            dao.close();
        });
    }
    @Test
    void selectExceptionTest()
    {
        assertThrows(DatabaseException.class, () -> {
            JdbcGOLBoardDao dao = new JdbcGOLBoardDao("adfj;;h");
            dao.read();
            dao.close();
        });
    }
    @Test
    void insertExceptionTest() throws DaoException {
        assertThrows(DatabaseException.class, () -> {
            JdbcGOLBoardDao dao = new JdbcGOLBoardDao("test1");
            PlainGameOfLifeSimulator pgols = new PlainGameOfLifeSimulator();
            GameOfLifeBoard testowa = new GameOfLifeBoard(4,4,pgols,4);
            dao.write(testowa);
            dao.close();
        });
    }

}