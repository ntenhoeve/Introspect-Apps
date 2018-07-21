package nth.foldersynch.dom.task;

public enum Direction {
	COPY_LOCAL_TO_SERVER ("Copy \"%s\" to server"),
	MOVE_LOCAL_TO_SERVER("Move \"%s\" to server"),
	COPY_SERVER_TO_LOCAL("Copy server to \"%s\""),
	MOVE_SERVER_TO_LOCAL("Move server to \"%s\""),
	COPY_BOTH_WAYS	("Copy \"%s\" to server and back");

	private String titleFormat;
	
	private Direction(String titleFormat) {
		this.titleFormat = titleFormat;
	}

	public String toTitle(String localFolder) {
		return String.format(titleFormat , localFolder );
	}
}
