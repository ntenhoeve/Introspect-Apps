package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml;

import java.time.LocalTime;

import com.sun.prism.PixelFormat.DataType;

import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

/**
 * See <a href=
 * "http://industrial.omron.com.br/uploads/arquivos/W501E101_NJ_CPU_Software_UsersMan.pdf">W501E101_NJ_CPU_Software_UsersMan.pdf
 * - Chapter Constant types</a>
 * 
 * @author nilsth
 *
 */
public class SysmacConstant {

	private final SysmacDataType type;
	private final String value;

	public SysmacConstant(SysmacDataType type, String value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		if (type==SysmacDataType.TIME || type ==SysmacDataType.DATE) {
			reply.append(type.toString().substring(0, 1));
		} else {
			reply.append(type.toString());
		}
		reply.append("#");
		reply.append(value);
		return reply.toString();
	}

	public static SysmacConstant createTime(int tenthsOfSeconds) {
		LocalTime localTime = LocalTime.ofSecondOfDay(tenthsOfSeconds / 10);
		int millis = tenthsOfSeconds % 10 * 100;
		StringBuilder time = new StringBuilder();
		if (localTime.getHour() > 0) {
			time.append(localTime.getHour());
			time.append("h");
		}
		if (localTime.getMinute() > 0) {
			time.append(localTime.getMinute());
			time.append("m");
		}
		if (localTime.getSecond() > 0) {
			time.append(localTime.getSecond());
			time.append("s");
		}
		if (millis > 0) {
			time.append(millis);
			time.append("ms");
		}
		return new SysmacConstant(SysmacDataType.TIME, time.toString());
	}
}
