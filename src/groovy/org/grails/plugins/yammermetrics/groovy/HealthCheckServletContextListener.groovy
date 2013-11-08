package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.servlets.HealthCheckServlet

class HealthCheckServletContextListener extends HealthCheckServlet.ContextListener {

    public final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry()

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return healthCheckRegistry
    }
}
