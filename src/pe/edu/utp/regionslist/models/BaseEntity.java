package pe.edu.utp.regionslist.models;

import java.sql.Connection;

/**
 * Created by GrupoUTP on 17/02/2017.
 */
public class BaseEntity {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
