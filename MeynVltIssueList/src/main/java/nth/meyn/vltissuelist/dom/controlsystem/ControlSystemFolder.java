package nth.meyn.vltissuelist.dom.controlsystem;


public class ControlSystemFolder {

//	public ControlSystemFolder(File controlSystemFolder) {
//		this.controlSystemFolder = controlSystemFolder;
//	}
//
//	public VltFile findLatestVltFile() {
//		List<File> vltFiles = findAllVltFiles(controlSystemFolder);
//		File latestModifiedFile = findLastModifiedFile(vltFiles);
//		if (latestModifiedFile == null) {
//			return null;
//		} else {
//			try {
//				return new VltFile(latestModifiedFile);
//			} catch (IOException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//	}
//
//	private File findLastModifiedFile(List<File> vltFiles) {
//		File lastModifiedFile = null;
//		for (File file : vltFiles) {
//			if (lastModifiedFile == null
//					|| file.lastModified() >= lastModifiedFile.lastModified()) {
//				lastModifiedFile = file;
//			}
//		}
//		return lastModifiedFile;
//	}
//
//	private List<File> findAllVltFiles(File folder) {
//		List<File> vltFiles = new ArrayList<>();
//		File[] children = folder.listFiles();
//		if (children != null) {
//			for (File file : children) {
//				if (file.isDirectory()) {
//					vltFiles.addAll(findAllVltFiles(file));
//				} else if (file.isFile()
//						&& file.getName().toLowerCase()
//								.endsWith(VltFile.EXTENTION)) {
//					vltFiles.add(file);
//				}
//			}
//		}
//		return vltFiles;
//	}
}
