package taxi;

import java.util.List;
import taxi.lib.Injector;
import taxi.model.Manufacturer;
import taxi.service.ManufacturerService;

public class Main {
    private static final Injector injector = Injector.getInstance("taxi");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);

        Manufacturer manufacturer1 = new Manufacturer("Opel", "Germany");
        Manufacturer manufacturer2 = new Manufacturer("Renault", "France");
        Manufacturer manufacturer3 = new Manufacturer("GeneralMotors", "USA");

        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);

        List<Manufacturer> manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        manufacturerService.update(manufacturer1);
        manufacturerService.update(manufacturer2);
        manufacturerService.update(manufacturer3);

        manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        System.out.println(manufacturerService.get(1L));
        System.out.println(manufacturerService.get(2L));
        System.out.println(manufacturerService.get(3L));

        manufacturerService.delete(1L);
        manufacturerService.delete(2L);
        manufacturerService.delete(3L);

        manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);
    }
}
