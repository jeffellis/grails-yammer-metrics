Grails Plugin for the Yammer Metrics Package
=======

Provides the following features:
   * metrics-core
   * metrics-servlet

This plugin wires the AdminServlet to the /metrics endpoint in the including application. To disable this functionality,
add the following to the application's Config:
```
    metrics.servletEnabled = false
```
To customize the url pattern of the AdminServlet to something different than /metrics add the following Config param:
```
    metrics.servletUrlPattern = '/my/custom/metrics/url/*'
```

For detailed documentation on the yammer metrics package see: http://metrics.codahale.com/index.html

Annotations
-------
@Timed
This annotation can be added to any method you wish to be timed.  @Timed uses sensible defaults to create an instance of
com.codahale.metrics.core.Timer and the associated code to update it from within your method body.

Before
```
class SomeService{
  public String serve(def foo, def bar) {
     return "OK";
  }
}
```

Timed the Java Way
```
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
class SomeService{
  @org.grails.plugins.metrics.groovy.Timed
  public String serve(def foo, def bar) {
    return "OK"
  }
}
```

@Metered
This annotation can be added to any method you wish to be metered.  @Metered uses sensible defaults to create an instance of
com.codahale.metrics.core.Meter and the associated code to update it from within your method body.


Before
```
class SomeService{
  public String serve(def foo, def bar) {
    return "OK";
  }
}
```

Metered the Java Way
```
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
class SomeService{

  @org.grails.plugins.metrics.groovy.Metered
  public String serve(def foo, def bar) {
    return "OK"
  }
}
```

Note: Annotations can be safely combined.

TODO
-------
 * Annotations to support other yammer metrics
 * Automatic controller or service metrics
 * Some gui component (different plugin?)


License
-------

This plugin is
 Copyright (c) 2013 Jeff Ellis

 Published under Apache Software License 2.0, see LICENSE

The metrics jars are:
 Copyright (c) 2010-2013 Coda Hale, Yammer.com

 Published under Apache Software License 2.0
