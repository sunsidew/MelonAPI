package org.nhnnext.MelonAPI;

public class Album {
	private String albumId;
	private String albumName;
	private String artistId;
	private String artistName;
	private String repSongId;
	private String repSongName;
	private int currentRank;
	private int pastRank;
	private int repSongCurrentRank;
	private int repSongPastRank;
	private String issueDate;
	private String totalSongCount;
	private String averageScore;
	private String albumType;

	public Album(String albumId, String albumName, String artistId,
			String artistName, String repSongId, String repSongName,
			int currentRank, int pastRank, int repSongCurrentRank,
			int repSongPastRank, String issueDate, String albumType) {
		this.albumId = albumId;
		this.albumName = albumName;
		this.artistId = artistId;
		this.artistName = artistName;
		this.repSongId = repSongId;
		this.repSongName = repSongName;
		this.currentRank = currentRank;
		this.pastRank = pastRank;
		this.repSongCurrentRank = repSongCurrentRank;
		this.repSongPastRank = repSongPastRank;
		this.issueDate = issueDate;
		this.albumType = albumType;
	}

	public Album(String albumId, String albumName, String repSongId,
			String repSongName, String artistId, String artistName,
			String issueDate, String totalSongCount, String averageScore) {
		this.albumId = albumId;
		this.albumName = albumName;
		this.repSongId = repSongId;
		this.repSongName = repSongName;
		this.artistId = artistId;
		this.artistName = artistName;
		this.issueDate = issueDate;
		this.totalSongCount = totalSongCount;
		this.averageScore = averageScore;
	}

	public String getAlbumId() {
		return albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public String getArtistId() {
		return artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getRepSongId() {
		return repSongId;
	}

	public String getRepSongName() {
		return repSongName;
	}

	public int getCurrentRank() {
		return currentRank;
	}

	public int getPastRank() {
		return pastRank;
	}

	public int getRepSongCurrentRank() {
		return repSongCurrentRank;
	}

	public int getRepSongPastRank() {
		return repSongPastRank;
	}

	public String getIssueDate() {
		return issueDate;
	}
	
	public String getTotalSongCount() {
		return totalSongCount;
	}
	
	public String getAverageScore() {
		return averageScore;
	}

	public String getAlbumType() {
		return albumType;
	}
}
