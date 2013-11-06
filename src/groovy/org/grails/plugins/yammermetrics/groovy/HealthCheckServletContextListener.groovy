package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.servlets.HealthCheckServlet

class HealthCheckServletContextListener extends HealthCheckServlet.ContextListener {

    public static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry()

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return HealthCheckServletContextListener.HEALTH_CHECK_REGISTRY
    }
}
