package Servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private static int lastIDUser;
    private static User user;
    static Connection con = InitializeConnection.getCon();
    public static String currentUser;

    static {
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from users");
            while (rs.next())
                lastIDUser = rs.getInt("id_user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        User.currentUser = currentUser;
    }

    public User() {
    }

    static boolean isExist(String login, String password){
        boolean isUserExists = false;
        final String isUserExist = "select 1 from Users where login = ? and password = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(isUserExist)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    isUserExists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUserExists;
    }

    static void addUser(String login, String password){
        final String addUser = "insert into Users (id_user, login, password) values (?,?,?)";
        try(PreparedStatement preparedStatement = con.prepareStatement(addUser)) {
            preparedStatement.setInt(1, ++lastIDUser);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String getNameAndSurname(String login){
        String data = "";
        final String isUserExist = "select first_name, last_name from Users where login = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(isUserExist)) {
            preparedStatement.setString(1, login);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("first_name") + " " + rs.getString("last_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    static int getId(String login){
        int userId = 0;
        final String getId = "select id_user from Users where login = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(getId)) {
            preparedStatement.setString(1, login);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id_user");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    static ArrayList<String> getUserResources(String login){
        ArrayList<String> strings = new ArrayList<>();
        int currUserId = getId(login);
        final String isUserExist = "select name from Resources r, Users_resources ur where r.id_resource = ur.resources_id_resource and ur.users_id_user = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(isUserExist)) {
            preparedStatement.setInt(1, currUserId);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    strings.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static User getInstanceOfUser(){
        if(user == null) user = new User();
        return user;
    }
}
