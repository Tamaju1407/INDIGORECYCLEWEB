package pe.edu.utp.regionslist.controllers;

import pe.edu.utp.regionslist.models.Region;
import pe.edu.utp.regionslist.services.HrService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by GrupoUTP on 17/02/2017.
 */
@WebServlet(name = "RegionsController", urlPatterns = "/regions")
public class RegionsController extends BaseController {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("Post", request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("Get", request, response);
    }

    private void processRequest(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "index.jsp";
        if(method.equals("Get") && action == null) { action = "index"; }
        if(method.equals("Post") && action.equalsIgnoreCase("index")) return;
        if(method.equals("Get") && action.equalsIgnoreCase("create")) return;
        if(method.equals("Get") && action.equalsIgnoreCase("update")) return;
        HrService service = new HrService();
        service.setConnection(getConnection());
        // action = index
        if(action.equalsIgnoreCase("index")) {
            List<Region> regions = service.findAllRegions();
            request.setAttribute("regions", regions);
            url = "listRegions.jsp";
        }
        // action = show
        if(action.equalsIgnoreCase("show")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("region", service.findRegionById(id));
            url = "showRegion.jsp";
        }
        // action = new
        if(action.equalsIgnoreCase("new")) {
            url = "newRegion.jsp";
        }
        // action = create
        if(action.equalsIgnoreCase("create")) {
            String name = request.getParameter("name");
            Region region = service.createRegion(name);
            request.setAttribute("regions", service.findAllRegions());
            url = "listRegions.jsp";
        }
        // action = edit
        if(action.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("region", service.findRegionById(id));
            url = "editRegion.jsp";
        }

        // action = delete
        if(action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean result = service.deleteRegion(id);
            request.setAttribute("regions", service.findAllRegions());
            url = "listRegions.jsp";
        }
        // action = update
        if(action.equalsIgnoreCase("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            boolean result = service.updateRegion(new Region(id, name));
            request.setAttribute("regions", service.findAllRegions());
            url = "listRegions.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }


}
