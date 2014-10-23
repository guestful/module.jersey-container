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
package com.guestful.jersey.container;

import javax.ws.rs.core.Application;
import java.util.ServiceLoader;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public abstract class Container {

    private String contextPath = "/";
    private int port = 8080;
    private int maxWorkers = -1;
    private Class<? extends Application> applicationClass;

    public static Container create() {
        return ServiceLoader.load(Container.class).iterator().next();
    }

    public String getContextPath() {
        return contextPath;
    }

    public Container setContextPath(String contextPath) {
        this.contextPath = contextPath;
        return this;
    }

    public int getPort() {
        return port;
    }

    public Container setPort(int port) {
        this.port = port;
        return this;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public Container setMaxWorkers(int maxWorkers) {
        this.maxWorkers = maxWorkers;
        return this;
    }

    public Class<? extends Application> getApplicationClass() {
        return applicationClass;
    }

    public Container setApplicationClass(Class<? extends Application> applicationClass) {
        this.applicationClass = applicationClass;
        return this;
    }

    public final void start() throws Exception {
        if (isStopped()) {
            if (!Port.isFree(getPort())) {
                throw new IllegalStateException("Port already used: " + getPort());
            }
            doStart();
        }
    }

    protected abstract void doStart() throws Exception;

    public final void stop() throws Exception {
        if (!isRunning()) {
            doStop();
            applicationClass = null;
        }
    }

    protected abstract void doStop() throws Exception;

    public abstract boolean isRunning();

    public abstract boolean isStopped();

}
