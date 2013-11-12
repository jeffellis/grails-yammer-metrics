package org.grails.plugins.metrics.groovy

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.servlets.MetricsServlet
import grails.util.Holders

import java.util.concurrent.TimeUnit

class MetricsServletContextInitializer extends MetricsServlet.ContextListener {

    public final MetricRegistry metricRegistry = new MetricRegistry()

    @Override
    protected MetricRegistry getMetricRegistry() {
        return metricRegistry
    }

    @Override
    protected TimeUnit getRateUnit() {
        return getUnitFromConfig("rateUnit")
    }

    @Override
    protected TimeUnit getDurationUnit() {
        return getUnitFromConfig("durationUnit")
    }

    private TimeUnit getUnitFromConfig(String propertyName) {
        def unit = Holders.config.metrics.servlet[propertyName]
        if (unit instanceof String) {
            return TimeUnit.valueOf(unit.toUpperCase(Locale.US))
        }
        return null
    }

}
