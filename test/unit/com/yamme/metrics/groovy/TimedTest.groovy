package com.yamme.metrics.groovy

import com.yammer.metrics.core.Timer

/**
 * User: GavinHogan@gmail.com
 * Date: 5/23/12
 * Time: 2:37 PM
 */
class TimedTest extends GroovyTestCase {
    SampleObject sample

    void setUp(){

        sample = new SampleObject()
    }

    void testAddingTimerField(){
        def timer = sample.hasProperty('timedTimer')?sample.timedTimer : null
        assertNotNull("We should generate 'timedTimer' for timed() :> ${sample.dump()}", timer)
        if(!timer instanceof Timer){
            fail("We should generate a com.yammer.metrics.groovy.Timer for timed()  :> ${sample.dump()}")
        }
    }

    void testNotAddingTimerField(){
        def timer = sample.hasProperty('notTimedTimer')?sample.notTimedTimer : null
        assertNull("We should NOT generate 'notTimedTimer' for notTimed() :> ${sample.dump()}", timer)
    }

    void testCreatingNamedTimer(){
        def defaultASTTimer = sample.hasProperty('timedWithNamedTimer') ? sample.timedWithNamedTimer : null
        def namedTimer = sample.hasProperty('namedTimerInst') ? sample.namedTimerInst : null
        assertNull("We should NOT have created a default timer via AST when the annotation provides a name", defaultASTTimer)
        assertNotNull("We should have created a timer via AST to match the annotations name param", namedTimer)
    }

    void testUsingNamedTimer(){
        def namedTimer = sample.namedTimerInst
        namedTimer.clear()
        def callCount = 10
        callCount.times{
            sample.timedWithNamed()
        }
        assertEquals("We should see ${callCount} calls on our timer", callCount, namedTimer.count() )
    }

    void testSetupExistingNamedTimer(){
        def defaultASTTimer = sample.hasProperty('timedWithExistingNamedTimer') ? sample.timedWithExistingNamedTimer : null
        assertNull("We should NOT have created a default timer via AST when the annotation provides a name to an existing timer", defaultASTTimer)
    }

    void testUsingExistingNamedTimer(){
        def namedExistingTimer = sample.existingNamedTimerInst
        namedExistingTimer.clear()
        def callCount = 10
        callCount.times{
            sample.timedWithExistingNamed()
        }
        assertEquals("We should see ${callCount} calls on our timer", callCount, namedExistingTimer.count() )

    }

    //Not going crazy testing the yammer code, just want to ensure that our AST is functioning.
    void testCallingTimedMethod(){
        def callCount = 10
        callCount.times {
            sample.timed()
        }
        def timer = sample.timedTimer
        assertEquals("We should see ${callCount} calls on our timer", callCount, timer.count() )

    }


    //Not going crazy testing the yammer code, just want to ensure that our AST is functioning.
    void testCallingMultiMetricMethod(){
        sample.multiMetricTimer.clear()

        def callCount = 10
        callCount.times {
            sample.multiMetric()
        }
        def timer = sample.multiMetricTimer
        assertEquals("We should see ${callCount} calls on our timer", callCount, timer.count() )
    }

}
