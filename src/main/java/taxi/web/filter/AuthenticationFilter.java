package taxi.web.filter;

import taxi.lib.Injector;
import taxi.service.DriverService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter implements Filter {
    private static final String DRIVER_ID = "drivers_id";
    private static final Injector injector = Injector.getInstance("taxi");
    private final Set<String> allowedUrls = new HashSet<>();
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    allowedUrls.add("/drivers/login");
    allowedUrls.add("/drivers/add");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();
        if (allowedUrls.contains(url)) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (driverId == null || driverService.get(driverId) == null) {
            resp.sendRedirect("/drivers/login");
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
