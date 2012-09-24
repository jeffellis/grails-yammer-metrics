package com.yammer.metrics.reporting

class GrailsAdminServlet extends AdminServlet {

    GrailsAdminServlet() {
        super(new GrailsHealthCheckServlet(), new MetricsServlet(), new PingServlet(),
                new ThreadDumpServlet(), DEFAULT_HEALTHCHECK_URI, DEFAULT_METRICS_URI,
                DEFAULT_PING_URI, DEFAULT_THREADS_URI);
    }
}
