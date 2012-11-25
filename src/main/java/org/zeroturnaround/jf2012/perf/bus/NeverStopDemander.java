package org.zeroturnaround.jf2012.perf.bus;

public class NeverStopDemander implements StopDemander {
	
	public static final StopDemander INSTANCE = new NeverStopDemander();
	
	private NeverStopDemander() {
	}

	public boolean isDemandingStop() {
		return false;
	}

}
