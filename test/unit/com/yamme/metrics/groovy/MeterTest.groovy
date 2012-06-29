package com.yamme.metrics.groovy

import com.yammer.metrics.core.Meter

/**
 * User: GavinHogan@gmail.com
 * Date: 5/23/12
 * Time: 4:37 PM
 */

class MeterTest extends GroovyTestCase {
    SampleObject sample

    void setUp(){
        sample = new SampleObject()
    }


    void testAddingMeterField(){
        def meter = sample.hasProperty('meteredMeter')?sample.meteredMeter : null
        assertNotNull("We should generate 'meteredMeter' for metered() :> ${sample.dump()}", meter)
        if(!meter instanceof Meter){
            fail("We should generate a com.yammer.metrics.core.meter for timed()  :> ${sample.dump()}")
        }

    }

    void testNotAddingMeterField(){
        def meter = sample.hasProperty('notMeteredMeter')?sample.notMeteredMeter : null
        assertNull("We should NOT generate 'notMeteredMeter' for notMetered() :> ${sample.dump()}", meter)
    }

    //Not going crazy testing the yammer code, just want to ensure that our AST is functioning.
    void testCallingMeteredMethod(){

        def init = sample.multiMetricMeter.count.longValue()
        def callCount = 10
        callCount.times {
            sample.metered()
        }
        Meter meter = sample.meteredMeter
        assertEquals("We should see ${callCount} calls on our meter", callCount+init, meter.count() )
    }

    void testCountsSpanInstances(){
        def one = new SampleObject()
        def two = new SampleObject()
        def meterOne = one.anotherMeteredMeter
        def meterTwo = two.anotherMeteredMeter
        def initOne = meterOne.count.longValue()
        def initTwo = meterTwo.count.longValue()
        def callCount = 10

        10.times{
            one.anotherMetered()
            two.anotherMetered()
        }

        assertEquals("We should see ${callCount} calls on our meter", (callCount*2) + initOne, meterOne.count() )
        assertEquals("We should see ${callCount} calls on our meter", (callCount*2) + initTwo, meterTwo.count() )

    }

    //Not going crazy testing the yammer code, just want to ensure that our AST is functioning.
    void testCallingMultiMetricMethod(){
        def init = sample.multiMetricMeter.count.longValue()
        def callCount = 10
        callCount.times {
            sample.multiMetric()
        }
        Meter meter = sample.multiMetricMeter
        assertEquals("We should see ${callCount + init} calls on our meter", callCount + init, meter.count() )
    }
}
