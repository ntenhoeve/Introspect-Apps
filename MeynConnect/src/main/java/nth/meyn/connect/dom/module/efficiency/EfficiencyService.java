package nth.meyn.connect.dom.module.efficiency;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import nth.meyn.connect.dom.asset.Asset;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

@FontIcon(fontIconUrl=FontAwesomeUrl.LINE_CHART)
@DisplayName(defaultEnglish = "Efficiency")
public class EfficiencyService {

	@Order(value=10)
	public List<EfficiencyOverview> efficiencyOverviews() {
		return EfficiencyOverviewExampleFactory.create();
	}

	@Order(value=20)
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public EfficiencyGraph efficiencyGraph(EfficiencyOverview efficiencyOverview) {
		return efficiencyGraph(efficiencyOverview.getAsset());
	}
	
	@Order(value=21)
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public EfficiencyGraph efficiencyGraph(Asset asset) {
		return new EfficiencyGraph();
	}
	
	@Order(value=31)
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public TroughputGraph troughputGraph(EfficiencyOverview efficiencyOverview) {
		return troughputGraph(efficiencyOverview.getAsset());
	}
	
	@Order(value=32)
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public TroughputGraph troughputGraph(Asset asset) {
		return new TroughputGraph();
	}


	@Order(value=41)
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public List<EfficiencyIssue> efficiencyIssues(EfficiencyOverview efficiencyOverview) {
		return efficiencyIssues(efficiencyOverview.getAsset());
	}
	
	@Order(value=42)
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public List<EfficiencyIssue> efficiencyIssues(Asset asset) {
		return new ArrayList<>();
	}

	@ParameterFactory
	public List<EfficiencyIssue> efficiencyIssues(EfficiencyIssueFilter efficiencyIssueFilter) {
		return new ArrayList<>();
	}

}
