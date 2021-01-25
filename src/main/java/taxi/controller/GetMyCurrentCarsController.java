package taxi.controller;

import taxi.lib.Injector;
import taxi.model.Car;
import taxi.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetMyCurrentCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Long driver_id = (Long) session.getAttribute("drivers_id");
        List<Car> allCarsByDriver = this.carService.getAllByDriver(driver_id);

        req.setAttribute("carsByDriver", allCarsByDriver);
        req.getRequestDispatcher("/WEB-INF/views/cars/driver/all.jsp").forward(req, resp);
    }
}
