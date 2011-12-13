package com.yammer.metrics.groovy

import com.yammer.metrics.Metrics
import com.yammer.metrics.core.TimerContext
import java.util.concurrent.TimeUnit
import org.apache.commons.logging.LogFactory

class TimerMetric {

	@Delegate private com.yammer.metrics.core.TimerMetric timerMetric
	String owner
	String name

	private static final log = LogFactory.getLog( com.yammer.metrics.groovy.TimerMetric )
	private ownerLog

	TimerMetric( Class<?> owner, String name ) {
		this.owner = owner.name
		this.name = name

		ownerLog = LogFactory.getLog( owner )
		timerMetric = Metrics.newTimer( owner, name,
			TimeUnit.MILLISECONDS, TimeUnit.SECONDS )
	}

	def time( Closure closure ) {
		def oldMax = timerMetric.max()
		TimerContext tc = timerMetric.time()
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
