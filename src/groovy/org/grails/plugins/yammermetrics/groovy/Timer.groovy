/*
 * Copyright 2012 Jeff Ellis
 */
package org.grails.plugins.yammermetrics.groovy

import org.apache.commons.logging.LogFactory

class Timer {

	@Delegate private com.codahale.metrics.Timer timerMetric
	String owner
	String name

	private static final log = LogFactory.getLog( Timer )
	private ownerLog

	Timer( Class<?> owner, String name ) {
		this.owner = owner.name
		this.name = name

		ownerLog = LogFactory.getLog( owner )
		timerMetric = Metrics.newTimer( owner, name)
	}

	def time( Closure closure ) {
		def oldMax = timerMetric.max()
        com.codahale.metrics.Timer.Context tc = timerMetric.time()
		try {
			closure.call()
		} finally {
			tc.stop()
		}
		if( timerMetric.max() > oldMax ) {
			onNewMax.call()
		}
	}

	def time( String maxMessage, Closure closure ) {
		def oldMax = timerMetric.max()
		this.time( closure )
		if( timerMetric.max() > oldMax ) {
			ownerLog.info( "${name} -- New maximum of ${max()} ${durationUnit()} set")
			ownerLog.info( maxMessage )
		}
	}

	def onNewMax = {
	}

}
