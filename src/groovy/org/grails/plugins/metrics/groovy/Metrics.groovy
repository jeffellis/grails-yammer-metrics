package org.grails.plugins.metrics.groovy

import com.codahale.metrics.*
import com.codahale.metrics.servlets.MetricsServlet
import grails.util.Holders
import org.codehaus.groovy.reflection.ReflectionUtils

import java.util.concurrent.TimeUnit

class Metrics {

    private static final String REGISTRY_NAME = 'org.grails.plugins.metrics'

    // ignore org.springsource.loaded.ri.ReflectiveInterceptor when not running as a war, and ignore this class for convenience
    static final List<String> extraIgnoredPackages = ["org.springsource.loaded.ri", "org.grails.plugins.metrics.groovy"]

    private static String buildMetricName(String metricName) {
        if (Holders?.config?.metrics?.core?.prependClassName == false) {
            return metricName
        }

        Class callingClass = ReflectionUtils.getCallingClass(0, extraIgnoredPackages)
        return MetricRegistry.name(callingClass, metricName)
    }

    static Metric getOrAdd(String name, Metric metricToAdd) {
        String metricName = buildMetricName(name)
        Metric metric = registry.getMetrics().get(metricName)
        if (!metric) {
            metric = registry.register(metricName, metricToAdd)
        }
        return metric
    }

    static Gauge newGauge(String name, Gauge gauge) {
        return getOrAdd(name, gauge) as Gauge
    }

    static Counter newCounter(String name) {
        String metricName = buildMetricName(name)
        return registry.counter(metricName)
    }

    static Histogram newHistogram(String name) {
        String metricName = buildMetricName(name)
        return registry.histogram(metricName)
    }

    static Histogram newHistogram(String name, Reservoir reservoir) {
        Histogram histogram = new Histogram(reservoir)
        return getOrAdd(name, histogram) as Histogram
    }

    static Meter newMeter(String name) {
        String metricName = buildMetricName(name)
        return registry.meter(metricName)
    }

    static Timer newTimer(String name) {
        String metricName = buildMetricName(name)
        return registry.timer(metricName)
    }

    static MetricRegistry getRegistry() {
        return SharedMetricRegistries.getOrCreate(REGISTRY_NAME)
    }

    static JmxReporter startJmxReporter(TimeUnit rateUnit = TimeUnit.SECONDS, TimeUnit durationUnit = TimeUnit.MILLISECONDS) {
        final JmxReporter reporter = JmxReporter
                .forRegistry(registry)
                .convertRatesTo(rateUnit)
                .convertDurationsTo(durationUnit)
                .build();
        reporter.start();
        return reporter
    }

    static removeAll() {
        registry.removeMatching(MetricFilter.ALL)
    }

    private Metrics() {}
}
