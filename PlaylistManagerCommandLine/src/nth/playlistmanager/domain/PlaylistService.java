package nth.playlistmanager.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.info.valuemodel.annotations.GenericReturnType;
import nth.playlistmanager.domain.util.FileUtil;

public class PlaylistService {

	@GenericReturnType(Playlist.class)
	public List<Playlist> allPlaylists(Source source) {
		List<Playlist> playlists = new ArrayList<Playlist>();
		File fileOrFolder = source.getFileOrFolder();
		if (fileOrFolder.isDirectory()) {
			File[] children = fileOrFolder.listFiles();
			for (File child : children) {
				playlists.addAll(allPlaylists(new Source(child)));// recursive call
			}
		} else if (Playlist.isPlaylist(fileOrFolder)) {
			Playlist playlist = readPlaylist(fileOrFolder);
			playlists.add(playlist);
		}
		return playlists;
	}

	public void createPlaylists(SourceAndDestination sourceAndDestination) {
		List<File> foldersWithMusicFiles = findFolderWithMusicFiles(sourceAndDestination.getSourceFolder());
		for (File folderWithMusicFile : foldersWithMusicFiles) {
			// create playlist name
			String relativePath = relativePath(sourceAndDestination.getDestinationFolder().getAbsolutePath(), folderWithMusicFile.getAbsolutePath());
			String playlistName = relativePath.replace("../", "").replace("/", "-");
			String playListFileName = playlistName + "." + Playlist.PLAYLIST_FILE_EXTENTION;
			File playlistFile = new File(sourceAndDestination.getDestinationFolder().getAbsolutePath(), playListFileName);

			// create playlist file
			Playlist playlist = new Playlist(playlistFile);

			// add inclusion path
			List<File> inclusionPaths = new ArrayList<File>();
			playlist.setInclusionPaths(inclusionPaths);
			inclusionPaths.add(folderWithMusicFile);

			// add music files
			List<File> musicFiles = new ArrayList<File>();
			playlist.setMusicFiles(musicFiles);
			for (File child : folderWithMusicFile.listFiles()) {
				if (FileUtil.isMusicFile(child)) {
					musicFiles.add(child);
				}
			}

			// save to disk
			savePlaylist(playlist);
		}

	}

	private static List<File> findFolderWithMusicFiles(File path) {
		List<File> foldersWithMusicFiles = new ArrayList<File>();
		File[] childen = path.listFiles();
		for (File child : childen) {
			if (child.isDirectory()) {
				// recursive call for sub directories
				foldersWithMusicFiles.addAll(findFolderWithMusicFiles(child));
			} else if (FileUtil.isMusicFile(child)) {
				// found a music file so return the parent
				foldersWithMusicFiles.add(path);
				return foldersWithMusicFiles;
			}
		}
		return foldersWithMusicFiles;
	}

	public void updatePlaylists(Source source) {
		List<Playlist> playlists = allPlaylists(source);
		for (Playlist playlist : playlists) {
			updatePlaylist(playlist);
		}
	}

	private static void updatePlaylist(Playlist playlist) {
		List<File> inclusionPaths = playlist.getInclusionPaths();
		List<File> musicFiles = playlist.getMusicFiles();
		musicFiles.clear();
		for (File path : inclusionPaths) {
			musicFiles.addAll(findMusicFiles(path));
		}
		savePlaylist(playlist);
	}

	private static void savePlaylist(Playlist playlist) {
		String NEWLINE = "\r\n";
		StringBuffer content = new StringBuffer("#EXTM3U");
		content.append(NEWLINE);
		String PLAYLIST_PATH = playlist.getFile().getParentFile().getAbsolutePath();
		for (File inclusion : playlist.getInclusionPaths()) {
			content.append(Playlist.INCLUDE_PREFIX);
			content.append(relativePath(PLAYLIST_PATH, inclusion.getAbsolutePath()));
			content.append(NEWLINE);
		}

		for (File musicFile : playlist.getMusicFiles()) {
			content.append("#EXTINF:,");
			content.append(FileUtil.getFileNameWithoutExtention(musicFile).trim().replace(" - ", "-").replace("-", " - "));
			content.append(NEWLINE);

			content.append(relativePath(PLAYLIST_PATH, musicFile.getPath()));
			content.append(NEWLINE);
		}

		FileUtil.writeFile(playlist.getFile(), content.toString());
	}

	private static String relativePath(String absolutePath, String path) {
		String[] absoluteDirectories = absolutePath.replace("\\", "/").split("/");
		String[] relativeDirectories = path.replace("\\", "/").split("/");

		// Get the shortest of the two paths
		int length = absoluteDirectories.length < relativeDirectories.length ? absoluteDirectories.length : relativeDirectories.length;

		// Use to determine where in the loop we exited
		int lastCommonRoot = -1;
		int index;

		// Find common root
		for (index = 0; index < length; index++) {
			if (absoluteDirectories[index].equals(relativeDirectories[index]))
				lastCommonRoot = index;
			else
				break;
		}

		// If we didn't find a common prefix then throw
		if (lastCommonRoot == -1)
			throw new IllegalArgumentException("Paths do not have a common base");

		// Build up the relative path
		StringBuilder relativePath = new StringBuilder();

		// Add on the ..
		for (index = lastCommonRoot + 1; index < absoluteDirectories.length; index++)
			if (absoluteDirectories[index].length() > 0)
				relativePath.append("../");

		// Add on the folders
		for (index = lastCommonRoot + 1; index < relativeDirectories.length - 1; index++)
			relativePath.append(relativeDirectories[index] + "/");
		relativePath.append(relativeDirectories[relativeDirectories.length - 1]);

		return relativePath.toString();
	}

	private static Playlist readPlaylist(File playlistFile) {
		if (!Playlist.isPlaylist(playlistFile))
			throw new IllegalArgumentException(playlistFile.getAbsolutePath() + " is not a valid playlist file.");

		Playlist playlist = new Playlist(playlistFile);

		readInclusionPaths(playlist);

		readMusicFiles(playlist);
		return playlist;

	}

	private static void readMusicFiles(Playlist playlist) {
		List<File> musicFiles = new ArrayList<File>();
		File playlistFile = playlist.getFile();
		List<String> lines = FileUtil.readFile(playlistFile);
		for (String line : lines) {
			if (!line.startsWith("#")) {
				String relativePath = line;
				String parentPath = playlistFile.getParent();
				File menuItem = new File(parentPath, relativePath);
				musicFiles.add(menuItem);
			}
		}
		playlist.setMusicFiles(musicFiles);

	}

	private static void readInclusionPaths(Playlist playlist) {
		File playlistFile = playlist.getFile();
		List<String> lines = FileUtil.readFile(playlistFile);
		List<File> inclusionPaths = new ArrayList<File>();
		for (String line : lines) {
			if (line.startsWith(Playlist.INCLUDE_PREFIX)) {
				String relativePath = line.replace(Playlist.INCLUDE_PREFIX, "");
				String parentPath = playlistFile.getParent();
				File includePath = new File(parentPath, relativePath);
				inclusionPaths.add(includePath);
			}
		}
		playlist.setInclusionPaths(inclusionPaths);
	}

	private static List<File> findMusicFiles(File path) {
		List<File> musicFiles = new ArrayList<File>();
		File[] children = path.listFiles();
		if (children != null) {
			for (File child : children) {
				if (child.isDirectory()) {
					musicFiles.addAll(findMusicFiles(child));// recursive call
				} else if (FileUtil.isMusicFile(child)) {
					musicFiles.add(child);
				}
			}
		}
		return musicFiles;
	}

}
