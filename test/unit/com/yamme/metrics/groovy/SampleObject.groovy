package com.yamme.metrics.groovy

import com.yammer.metrics.groovy.GroovierMetrics
import com.yammer.metrics.groovy.Metered
import com.yammer.metrics.groovy.Timed

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