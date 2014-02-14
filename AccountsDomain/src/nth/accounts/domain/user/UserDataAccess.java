package nth.accounts.domain.user;

import java.util.List;

import nth.accounts.domain.repository.AccountsRepository;
import nth.accounts.domain.repository.ObjectRepositoryForDifferentTypes;
import nth.introspect.filter.Filter;

public class UserDataAccess  {

	private ObjectRepositoryForDifferentTypes repository;

	public UserDataAccess() {
		repository = AccountsRepository.getInstance(); 
	}
	
	public List<User> getAll() throws Exception {
		Filter<Object> typeFilter=new Filter<Object>() {
			
			@Override
			public boolean isMatch(Object obj) {
				return User.class== obj.getClass();
			}
		};
		return (List<User>) repository.getAll(typeFilter);
	}
	
	public void persist(User user) throws Exception {
		repository.persist(user);
	}
	
	public void delete(User user) throws Exception {
		//TODO throw exception if one of the accounts hold a reference to the user to be deleted 
		repository.delete(user);
	}


}
