package nth.meyn.connect.dom.module.settings;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;

@FontIcon(fontIconUrl=FontAwesomeUrl.COGS)
//@ServiceObjectChildren(serviceClasses={BirdTypeService.class, LocationService.class, ConfigurationService.class})
public class SettingsService {

	@DisplayName(defaultEnglish="Modify LDAP Settings")
	public void modifyLdapSettings(LdapSettings ldapSettings) {
		//TODO
	}
	
	public LdapSettings modifyLdapSettingsParameterFactory() {
		//TODO return stored settings
		return new LdapSettings();
	}
	
	public void modifyDatabaseSettings(DatabaseSettings databaseSettings) {
		//TODO
	}
	
	public DatabaseSettings modifyDatabaseSettingsParameterFactory() {
		//TODO return stored database settings
		return new DatabaseSettings();
	}
	
}
