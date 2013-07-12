package org.grails.plugins.yammermetrics.groovy

import com.yammer.metrics.Metrics
import org.codehaus.groovy.reflection.ReflectionUtils

import java.util.concurrent.TimeUnit

class GroovierMetrics extends Metrics {
	// ignore org.springsource.loaded.ri.ReflectiveInterceptor when not running as a war, and ignore this class for convenience
	static final List<String> extraIgnoredPackages = ["org.springsource.loaded.ri", "org.grails.plugins.yammermetrics.groovy"]

    private GroovierMetrics() { super() }

    static com.yammer.metrics.core.Meter newMeter(String meterName){
        return newMeter(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), meterName, meterName, TimeUnit.SECONDS)
    }

    static com.yammer.metrics.core.Timer newTimer(String timerName){
        return newTimer(ReflectionUtils.getCallingClass(0, extraIgnoredPackages), timerName )
    }

    static com.yammer.metrics.core.Timer newTimer(String className, String timerName){
        return newTimer(Class.forName(className), timerName)
    }
}
