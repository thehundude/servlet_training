import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/servlet_training", "postgres", "SAPdb123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int save(Emp e){
        int status = 0;

        try {
            Connection connection = EmpDao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user905 (name, password, email, country)" +
                    "VALUES (?, ?, ?, ?)");
            ps.setString(1, e.getName());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getCountry());

            status = ps.executeUpdate();

            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return status;
    }

    public static int update(Emp e) {
        int status = 0;

        try {
            Connection connection = EmpDao.getConnection();
            PreparedStatement ps = connection.prepareStatement("update user905 set name = ?, password = ?," +
                    "email = ?, country = ? where id = ?");
            ps.setString(1, e.getName());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getCountry());
            ps.setInt(5, e.getId());

            status = ps.executeUpdate();

            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return status;
    }

    public static int delete(int id) {
        int status = 0;

        try {
            Connection connection = EmpDao.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from user905 where id=?");
            ps.setInt(1, id);

            status = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static Emp getEmployeeById(int id) {
        Emp e = new Emp();

        try {
            Connection connection = EmpDao.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from user905 where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setCountry(rs.getString(5));
            }

            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return e;
    }

    public static List<Emp> getAllEmployees(){
        List<Emp> list = new ArrayList<Emp>();

        try {
            Connection connection = EmpDao.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from user905");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emp e = new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPassword(rs.getString("password"));
                e.setEmail(rs.getString(4));
                e.setCountry(rs.getString(5));
                list.add(e);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
