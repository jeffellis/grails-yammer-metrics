package metricstest

import com.codahale.metrics.health.HealthCheck

class AlwaysHealthyCheck extends HealthCheck {

    @Override
    protected HealthCheck.Result check() throws Exception {
        return HealthCheck.Result.healthy()
    }
}