package metricstest

import com.codahale.metrics.health.HealthCheck

class AlwaysUnhealthyCheck extends HealthCheck {

    @Override
    protected HealthCheck.Result check() throws Exception {
        return HealthCheck.Result.unhealthy("I am not feeling well")
    }
}