package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.DTOBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RepositoryBase<TDTO extends DTOBase> implements IRepository<TDTO> {

    private final Connection _connection;

    protected RepositoryBase(Connection connection) throws SQLException {
        _connection = connection;
        _connection.setAutoCommit(false);
    }

    public final boolean exists(TDTO dto) throws Task10Exception {
        boolean exists = false;
        if(dto.hasExistingId()){
            TDTO savedEntity = findById(dto.getId());
            exists = savedEntity != null;
        }
        return exists;
    }

    public final Connection getConnection(){
        return _connection;
    }

    public void addOrUpdate(TDTO dto) throws Task10Exception {
        if(exists(dto)){
            update(dto);
        } else {
            add(dto);
        }
    }

    public void beginTransaction(){
        try{
            Connection connection = getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitTransaction(){
        try{
            Connection connection = getConnection();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackTransaction(){
        try{
            Connection connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final int getCount(){
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getCountQuery());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                return count;
            }else{
                throw new SQLException("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected final String sequenceNextValue(String sequenceName){
        return "NEXTVAL('" + sequenceName + "')";
    }

    protected abstract String getTableName();
    protected abstract String getIdColumnName();

    private String getCountQuery(){
        String query = "SELECT COUNT(1) FROM " + getTableName();
        return query;
    }


}
