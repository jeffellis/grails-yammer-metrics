package org.grails.plugins.metrics.groovy

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.servlets.HealthCheckServlet
import grails.util.Holders

class HealthChecks {

    private static final HealthCheckRegistry builtInRegistry = new HealthCheckRegistry()

    static void register( String name, HealthCheck healthCheck ) {
        HealthCheckRegistry registry = getRegistry()
        registry.register(name, healthCheck)
    }

    static HealthCheckRegistry getRegistry() {
        // Use the registry from the servletContext if one has been configured in doWithApplicationContext (i.e., the
        // normal runtime case for apps using the plugin) or fall back to the built in one otherwise (e.g., unit tests
        // which happen to touch instrumented classes)

        HealthCheckRegistry registry = Holders?.servletContext?.getAttribute(HealthCheckServlet.HEALTH_CHECK_REGISTRY)
        if (!registry) {
            registry = builtInRegistry
        }
        return registry
    }
}
