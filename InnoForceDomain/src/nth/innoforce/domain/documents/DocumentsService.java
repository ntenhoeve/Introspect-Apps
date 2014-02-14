package nth.innoforce.domain.documents;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.innoforce.domain.project.Project;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class DocumentsService {

	private static File PROJECT_ROOT_FOLDER = null;
	private File PROJECTS_RUNNING;
	private File PROJECTS_ONHOLD;
	private File PROJECTS_KILLED;
	private File PROJECTS_FINISHED;
	private File[] PROJECT_PARENT_FOLDERS;

	public DocumentsService() {
		PROJECT_ROOT_FOLDER = new File("//meynfile.meyn.nl/nilsth/STARDOCUMENTS/REND_PROJECTS");//TODO get username from Introspect.getAuthrizationPort
		PROJECTS_RUNNING = new File(PROJECT_ROOT_FOLDER + "/Projects Running");
		PROJECTS_ONHOLD = new File(PROJECT_ROOT_FOLDER + "/Projects On Hold");
		PROJECTS_KILLED = new File(PROJECT_ROOT_FOLDER + "/Projects Killed");
		PROJECTS_FINISHED = new File(PROJECT_ROOT_FOLDER + "/Projects Finished");
		PROJECT_PARENT_FOLDERS = new File[] {PROJECTS_RUNNING, PROJECTS_ONHOLD, PROJECTS_FINISHED, PROJECTS_KILLED};
	}

	@FormMode(FormModeType.executeMethodDirectly)
	public URI viewDocument(Document document) {
		return document.getFile().toURI();
	}

	@GenericReturnType(Document.class)
	@FormMode(FormModeType.executeMethodDirectly)
	public List<Document> findBusinessCases(Project project) throws Exception {
		URI projectFolder = findProjectFolder(project);
		List<Document> documents = findDocuments(new File(projectFolder), new String[] {"business", "case"});
		return documents;
	}

	@GenericReturnType(Document.class)
	@FormMode(FormModeType.executeMethodDirectly)
	public List<Document> findHighLightReports(Project project) throws Exception {
		URI projectFolder = findProjectFolder(project);
		List<Document> documents = findDocuments(new File(projectFolder), new String[] {"high", "light"});
		return documents;
	}

	@GenericReturnType(Document.class)
	@FormMode(FormModeType.executeMethodDirectly)
	public List<Document> findDeliverablesActionablesBotleneckLists(Project project) throws Exception {
		URI projectFolder = findProjectFolder(project);
		List<Document> documents = findDocuments(new File(projectFolder), new String[] {"dab"});
		return documents;
	}

	@FormMode(FormModeType.executeMethodDirectly)
	public URI findProjectFolder(Project project) throws Exception {
		String projectNr = project.getNumber().toString();
		for (File projectParentFolder : PROJECT_PARENT_FOLDERS) {
			for (File file : projectParentFolder.listFiles()) {
				if (file.isDirectory() && file.getName().contains(projectNr)) {
					return file.toURI();
				}
			}
		}
		//TODO throw an error message when there are multiple folder with the same project numbers
		throw new RuntimeException("Could not find the project folder with project number "+projectNr+" in " + PROJECT_ROOT_FOLDER);
	}

	
	//TODO public void List<Message> showDocumentStatusOfActiveProjects() {}
	
	private List<Document> findDocuments(File folder, String[] fileNamePartials) {
		ArrayList<Document> foundDocuments = new ArrayList<Document>();
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				boolean found = true;
				for (String fileNamePartial : fileNamePartials) {
					if (!file.getName().toLowerCase().contains(fileNamePartial.toLowerCase())) {
						found = false;
						break;
					}
				}
				if (found) {
					foundDocuments.add(new Document(file));
				}
			} else if (file.isDirectory()) {
				// recursive calls for child folders
				foundDocuments.addAll(findDocuments(file, fileNamePartials));
			}
		}
		return foundDocuments;

	}

	// TODO create a Document class that wraps a URL: name, parent, children and URL
	// TODO show list of documents in a treetable.
	// TODO add open(Document document) and download(Document document) methods and add a openTitle(Object obj) method that dynamically returns open {document name}
}
