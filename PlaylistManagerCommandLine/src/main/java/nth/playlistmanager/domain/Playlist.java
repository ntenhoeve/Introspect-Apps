package nth.playlistmanager.domain;

import java.io.File;
import java.util.List;

import nth.playlistmanager.domain.util.FileUtil;

public class Playlist {

	public static final String PLAYLIST_FILE_EXTENTION = "m3u";
	public final static String INCLUDE_PREFIX = "#INCLUDE=";
	private final File file;
	private List<File> inclusionPaths;
	private List<File> musicFiles;

	public Playlist(File file) {
		this.file = file;
	}

	public String getName() {
		return FileUtil.getFileNameWithoutExtention(file);
	}
	
	public void setName(String name) {
		//Read only: name is defined by file
	}
	

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		//Read only: name is defined in constructor
	}
	

	public static boolean isPlaylist(File file) {
		if (!file.isFile()) return false;
		String extention = FileUtil.getExtention(file).toLowerCase();
		boolean validExtention = PLAYLIST_FILE_EXTENTION.equals(extention);
		return validExtention && file.isFile();
	}
	
	

	public List<File> getInclusionPaths() {
		return inclusionPaths;
	}

	public void setInclusionPaths(List<File> inclusionPaths) {
		this.inclusionPaths = inclusionPaths;
	}

	public List<File> getMusicFiles() {
		return musicFiles;
	}

	public void setMusicFiles(List<File> musicFiles) {
		this.musicFiles = musicFiles;
	}



}
