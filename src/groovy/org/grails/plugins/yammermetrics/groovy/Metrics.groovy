package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.Counter
import com.codahale.metrics.Histogram
import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.servlets.MetricsServlet
import com.codahale.metrics.Gauge
import grails.util.Holders
import org.codehaus.groovy.reflection.ReflectionUtils

class Metrics {

    private static final MetricRegistry builtInRegistry = new MetricRegistry()

	// ignore org.springsource.loaded.ri.ReflectiveInterceptor when not running as a war, and ignore this class for convenience
	static final List<String> extraIgnoredPackages = ["org.springsource.loaded.ri", "org.grails.plugins.yammermetrics.groovy"]

    private Metrics() { super() }

    static Gauge newGauge(String gaugeName, Gauge metric){
        return registry.register(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), gaugeName), metric)
    }

    static Counter newCounter(String counterName){
        return registry.counter(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), counterName))
    }

    static Histogram newHistogram(String name){
        return registry.histogram(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), name))
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
}
