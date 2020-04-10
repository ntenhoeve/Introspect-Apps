package nth.meyn.vltissuelist.dom.issue;

import nth.meyn.vltissuelist.dom.vlt.VltFile;


public class VltReportImpl implements VltReport {

	private boolean readSuccesfull;
	private Boolean containsVltWithDeviceNet;
	private Boolean containsVltWithWiredQuickstop;
	private Boolean allVltsStopAndTripAfterDeviceNetError;

	
	
	public VltReportImpl(boolean readSuccesfull, Boolean containsVltWithDeviceNet,
			Boolean containsVltWithWiredQuickstop, Boolean allVltsStopAndTripAfterDeviceNetError) {
		this.readSuccesfull = readSuccesfull;
		this.containsVltWithDeviceNet = containsVltWithDeviceNet;
		this.containsVltWithWiredQuickstop = containsVltWithWiredQuickstop;
		this.allVltsStopAndTripAfterDeviceNetError = allVltsStopAndTripAfterDeviceNetError;
	}

	
	public VltReportImpl(VltFile vltFile)  {
		this.readSuccesfull = vltFile.isReadSuccesfull();
		this.containsVltWithDeviceNet = vltFile.isContainsVltWithDeviceNet();
		this.containsVltWithWiredQuickstop = vltFile.isContainsVltWithWiredQuickstop();
		this.allVltsStopAndTripAfterDeviceNetError = vltFile.isAllVltsStopAndTripAfterDeviceNetError();
	}

	@Override
	public boolean isReadSuccesfull() {
		return readSuccesfull;
	}

	@Override
	public Boolean isContainsVltWithDeviceNet() {
		return containsVltWithDeviceNet;
	}

	@Override
	public Boolean isContainsVltWithWiredQuickstop() {
		return containsVltWithWiredQuickstop;
	}

	@Override
	public Boolean isAllVltsStopAndTripAfterDeviceNetError() {
		return allVltsStopAndTripAfterDeviceNetError;
	}

	public void and(VltReport vltReport) {
		if (!vltReport.isReadSuccesfull()) {
			readSuccesfull=false;
		}
		if (vltReport.isContainsVltWithDeviceNet()) {
			containsVltWithDeviceNet=true;
		}
		if (vltReport.isContainsVltWithWiredQuickstop()) {//TODO
			containsVltWithWiredQuickstop=true;
		}
		if (!vltReport.isAllVltsStopAndTripAfterDeviceNetError()) {
			allVltsStopAndTripAfterDeviceNetError=false;
		}
	}
}
