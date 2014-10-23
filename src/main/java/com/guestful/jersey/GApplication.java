package com.guestful.jersey;

import com.guestful.jersey.container.Container;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public class GApplication extends ResourceConfig {

    @Inject
    private ServiceLocator serviceLocator;

    public final ServiceLocator getServiceLocator() {
        if (serviceLocator == null) {
            throw new IllegalStateException("No ServiceLocator installed");
        }
        return serviceLocator;
    }

    public final void run(String... args) throws Exception {
        Container container = Container.create().setApplicationClass(getClass());
        configure(container);
        container.start();
    }

    public void configure(Container container) {
    }

    @PostConstruct
    private void _initialize() {
        initialize();
    }

    public void initialize() {
    }

}
