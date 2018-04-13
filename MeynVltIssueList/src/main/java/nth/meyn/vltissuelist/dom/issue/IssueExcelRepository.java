package nth.meyn.vltissuelist.dom.issue;

import java.util.List;

import nth.introspect.excel.repository.ExcelRepository;
import nth.introspect.layer5provider.reflection.ReflectionProvider;

public class IssueExcelRepository extends ExcelRepository<Issue> {

	public IssueExcelRepository(ReflectionProvider reflectionProvider) {
		super(reflectionProvider);
	}

	public List<Issue> readAll() throws Exception {
		return readFromTable(new IssueExcelRepositoryReader());
	}

	
	

}
