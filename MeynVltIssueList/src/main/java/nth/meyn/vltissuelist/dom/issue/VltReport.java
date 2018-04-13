package nth.meyn.vltissuelist.dom.issue;


public interface VltReport {
	boolean isReadSuccesfull();

	Boolean isContainsVltWithDeviceNet();

	Boolean isContainsVltWithWiredQuickstop();

	Boolean isAllVltsStopAndTripAfterDeviceNetError();

}
