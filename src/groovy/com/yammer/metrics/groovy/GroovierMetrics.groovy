package com.yammer.metrics.groovy

import com.yammer.metrics.Metrics
import org.codehaus.groovy.reflection.ReflectionUtils

import java.util.concurrent.TimeUnit

class GroovierMetrics extends Metrics {

    private GroovierMetrics() { super() }

    static com.yammer.metrics.core.Meter newMeter(String meterName){
        return newMeter(ReflectionUtils.getCallingClass(2), meterName, meterName, TimeUnit.SECONDS)
    }

    static com.yammer.metrics.core.Timer newTimer(String timerName){
        return newTimer(ReflectionUtils.getCallingClass(2), timerName )
    }

    static com.yammer.metrics.core.Timer newTimer(String className, String timerName){
        return newTimer(Class.forName(className), timerName)
    }
}
