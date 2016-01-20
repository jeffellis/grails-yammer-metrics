Grails Plugin for the Yammer Metrics Package
=======

Metrics provide a way to monitor performance characteristics and health of an application.  This plugin provides easy access 
to Coda Hale's metrics package from Grails applications.  For detailed information on the metrics API, see <http://metrics.codahale.com>

The plugin packages the following metrics modules:
   
   * metrics-core
   * metrics-healthchecks
   * metrics-servlets

# Usage

This plugin:

   * Wires the metrics AdminServlet to the /metrics endpoint
   * Creates a global MetricsRegistry and HealthCheckRegistry for you application and exposes them through the /metrics endpoint
   * Adds annotations to easily use meters and timers around groovy methods

## The AdminServlet

By default the plugin wires the metrics AdminServlet to the /metrics endpoint within the application.  This servlet provides an easy way
to see application metrics via a web browser or REST client.  The following endpoints are exposed:

   * **/metrics** -- A simple HTML menu providing links to the other endpoints
   * **/metrics/metrics** -- Retrieves current metric values in JSON format
   * **/metrics/ping** -- Responds with a 200 status code and the text 'pong' if the application is up and running
   * **/metrics/threads** -- Returns a thread dump of the application (basically allowing a 'kill -QUIT' via a browser)
   * **/metrics/healthcheck** -- Runs all registered healthchecks and responds with a 200 status code if the application is healthy and a 500 
     if any of the checks fail. In either case, a JSON array is returned listing each registered check, its status, and any message produced by 
     the check.

The endpoint used to access the servlet can be changed (for example to '/admin/mymetrics') by adding a line like the following to the application's
Config.groovy:

    metrics.servletUrlPattern = '/admin/mymetrics/*'

This servlet wiring can be disabled altogether with:
    
    metrics.servletEnabled = false

Metrics reporters like AdminServlet support customizable units for the rate and duration units of timers and meters.  These default to seconds but can be
changed in Config.groovy with:

    metrics.servlet.rateUnit = "days"
    metrics.servlet.durationUnit = "hours"

Any value supported by ```java.util.concurrent.TimeUnit``` are valid for these values.  Case does not matter.


# Global Registries

Version 3.0 of the core metrics package removed the static metrics and healthcheck registries delegating the creation of them to the application.
To make configuration within grails as automatic as possible, the plugin creates global MetricsRegistry and HealthCheckRegistry for use by
the application and exposes them through the AdminServlet.  To create application metrics within these registries use the ```Metrics``` and ```HealthChecks```
factories provided by the plugin.  Metrics created through annotations are added to them automatically.

For example to create a new timer metric named 'MyTimer' use:
    
    def timer = Metrics.newTimer("MyTimer")

By default the name of the class creating the metric will be prepended to the supplied name.  To disable this behavior add the following line to 
Config.groovy:

    metrics.core.prependClassName = false

# Annotations

## @Timed(name = "TimerName")

This annotation can be added to any method you wish to be timed.  @Timed uses sensible defaults to create an instance of
com.codahale.metrics.core.Timer and the associated code to update it from within your method body.  If no name is 
supplied, the timer will be named for the class and method.  In the example below the timer will be named ```org.grails.SomeService.serveTimer```

Before
```
package org.grails
class SomeService{
  public String serve(def foo, def bar) {
     return "OK";
  }
}
```

Timed the Java Way
```
package org.grails
class SomeService{
  private final Timer serveTimer = Metrics.newTimer(SomeService.class, "serveTimer", TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
  public String serve(def foo, def bar) {
    final TimerContext context = serveTimer.time();
    try {
        return "OK";
    } finally {
        context.stop();
    }
  }
}
```

Timed the Grails Way
```
package org.grails
class SomeService{
  @org.grails.plugins.metrics.groovy.Timed
  public String serve(def foo, def bar) {
    return "OK"
  }
}
```

If no name is supplied, the timer will be named for the class and method.  In the above example the timer will be named ```org.grails.SomeService.serveTimer```

## @Metered(name = "MyMeter")

This annotation can be added to any method you wish to be metered.  @Metered uses sensible defaults to create an instance of
com.codahale.metrics.core.Meter and the associated code to update it from within your method body. Name is optional.  If no name is 
supplied, the timer will be named for the class and method.  In the example below the timer will be named ```org.grails.SomeService.serveMeter```


Before

```
package org.grails  
class SomeService{  
  public String serve(def foo, def bar) {  
    return "OK";  
  }  
}   
```

Metered the Java Way
```
package org.grails
class SomeService{
  private final Meter serveMeter = Metrics.newMeter(SomeService.class, "serveMeter", "serveMeter", TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
  public String serve(def foo, def bar) {
    serveMeter.mark();
    return "OK";
  }
}
```

Metered the Grails Way

```
package org.grails
class SomeService{

  @org.grails.plugins.metrics.groovy.Metered
  public String serve(def foo, def bar) {
    return "OK"
  }
}
```

Note: Annotations can be safely combined.

# Example App

To see an example of how metrics works in an application, clone this repo and then (instruction for mac or linux):

```
cd example-app  
./grailsw run-app
```

Then open http://localhost:8080/metricsTest/metrics in a browser to access the AdminServlet menu.  Create the metrics an peg a meter and timer by hitting:
http://localhost:8080/metricsTest/peg/timed or http://localhost:8080/metricsTest/peg/metered

# License

This plugin is
 Copyright (c) 2013 Jeff Ellis

 Published under Apache Software License 2.0, see LICENSE

The metrics jars are:
 Copyright (c) 2010-2013 Coda Hale, Yammer.com

 Published under Apache Software License 2.0
