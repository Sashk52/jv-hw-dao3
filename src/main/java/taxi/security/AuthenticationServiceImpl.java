package taxi.security;

import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;
import taxi.service.DriverService;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService{
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driver = driverService.findByLogin(login)
                .orElseThrow(()->new AuthenticationException("Your login is incorrect!"));

    if (driver.getPassword().equals(password)){
        return driver;
    }
    throw new AuthenticationException("Your password is incorrect!");
    }
}
