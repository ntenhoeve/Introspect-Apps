package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGenerator;

/**
 * <h3>Visible component codes</h3>
 * <p>
 * You can put one or more component code's in the comment of a data type. These will all
 * be collected by the {@link SysmacUserAlarmsGenerator} and be put in the
 * beginning of the alarm message. In example:
 * <p>
 * <table border="2">
 * <tr>
 * <th colspan=3>Data Type example:</th>
 * </tr>
 * <tr>
 * <th align="left">Name</th>
 * <th align="left">Base Type</th>
 * <th align="left">Comment</th>
 * </tr>
 * <tr>
 * <td>sEvent</td>
 * <td>STRUCT</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>- AirPressure</td>
 * <td>BOOL</td>
 * <td>{110S3} Line tensioner out of position</td>
 * </tr>
 * <tr>
 * <th colspan=3>MatchResults in: 110S3 Line tensioner out of position</th>
 * </tr>
 * </table>
 * 
 * 
 * TODO array example {110S2 sc=u,110.4 sp=111} 110S2 110S6 110S8 112S2
 * 
 * <p>
 * 
 * @author nilsth
 *
 */
public class VisibleComponentCodeRule implements NodeParserRule {

	@Override
	public MatchRules getMatchRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		// TODO Auto-generated method stub

	}

}
