package nth.accounts.domain.tag;

import java.util.List;

import nth.accounts.domain.repository.AccountRepository;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;

public class TagService {

	private AccountRepository accountRepository;

	public TagService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public List<Tag> allTags() throws Exception {
		return (List<Tag>) accountRepository.getAll(Tag.class);
	}

	public void modifyTag(Tag tag) throws Exception {
		accountRepository.persist(tag);
	}

	public void createTag(Tag tag) throws Exception {
		accountRepository.persist(tag);
	}

	public Tag createTagParameterFactory() {
		return new Tag();
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteTag(Tag tag) throws Exception {
		// TODO throw exception if one of the accounts holds a reference to the
		// user to be deleted
		accountRepository.delete(tag);
	}

}
