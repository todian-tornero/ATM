import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(String firstname, String lastname, String login, String password){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_FIRSTNAME + ","
                + Const.USERS_LASTNAME + "," + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + ")"
                + "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, firstname);
            prSt.setString(2, lastname);
            prSt.setString(3, login);
            prSt.setString(4, password);
            prSt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int id){
        String remove = "DELETE FROM " + Const.USER_TABLE + " WHERE idusers = " + id;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(remove);
            prSt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void selectFromTable(){
        String select = "SELECT * FROM " + Const.USER_TABLE;
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idusers");
                String s = resultSet.getString("firstname");
                String s1 = resultSet.getString("lastname");
                System.out.println(id + "   " + s + "   " + s1);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
