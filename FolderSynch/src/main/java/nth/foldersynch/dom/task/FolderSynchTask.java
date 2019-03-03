package nth.foldersynch.dom.task;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class FolderSynchTask {
	private String ftpUserName;
	private String ftpPassword;
	private String serverFolder;
	private String localFolder;
	private Direction direction;
	private Override override;
	private boolean includeSubFolders;
	private boolean removeFilesAndFolders;
	private String includeFilter;
	private String excludeFilter;

	@Order(value=10)
	public String getFtpUserName() {
		return ftpUserName;
	}

	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}

	@Order(value=20)
	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	@Order(value=30)
	public String getServerFolder() {
		return serverFolder;
	}

	public void setServerFolder(String serverFolder) {
		this.serverFolder = serverFolder;
	}

	@Order(value=40)
	public String getLocalFolder() {
		return localFolder;
	}

	public void setLocalFolder(String localFolder) {
		this.localFolder = localFolder;
	}

	@Order(value=50)
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Order(value=60)
	public Override getOverride() {
		return override;
	}

	public void setOverride(Override override) {
		this.override = override;
	}

	@Order(value=70)
	public boolean isIncludeSubFolders() {
		return includeSubFolders;
	}

	public void setIncludeSubFolders(boolean includeSubFolders) {
		this.includeSubFolders = includeSubFolders;
	}

	@Order(value=80)
	public boolean isRemoveFilesAndFolders() {
		return removeFilesAndFolders;
	}

	public void setRemoveFilesAndFolders(boolean removeFilesAndFolders) {
		this.removeFilesAndFolders = removeFilesAndFolders;
	}

	@Order(value=90)
	public String getIncludeFilter() {
		return includeFilter;
	}

	public void setIncludeFilter(String includeFilter) {
		this.includeFilter = includeFilter;
	}

	@Order(value=100)
	public String getExcludeFilter() {
		return excludeFilter;
	}

	public void setExcludeFilter(String excludeFilter) {
		this.excludeFilter = excludeFilter;
	}

	public String toString() {
		if (direction!=null) {
			return direction.toTitle(localFolder);
		}
		return "";
	}
	
}
