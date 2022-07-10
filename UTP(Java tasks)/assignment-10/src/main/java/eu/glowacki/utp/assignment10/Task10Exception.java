package eu.glowacki.utp.assignment10;

import java.sql.SQLException;

public class Task10Exception extends Exception{
    public Task10Exception(SQLException e) {
        super(e);
    }
}
