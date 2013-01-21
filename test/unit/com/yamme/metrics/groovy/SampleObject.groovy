package com.yamme.metrics.groovy

import org.grails.plugins.yammermetrics.groovy.GroovierMetrics
import org.grails.plugins.yammermetrics.groovy.Metered
import org.grails.plugins.yammermetrics.groovy.Timed

class SampleObject {

    final def existingNamedTimerInst = GroovierMetrics.newTimer('existingNamedTimerInst')

    @Timed
    public void timed(){
        ;//nothing
    }

    public void notTimed(){
        ;//nothing
    }


    @Timed(name='namedTimerInst')
    public void timedWithNamed(){
        ;//nothing
    }



    @Timed(name='existingNamedTimerInst')
    public void timedWithExistingNamed(){
        ;//nothing
    }



    @Metered
    public void metered(){
        ;//nothing
    }


    @Metered
    public void anotherMetered(){
        ;//nothing
    }

    public void notMetered(){
        ;//nothing
    }

    @Metered
    @Timed
    public void multiMetric(){
        ;//nothing
    }

}