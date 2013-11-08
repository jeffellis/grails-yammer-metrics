package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.servlets.HealthCheckServlet
import grails.util.Holders

class HealthChecks {

    private static final HealthCheckRegistry builtInRegistry = new HealthCheckRegistry()

    static void register( String name, HealthCheck healthCheck) {
        HealthCheckRegistry registry = Holders?.servletContext?.getAttribute(HealthCheckServlet.HEALTH_CHECK_REGISTRY)
        if(!registry) {
            registry = builtInRegistry
        }
        registry.register(name, healthCheck)
    }
}
