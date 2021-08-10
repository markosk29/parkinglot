import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionsLesson {
    public static void main(String[] args) throws SQLException {
        new TransactionsLesson().jdbc();
    }

    private void jdbc() throws SQLException {
        DBUtil dbUtil = new DBUtil();
        Savepoint initialSavePoint = null;
        Savepoint afterInsertSavePoint = null;
        dbUtil.open();

        String sql1 = "INSERT INTO parking.vehicles VALUES (DEFAULT, 'CAR', 'B79PUP');";
        String sql2 = "INSERT INTO parking.vehicles VALUES (DEFAULT, 'CAR', 'B79PUP');";
        String sql3 = "INSERT INTO parking.vehicles VALUES (DEFAULT, 'CAR', 'CJ34AVG');";
        try {
            dbUtil.getConn().setAutoCommit(false);

            Statement statement = dbUtil.getConn().createStatement();
            initialSavePoint = dbUtil.getConn().setSavepoint();
            statement.executeUpdate(sql1);
            afterInsertSavePoint = dbUtil.getConn().setSavepoint();
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
            statement.getConnection().commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            dbUtil.getConn().rollback(initialSavePoint);

        }
        finally {
            dbUtil.close();
        }
    }
}
