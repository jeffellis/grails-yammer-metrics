package org.grails.plugins.yammermetrics.reporting

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import com.yammer.metrics.reporting.HealthCheckServlet

class GrailsHealthCheckServlet extends HealthCheckServlet {

    void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        super.doGet( req, new GrailsRoutablePrintWriterWorkaroundResponse( resp ) )
    }

}
