package pe.edu.utp.regionslist.controllers;

import org.atteo.evo.inflector.English;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by GrupoUTP on 24/02/2017.
 */
@WebServlet(name = "BaseController")
public class BaseController extends HttpServlet {
    private Connection connection;
    private String entityName;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected Connection getConnection() {
        if (connection == null) {
            try {
                InitialContext ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
                connection = dataSource.getConnection();
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }


    public String getUrl(String action) {
        if(action.equalsIgnoreCase("index")) { return "list" + English.plural(getEntityName()) + ".jsp"; }
        if(action.equalsIgnoreCase("show")) { return "show" + getEntityName() + ".jsp"; }
        if(action.equalsIgnoreCase("index")) { return "list" + English.plural(getEntityName()) + ".jsp"; }
        return "index.jsp";
    }

}
