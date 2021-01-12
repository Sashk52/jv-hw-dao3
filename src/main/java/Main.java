import lib.Injector;
import model.Manufacturer;
import service.ManufacturerServiceImpl;

public class Main {
    private static final Injector injector = Injector.getInstance("git-hw-dao-three");

    public static void main(String[] args) {
        ManufacturerServiceImpl manufacturerService = (ManufacturerServiceImpl) injector.getInstance(ManufacturerServiceImpl.class);
        Manufacturer manufacturer = new Manufacturer("Opel", "Germany");
        manufacturer.setName("Volkswagen");

        // initialize field values using setters or constructor
        manufacturerService.create(manufacturer);
        manufacturerService.get(Long.valueOf(1));
        manufacturerService.getAll();
        manufacturerService.update(manufacturer);
        manufacturerService.delete(Long.valueOf(1));
        // same for all other crud methods and for all models
    }
}
