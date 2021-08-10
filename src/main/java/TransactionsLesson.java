import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.SQLException;
import java.sql.Statement;

public class TransactionsLesson {
    public static void main(String[] args) throws SQLException {
        String sql = "BEGIN;" +
                "SAVEPOINT sp0;" +
                "INSERT INTO parking.vehicles VALUES (DEFAULT, 'CAR', 'B79PUP');" +
                "SAVEPOINT sp1;" +
                "INSERT INTO parking.vehicles VALUES (DEFAULT, 'CAR', 'B79PUP');" +
                "INSERT INTO parking.vehicles VALUES (DEFAULT, 'CAR', 'CJ34AVG');";
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        Statement statement = dbUtil.getConn().createStatement();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            sql = "ROLLBACK TO sp1; COMMIT;";
            statement.executeUpdate(sql);
        } finally {
            dbUtil.close();
            statement.close();
        }
    }
}
