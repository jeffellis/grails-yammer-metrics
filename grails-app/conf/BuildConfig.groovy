grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

yammermetrics.version = "2.1.2"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	
	
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()


        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
    }
	
	plugins {
		build 'org.grails.plugins:release:1.0.0.RC3'

	}
	
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

		compile "com.yammer.metrics:metrics-core:${yammermetrics.version}"
		compile "com.yammer.metrics:metrics-servlet:${yammermetrics.version}"

    }
}
