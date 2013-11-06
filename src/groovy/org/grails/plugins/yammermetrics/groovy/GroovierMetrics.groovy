package org.grails.plugins.yammermetrics.groovy

import com.codahale.metrics.MetricRegistry
import org.codehaus.groovy.reflection.ReflectionUtils

class GroovierMetrics {
    static MetricRegistry DEFAULT_METRICS_REGISTRY = new MetricRegistry()

	// ignore org.springsource.loaded.ri.ReflectiveInterceptor when not running as a war, and ignore this class for convenience
	static final List<String> extraIgnoredPackages = ["org.springsource.loaded.ri", "org.grails.plugins.yammermetrics.groovy"]

    private GroovierMetrics() { super() }

    static com.codahale.metrics.Meter newMeter(String meterName){
        return DEFAULT_METRICS_REGISTRY.meter(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), meterName))
    }

    static com.codahale.metrics.Timer newTimer(String timerName){
        return DEFAULT_METRICS_REGISTRY.timer(MetricRegistry.name(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), timerName))
    }

    static com.codahale.metrics.Timer newTimer(String className, String timerName){
        return DEFAULT_METRICS_REGISTRY.timer(MetricRegistry.name(className, timerName))
    }
}
