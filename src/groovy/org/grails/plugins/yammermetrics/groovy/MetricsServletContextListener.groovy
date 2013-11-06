package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.servlets.MetricsServlet

class MetricsServletContextListener extends MetricsServlet.ContextListener {

    @Override
    protected MetricRegistry getMetricRegistry() {
        return GroovierMetrics.DEFAULT_METRICS_REGISTRY
    }
}
