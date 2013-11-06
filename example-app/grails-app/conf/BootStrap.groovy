import com.codahale.metrics.health.HealthCheckRegistry
import metricstest.AlwaysHealthyCheck
import org.grails.plugins.yammermetrics.groovy.HealthCheckServletContextListener

class BootStrap {

    def init = { servletContext ->
        HealthCheckRegistry healthCheckRegistry = HealthCheckServletContextListener.HEALTH_CHECK_REGISTRY
        healthCheckRegistry.register("AlwaysHealthyCheck", new AlwaysHealthyCheck())
    }

    def destroy = {
    }
}
