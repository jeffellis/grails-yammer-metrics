package org.grails.plugins.yammermetrics.reporting

import javax.servlet.http.HttpServletResponse
import org.codehaus.groovy.grails.web.sitemesh.GrailsRoutablePrintWriter

class GrailsRoutablePrintWriterWorkaroundResponse implements HttpServletResponse {

    @Delegate
    HttpServletResponse realResponse

    GrailsRoutablePrintWriterWorkaroundResponse(HttpServletResponse httpServletResponse) {
        realResponse = httpServletResponse
    }

    @Override
    PrintWriter getWriter() {
        //
        // GrailsRoutablePrintWriter has a bug where if you call format before any other methods that
        // call getDestination(), no output will be produced in the response.  HealthCheckServlet does this
        // when run under grails.  This workaround class makes sure that getDestination() is called by calling getOut()
        // when obtaining the PrintWriter.
        //
        PrintWriter writer = realResponse.getWriter()
        if( writer instanceof GrailsRoutablePrintWriter ) {
            writer.getOut()
        }
        return writer
    }
}
