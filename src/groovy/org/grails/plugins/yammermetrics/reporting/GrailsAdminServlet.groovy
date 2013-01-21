package org.grails.plugins.yammermetrics.reporting

import com.yammer.metrics.reporting.AdminServlet
import com.yammer.metrics.reporting.MetricsServlet
import com.yammer.metrics.reporting.PingServlet
import com.yammer.metrics.reporting.ThreadDumpServlet

class GrailsAdminServlet extends AdminServlet {

    GrailsAdminServlet() {
        super(new GrailsHealthCheckServlet(), new MetricsServlet(), new PingServlet(),
                new ThreadDumpServlet(), AdminServlet.DEFAULT_HEALTHCHECK_URI, AdminServlet.DEFAULT_METRICS_URI,
                AdminServlet.DEFAULT_PING_URI, AdminServlet.DEFAULT_THREADS_URI);
    }
}
