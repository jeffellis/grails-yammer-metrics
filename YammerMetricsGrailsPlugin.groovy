class YammerMetricsGrailsPlugin {

	String groupId = 'com.rallydev.plugins.grails'

    // the plugin version
    def version = "2.1.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Jeff Ellis"
    def authorEmail = "jellis@rallydev.com"
    def title = "Grails plugin to package Coda Hale's yammer metrics jars"
    def description = '''\\
Provides the following features:
   * metrics-core
   * metrics-servlet (wired to the /metrics end point for the app).

For detailed documentation on the yammer metrics package see:

http://metrics.codahale.com/index.html
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/yammer-metrics"

    def doWithWebDescriptor = { xml ->

		def count = xml.'servlet'.size()
		if(count > 0) {

			def servletElement = xml.'servlet'[count - 1]

			servletElement + {
				'servlet' {
					'servlet-name'("YammerMetrics")
					'servlet-class'("com.yammer.metrics.reporting.AdminServlet")
				}
			}
			println "***\nYammerMetrics servlet injected into web.xml"
		}

		count = xml.'servlet-mapping'.size()
		if(count > 0) {
			def servletMappingElement = xml.'servlet-mapping'[count - 1]
			servletMappingElement + {

				'servlet-mapping' {
					'servlet-name'("YammerMetrics")
					'url-pattern'("/metrics/*")
				}
			}
			println "YammerMetrics Admin servlet filter-mapping (for /metrics/*) injected into web.xml\n***"
		}

    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
