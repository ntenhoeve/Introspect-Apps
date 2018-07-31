package nth.introspect.accounts.swing;

import java.util.ArrayList;
import java.util.List;

import nth.accounts.domain.account.AccountService;
import nth.accounts.domain.repository.AccountRepository;
import nth.accounts.domain.tag.TagService;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;
import nth.reflect.infra.generic.xml.XmlConverter;

public class AccountsForSwing extends ReflecttApplicationForSwing {

	public static void main(String[] args) {
		launch();
	}

	public List<Class<?>> getServiceClasses() {
		List<Class<?>> serviceClasses = new ArrayList<Class<?>>();
		serviceClasses.add(AccountService.class);
		serviceClasses.add(TagService.class);
		return serviceClasses;
	}

	public List<Class<?>> getInfrastructureClasses() {
		List<Class<?>> infrastructureClasses = new ArrayList<Class<?>>();
		infrastructureClasses.add(AccountRepository.class);
		infrastructureClasses.add(XmlConverter.class);
		return infrastructureClasses;
	}
}
