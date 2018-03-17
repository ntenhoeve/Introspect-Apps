package nth.meyn.connect.dom.module.settings;

import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.layer5provider.reflection.behavior.serviceobjectchildren.ServiceObjectChildren;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.meyn.connect.dom.arrival.flock.BirdTypeService;
import nth.meyn.connect.dom.arrival.location.LocationService;

@Icon(iconURL=FontAwesomeUrl.COGS)
@ServiceObjectChildren(serviceClasses={BirdTypeService.class, LocationService.class, ConfigurationService.class})
public class SettingsService {

	@DisplayName(englishName="Modify LDAP Settings")
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
