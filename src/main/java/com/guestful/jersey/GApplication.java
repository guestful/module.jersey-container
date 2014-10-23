/**
 * Copyright (C) 2013 Guestful (info@guestful.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
