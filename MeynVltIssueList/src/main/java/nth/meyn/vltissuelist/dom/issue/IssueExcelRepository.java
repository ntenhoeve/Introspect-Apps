package nth.meyn.vltissuelist.dom.issue;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.infra.excel.repository.ExcelRepository;

public class IssueExcelRepository extends ExcelRepository<Issue> {

	public IssueExcelRepository(ReflectionProvider reflectionProvider) {
		super(reflectionProvider);
	}

	public List<Issue> readAll() throws Exception {
		return readFromTable(new IssueExcelRepositoryReader());
	}

	
	

}
