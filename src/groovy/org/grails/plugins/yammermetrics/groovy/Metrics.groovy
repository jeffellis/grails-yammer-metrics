package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.Counter
import com.codahale.metrics.Histogram
import com.codahale.metrics.JmxReporter
import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.Reservoir
import com.codahale.metrics.servlets.MetricsServlet
import com.codahale.metrics.Gauge
import grails.util.Holders
import org.codehaus.groovy.reflection.ReflectionUtils

class Metrics {

    private static final MetricRegistry builtInRegistry = new MetricRegistry()

	// ignore org.springsource.loaded.ri.ReflectiveInterceptor when not running as a war, and ignore this class for convenience
	static final List<String> extraIgnoredPackages = ["org.springsource.loaded.ri", "org.grails.plugins.yammermetrics.groovy"]

    private Metrics() { super() }

    static Gauge newGauge(String gaugeName, Gauge gauge){
        String metricName = MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), gaugeName)
        def metric = registry.getGauges().get(metricName)
        if(!metric) {
            metric = registry.register(metricName, gauge)
        }
        return metric
    }

    static Counter newCounter(String counterName){
        return registry.counter(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), counterName))
    }

    static Histogram newHistogram(String name){
        return registry.histogram(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), name))
    }

    static Histogram newHistogram(String name, Reservoir reservoir){
        String metricName = MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), name)
        def metric = registry.getHistograms().get(metricName)
        if(!metric) {
            Histogram histogram = new Histogram(reservoir)
            metric = registry.register(metricName, histogram)
        }
        return metric
    }

    static Meter newMeter(String meterName){
        return registry.meter(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), meterName))
    }

    static com.codahale.metrics.Timer newTimer(String timerName){
        return registry.timer(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), timerName))
    }

    static com.codahale.metrics.Timer newTimer(Class owner, String timerName){
        return registry.timer(MetricRegistry.name(owner, timerName))
    }

    static MetricRegistry getRegistry() {
        MetricRegistry metricRegistry = Holders?.servletContext?.getAttribute(MetricsServlet.METRICS_REGISTRY) as MetricRegistry
        if(!metricRegistry) {
            metricRegistry = builtInRegistry
        }
        return metricRegistry
    }

    static JmxReporter startJmxReporter() {
        final JmxReporter reporter = JmxReporter.forRegistry(Metrics.getRegistry()).build();
        reporter.start();
        return reporter
    }
}
