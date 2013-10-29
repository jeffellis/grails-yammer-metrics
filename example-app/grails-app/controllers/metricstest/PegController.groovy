package metricstest

import org.grails.plugins.yammermetrics.groovy.Metered
import org.grails.plugins.yammermetrics.groovy.Timed

class PegController {

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
