package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.servlets.MetricsServlet

class MetricsServletContextListener extends MetricsServlet.ContextListener {

    public final MetricRegistry metricRegistry = new MetricRegistry()

    @Override
    protected MetricRegistry getMetricRegistry() {
        return metricRegistry
    }
}
