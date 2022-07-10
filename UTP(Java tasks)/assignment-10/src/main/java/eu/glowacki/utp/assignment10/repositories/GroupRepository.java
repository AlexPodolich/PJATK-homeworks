package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GroupRepository extends RepositoryBase<GroupDTO> implements IGroupRepository {
    private static String TABLE_GROUPS = "utp10.groups";
    private static String COLUMN_GROUP_ID = "group_id";
    private static String COLUMN_GROUP_NAME = "group_name";
    private static String COLUMN_GROUP_DESCRIPTION = "group_description";
    private static String SEQUENCE_GROUP_SEQ = "utp10.groups_seq";

    public GroupRepository(Connection connection) throws SQLException {
        super(connection);
    }

    public GroupDTO findById(int id) throws Task10Exception {
        try{
            GroupDTO group = null;
            Connection connection = getConnection();
            final String statementString = "SELECT "
                    + COLUMN_GROUP_ID + ", "
                    + COLUMN_GROUP_NAME + ", "
                    + COLUMN_GROUP_DESCRIPTION
                    + " FROM " + TABLE_GROUPS
                    + " WHERE "
                    + COLUMN_GROUP_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int itemsCount = resultSet.getFetchSize();
            if(itemsCount == 1){
                resultSet.first();
                int groupId = resultSet.getInt(1);
                String groupName = resultSet.getString(2);
                String groupDescription = resultSet.getString(3);
                group = new GroupDTO(groupId, groupName, groupDescription);
            }
            return group;
        } catch (SQLException e) {
            throw new Task10Exception(e);
        }
    }

    public void add(GroupDTO groupDTO) throws Task10Exception {
        try {
            Connection connection = getConnection();
            String statementString = "INSERT INTO " + TABLE_GROUPS + " ("
                    + COLUMN_GROUP_ID + ", "
                    + COLUMN_GROUP_NAME + ", "
                    + COLUMN_GROUP_DESCRIPTION +
                    ") VALUES ("
                    + sequenceNextValue(SEQUENCE_GROUP_SEQ) + ", ?, ?)";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setString(1, groupDTO.getName());
            statement.setString(2, groupDTO.getDescription());
            statement.  executeUpdate();
        }catch (SQLException ex){
            throw new Task10Exception(ex);
        }
    }

    public void update(GroupDTO groupDTO) throws Task10Exception {
        try{
            Connection connection = getConnection();
            String statementString = "UPDATE " + TABLE_GROUPS
                    + " SET "
                    + COLUMN_GROUP_NAME + " = ?,"
                    + COLUMN_GROUP_DESCRIPTION + " = ?"
                    + " WHERE "
                    + COLUMN_GROUP_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setString(1, groupDTO.getName());
            statement.setString(2, groupDTO.getDescription());
            statement.setInt(3, groupDTO.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new Task10Exception(ex);
        }
    }


    public void delete(GroupDTO user) throws Task10Exception {
        try{
            Connection connection = getConnection();
            String statementString = "DELETE " + TABLE_GROUPS
                    + " WHERE "
                    + COLUMN_GROUP_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        }catch (SQLException ex) {
            throw new Task10Exception(ex);
        }
    }

    public List<GroupDTO> findByName(String name) throws Task10Exception {
        try{
            List<GroupDTO> groups = new LinkedList<>();
            Connection connection = getConnection();
            String statementString = "SELECT " +
                    COLUMN_GROUP_ID
                    + ", " + COLUMN_GROUP_NAME
                    + ", " + COLUMN_GROUP_DESCRIPTION
                    + " FROM " + TABLE_GROUPS
                    + " WHERE "
                    + COLUMN_GROUP_NAME + " LIKE ?";
            PreparedStatement statement = connection.prepareStatement(statementString);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int groupId = resultSet.getInt(1);
                String groupName = resultSet.getString(2);
                String groupDescription = resultSet.getString(3);
                GroupDTO group = new GroupDTO(groupId, groupName, groupDescription);
                groups.add(group);
            }
            return groups;
        }catch (SQLException ex){
            throw new Task10Exception(ex);
        }
    }

    protected String getTableName(){
        return TABLE_GROUPS;
    }

    @Override
    protected String getIdColumnName() {
        return null;
    }

}
