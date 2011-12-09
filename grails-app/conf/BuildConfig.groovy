grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	
	
	//
	// Here is where we will publish ourselves to in nexus
	//
	grails.project.repos.rally.url 	= "http://alm-build.f4tech.com:8080/nexus/content/repositories/releases/"
	grails.project.repos.default 	= "rally"
	
	
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

//		mavenRepo "http://repo.codahale.com"

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
	
	plugins {
		build 'org.grails.plugins:release:1.0.0.RC3'

	}
	
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

//		runtime 'com.yammer.metrics:metrics-core:2.0.0-BETA18-SNAPSHOT'
//		runtime 'com.yammer.metrics:metrics-servlet:2.0.0-BETA18-SNAPSHOT'
    }
}
