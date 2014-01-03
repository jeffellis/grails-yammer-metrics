import metricstest.AlwaysHealthyCheck
import metricstest.AlwaysUnhealthyCheck

import org.grails.plugins.metrics.groovy.HealthChecks

class BootStrap {

    def init = { servletContext ->
        HealthChecks.register("AlwaysHealthyCheck", new AlwaysHealthyCheck())
        HealthChecks.register("AlwaysUnhealthyCheck", new AlwaysUnhealthyCheck())
    }

    def destroy = {
    }
}
