package com.codahale.metrics.groovy

import org.grails.plugins.metrics.groovy.Metrics
import org.grails.plugins.metrics.groovy.Metered
import org.grails.plugins.metrics.groovy.Timed

class SampleObject {

    final def existingNamedTimerInst = Metrics.newTimer('existingNamedTimerInst')

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