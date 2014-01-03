Plugin version numbers are constructed as a combination of the core metrics version plus the release of the plugin.
For example, 2.1.2-4 is the fourth release of the plugin based on version 2.1.2 of the metrics jars.

3.0.1-2: Jan 03 2014
====================
* Upgrade to metrics version 3.0.1
* Removed unused logging code and got rid of the specialized Timer.groovy class
* Add config option to turn off prepending class names to metric names
* Use grails wrapper

2.2.0-1: Oct 29 2013
====================
* Fixed [Issue #15](https://github.com/jeffellis/grails-yammer-metrics/issues/15) - Fixed config lookup, it returns a ConfigObject. Returning it as a String w/ no
config resulted in "[:]", which fails to evaluate and set the default "/metrics/*" (Pull request from @rniedzial)
* Fixed [Issue #16](https://github.com/jeffellis/grails-yammer-metrics/issues/16) - Updated to Yammer Metrics 2.2.0 and Grails 2.2.2 (Pull request from @craigforster)

2.1.2-6: Jul 12 2013
====================
* Fixed [Issue #10] - malformed metric names when deployed as a WAR (pull request from @JoeDeVries)
* AdminServlet url-pattern should be configurable (pull request from @pvblivs)

2.1.2-4: Jan 21 2013
====================

* Fixed [Issue #5:](https://github.com/jeffellis/grails-yammer-metrics/issues/5) Error in metrics servlet
* Fixed [Issue #6:](https://github.com/jeffellis/grails-yammer-metrics/issues/6) Metrics servlet broken in Grails v2.2.0-RC2

**Note:** To solve Issue #5, some package names were changed.
