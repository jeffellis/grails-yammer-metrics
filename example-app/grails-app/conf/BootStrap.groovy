import metricstest.AlwaysHealthyCheck
import org.grails.plugins.yammermetrics.groovy.HealthChecks

class BootStrap {

    def init = { servletContext ->
        HealthChecks.register("AlwaysHealthyCheck", new AlwaysHealthyCheck())
    }

    def destroy = {
    }
}
