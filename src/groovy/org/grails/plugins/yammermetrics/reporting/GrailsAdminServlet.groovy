package org.grails.plugins.yammermetrics.reporting

import com.yammer.metrics.reporting.AdminServlet
import com.yammer.metrics.reporting.MetricsServlet
import com.yammer.metrics.reporting.PingServlet
import com.yammer.metrics.reporting.ThreadDumpServlet

class GrailsAdminServlet extends AdminServlet {

    GrailsAdminServlet() {
        super(new GrailsHealthCheckServlet(), new MetricsServlet(), new PingServlet(),
                new ThreadDumpServlet(), DEFAULT_HEALTHCHECK_URI, DEFAULT_METRICS_URI,
                DEFAULT_PING_URI, DEFAULT_THREADS_URI);
    }
}
