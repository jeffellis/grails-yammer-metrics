package metricstest

import com.codahale.metrics.Gauge
import org.grails.plugins.yammermetrics.groovy.Metered
import org.grails.plugins.yammermetrics.groovy.Metrics
import org.grails.plugins.yammermetrics.groovy.Timed

class PegController {

    class DepthGauge implements Gauge<Long> {
        Long depth = 100

        Long getValue() {
            depth += 1
            return depth
        }
    }

    PegController() {
        Metrics.newGauge("DepthGauge", new DepthGauge())
    }

    @Metered( name = "meter" )
    def metered() {
        render( contentType: "text/plain", text: "Metered!" )
    }

    @Timed( name = "timer" )
    def timed() {
        Thread.sleep( 200 )
        render( contentType: "text/plain", text: "Timed!" )
    }

}
