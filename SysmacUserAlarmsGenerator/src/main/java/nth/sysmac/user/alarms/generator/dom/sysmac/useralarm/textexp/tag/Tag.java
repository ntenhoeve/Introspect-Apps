package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * Tags provide extra information be used to create {@link UserAlarm}s from
 * {@link DataType} comments. Tags are always between curly brackets {} and are
 * therefore not directly visible in an {@link UserAlarm} text.
 * 
 * @author nilsth
 *
 */
public interface Tag extends Token {

}
