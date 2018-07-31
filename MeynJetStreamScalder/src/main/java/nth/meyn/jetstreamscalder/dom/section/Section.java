package nth.meyn.jetstreamscalder.dom.section;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public abstract class Section {

	@Order(sequenceNumber=1)
	public String getName() {
		String className=this.getClass().getSimpleName();
		String name=StringUtil.convertToNormalCase(className);
		return name;
	}
	
	public abstract int getWidthInMm();
	public abstract int getLengthInMm();
	public abstract int getWaterVolumeInLiter();
	public abstract int getEffectiveScalderLengthInMm();
	
}
