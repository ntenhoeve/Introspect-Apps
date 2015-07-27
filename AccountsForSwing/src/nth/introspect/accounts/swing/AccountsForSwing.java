package nth.introspect.accounts.swing;

import java.util.ArrayList;
import java.util.List;

import nth.accounts.domain.account.AccountService;
import nth.accounts.domain.repository.AccountRepository;
import nth.accounts.domain.user.UserService;
import nth.introspect.generic.xml.XmlConverter;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;

public class AccountsForSwing extends IntrospectApplicationForSwing {

	public AccountsForSwing(String[] args) {
		super(args);
	}

	public static void main(String[] args) {
		new AccountsForSwing(args);
	}

	public List<Class<?>> getServiceClasses() {
		List<Class<?>> serviceClasses = new ArrayList<Class<?>>();
		serviceClasses.add(AccountService.class);
		serviceClasses.add(UserService.class);
		return serviceClasses;
	}

	public List<Class<?>> getInfrastructureClasses() {
		List<Class<?>> infrastructureClasses = new ArrayList<Class<?>>();
		infrastructureClasses.add(AccountRepository.class);
		infrastructureClasses.add(XmlConverter.class);
		return infrastructureClasses;
	}
}
