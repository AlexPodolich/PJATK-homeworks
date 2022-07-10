package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserRepository extends RepositoryBase<UserDTO> implements IUserRepository {
    private static String TABLE_USERS = "utp10.users";
    private static String COLUMN_USER_ID = "user_id";
    private static String COLUMN_USER_LOGIN = "user_login";
    private static String COLUMN_USER_PASSWORD = "user_password";

    private static String SEQUENCE_USER_SEQ = "utp10.users_seq";

    public UserRepository(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void add(UserDTO userDTO) throws Task10Exception {
        try {
            Connection connection = getConnection();
            String statementString = "INSERT INTO " + TABLE_USERS + " ("
                    + COLUMN_USER_ID + ", "
                    + COLUMN_USER_LOGIN + ", "
                    + COLUMN_USER_PASSWORD +
                    ") VALUES ("
                    + sequenceNextValue(SEQUENCE_USER_SEQ) + ", ?, ?)";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setString(1, userDTO.getLogin());
            statement.setString(2, userDTO.getPassword());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new Task10Exception(ex);
        }
    }

    @Override
    public void update(UserDTO userDTO) throws Task10Exception {
        try{
            Connection connection = getConnection();
            String statementString = "UPDATE " + TABLE_USERS
                    + " SET "
                    + COLUMN_USER_LOGIN + " = ?,"
                    + COLUMN_USER_PASSWORD + " = ?"
                    + " WHERE "
                    + COLUMN_USER_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setString(1, userDTO.getLogin());
            statement.setString(2, userDTO.getPassword());
            statement.setInt(3, userDTO.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new Task10Exception(ex);
        }
    }

    @Override
    public void delete(UserDTO userDTO) throws Task10Exception {
        try{
            Connection connection = getConnection();
            String statementString = "DELETE " + TABLE_USERS
                    + " WHERE "
                    + COLUMN_USER_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setInt(1, userDTO.getId());
            statement.executeUpdate();
        }catch (SQLException ex) {
            throw new Task10Exception(ex);
        }
    }

    @Override
    public UserDTO findById(int id) throws Task10Exception {
        try{
            UserDTO user = null;
            Connection connection = getConnection();
            final String statementString = "SELECT "
                    + COLUMN_USER_ID + ", "
                    + COLUMN_USER_LOGIN + ", "
                    + COLUMN_USER_PASSWORD
                    + " FROM " + TABLE_USERS
                    + " WHERE "
                    + COLUMN_USER_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int itemsCount = resultSet.getFetchSize();
            if(itemsCount == 1){
                resultSet.first();
                int userId = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                user = new UserDTO(userId, userLogin, userPassword);
            }
            return user;
        } catch (SQLException e) {
            throw new Task10Exception(e);
        }
    }

    @Override
    public List<UserDTO> findByLogin(String login) throws Task10Exception {
        try{
            List<UserDTO> users = new LinkedList<>();
            Connection connection = getConnection();
            String statementString = "SELECT " +
                    COLUMN_USER_ID
                    + ", " + COLUMN_USER_LOGIN
                    + ", " + COLUMN_USER_PASSWORD
                    + " FROM " + TABLE_USERS
                    + " WHERE "
                    + COLUMN_USER_LOGIN + " LIKE ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                UserDTO user = new UserDTO(userId, userLogin, userPassword);
                users.add(user);
            }
            return users;
        }catch (SQLException ex){
            throw new Task10Exception(ex);
        }
    }

    protected String getTableName(){
        return TABLE_USERS;
    }

    @Override
    protected String getIdColumnName() {
        return null;
    }
}
