package taxi.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Manufacturer;
import taxi.service.CarService;

public class CarController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("taxi");
    private CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Car car1 = new Car("Astra",new Manufacturer("Opel","Germany"));
        Car car2 = new Car("Jetta",new Manufacturer("Volkswagen","Germany"));
        carService.create(car1);
        carService.create(car2);
        List<Car> allCars = this.carService.getAll();

        req.setAttribute("cars", allCars);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
