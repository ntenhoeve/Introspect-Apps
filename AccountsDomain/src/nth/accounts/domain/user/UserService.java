package nth.accounts.domain.user;

import java.util.List;

import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class UserService {


	private UserDataAccess userDataAccess;

	public UserService() {
		//userDataAccess = (DataAccessProvider<User>) Introspect.getDataAccess(UserDataAccess.class);
		userDataAccess=new UserDataAccess();//TODO get instance via Introspect.getDataAccess
	}
	
	
	
	@GenericReturnType(User.class)
	public List<User> allUsers() throws Exception {
		return userDataAccess.getAll();
	}

	
	public void  modifyUser(User user) throws Exception {
		userDataAccess.persist(user);
	}

	
	public void  createUser(User user) throws Exception {
		userDataAccess.persist(user);
	}

	public User createUserParameterFactory() {
		return new User();
	}
	
	@FormMode(FormModeType.executeMethodAfterConformation)
	public void  deleteUser(User user) throws Exception {
		//TODO throw exception if one of the accounts holds a reference to the user to be deleted 
		userDataAccess.delete(user);
	}

}
