package org.grails.plugins.yammermetrics.reporting

import com.codahale.metrics.servlets.AdminServlet
import com.codahale.metrics.servlets.MetricsServlet
import com.codahale.metrics.servlets.PingServlet
import com.codahale.metrics.servlets.ThreadDumpServlet

class GrailsAdminServlet extends AdminServlet {

    GrailsAdminServlet() {
        super()
//        super(new GrailsHealthCheckServlet(), new MetricsServlet(), new PingServlet(),
//                new ThreadDumpServlet(), AdminServlet.DEFAULT_HEALTHCHECK_URI, AdminServlet.DEFAULT_METRICS_URI,
//                AdminServlet.DEFAULT_PING_URI, AdminServlet.DEFAULT_THREADS_URI);
    }
}
