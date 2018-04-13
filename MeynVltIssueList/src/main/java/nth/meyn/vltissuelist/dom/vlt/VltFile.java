package nth.meyn.vltissuelist.dom.vlt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.vltissuelist.dom.issue.VltReport;

public class VltFile implements VltReport{
	public static final String EXTENTION = ".ssp";
	private File vltParameterFile;
	private final List<Vlt> vlts;

	public VltFile(File vltParameterFile) throws IOException {
		this.vltParameterFile = vltParameterFile;
		this.vlts=read();
	}

	public List<Vlt> read() throws IOException {
		byte[] data = Files.readAllBytes(vltParameterFile.toPath());

		List<Vlt> vlts = new ArrayList<>();
		readParameter(data, vlts, Vlt.PARAMETER_515_TERMINAL33);
		readParameter(data, vlts, Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT);
		readParameter(data, vlts, Vlt.PARAMETER_1000_CAN_PROTOCOL);

		return vlts;
	}

	@Override
	public boolean isReadSuccesfull() {
		return vlts.size()>0;
	};
	
	@Override
	public Boolean isContainsVltWithDeviceNet() {
		for (Vlt vlt : vlts) {
			if (vlt.usesDeviceNet()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isContainsVltWithWiredQuickstop() {
		for (Vlt vlt : vlts) {
			if (vlt.usesQuickStopFromPlc()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return vlt1.usesQuickStopFromPlc=0 vlt2.usesQuickStopFromPlc=0 = 0
	 *         vlt1.usesQuickStopFromPlc=0 vlt2.usesQuickStopFromPlc=1 = 0
	 *         vlt1.usesQuickStopFromPlc=1 vlt2.usesQuickStopFromPlc=0 = 0
	 *         vlt1.usesQuickStopFromPlc=1 vlt2.usesQuickStopFromPlc=1 = 1
	 * 
	 * @throws IOException
	 */
	@Override
	public Boolean isAllVltsStopAndTripAfterDeviceNetError() {
		if (vlts.size()==0) {
			return false;
		}
		for (Vlt vlt : vlts) {
			if (!vlt.stopsAndTrips()) {
				return false;
			}
		}
		return true;
	}

	private void readParameter(byte[] data, List<Vlt> vlts, int parameterNr) {

		ArrayFinder arrayFinder = new ArrayFinder(data);
		ParameterArrayMatcher matcher;
		int matcherNr = 0;
		int vltNr = 0;
		List<ParameterArrayMatcher> matchers = ParameterArrayMatcherFactory
				.create(parameterNr);
		do {
			matcher = arrayFinder.findNext(matchers.get(matcherNr));
			if (matcher != null) {
				int parameter = matcher.getParameter();
				int setup = matcher.getSetup();
				byte value = arrayFinder.getNextValue();

				Vlt vlt = getOrCreateVlt(vlts, vltNr);
				vlt.getSetup(setup).getParameters().put(parameter, value);

				matcherNr++;
				if (matcherNr >= matchers.size()) {
					matcherNr = 0;
					vltNr++;
				}
			}
		} while (matcher != null);
	}

	private Vlt getOrCreateVlt(List<Vlt> vlts, int vltNr) {
		Vlt vlt;
		if (vltNr >= vlts.size()) {
			vlt = new Vlt();
			vlts.add(vlt);
		} else {
			vlt = vlts.get(vltNr);
		}
		return vlt;
	}

	public List<String> printCompare(File otherVltParameterFile)
			throws IOException {
		byte[] data1 = Files.readAllBytes(vltParameterFile.toPath());
		byte[] data2 = Files.readAllBytes(otherVltParameterFile.toPath());

		List<String> results = new ArrayList<>();
		for (int index = 0; index < data1.length; index++) {
			results.add(dataToString(data1, data2, index));
			System.out.println(index);
		}

		return results;
	}

	private String dataToString(byte[] data1, byte[] data2, int index) {
		StringBuilder reply = new StringBuilder();
		reply.append(String.format("%02X", data1[index]));
		if (data1[index] == data2[index]) {
			reply.append(" ");
		} else {
			reply.append("!");
		}
		// reply.append(String.format("%02X", data2[index]));
		return reply.toString();
	}

	public File getFile() {
		return vltParameterFile;
	}

	public  List<Vlt> getVlts() {
		return vlts;
	}

}
