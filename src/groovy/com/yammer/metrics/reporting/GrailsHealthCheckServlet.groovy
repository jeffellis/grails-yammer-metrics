package com.yammer.metrics.reporting

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException

class GrailsHealthCheckServlet extends HealthCheckServlet {

    void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        super.doGet( req, new GrailsRoutablePrintWriterWorkaroundResponse( resp ) )
    }

}
