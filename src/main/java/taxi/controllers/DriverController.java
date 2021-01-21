package taxi.controllers;

import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;
import taxi.service.CarService;
import taxi.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private DriverService driverService = (DriverService) injector.getInstance(DriverService.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Driver driver1 = new Driver("Jim","1111");
        Driver driver2 = new Driver("Monica","2222");
        driverService.create(driver1);
        driverService.create(driver2);
        List<Driver> allDrivers=this.driverService.getAll();

        req.setAttribute("drivers", allDrivers);
        req.getRequestDispatcher("/WEB-INF/views/drivers/all.jsp").forward(req, resp);
    }
}
