package org.simple.melon;
/**
 * 
 * @brief song data class
 * @detail 곡 정보가 가지고 있는 자료형에 맞는 생성자 및 각각의 자료들을 가진다.
 *
 */
public class Song {
	private String songId;
	private String songName;
	private String artistId;
	private String artistName;
	private String albumId;
	private String albumName;
	private int currentRank;
	private int pastRank;
	private int playTime;
	private String issueDate;
	private boolean isTitleSong;
	private boolean isHitSong;
	private boolean isAdult;
	private boolean isFree;
	
	public Song(String songId, String songName, String artistId,
			String artistName, String albumId, String albumName,
			int currentRank, int pastRank, int playTime, String issueDate,
			boolean isTitleSong, boolean isHitSong, boolean isAdult,
			boolean isFree) {
		
		this.songId = songId;
		this.songName = songName;
		this.artistId = artistId;
		this.artistName = artistName;
		this.albumId = albumId;
		this.albumName = albumName;
		this.currentRank = currentRank;
		this.pastRank = pastRank;
		this.playTime = playTime;
		this.issueDate = issueDate;
		this.isTitleSong = isTitleSong;
		this.isHitSong = isHitSong;
		this.isAdult = isAdult;
		this.isFree = isFree;
	}
	public String getSongId() {
		return songId;
	}
	public String getSongName() {
		return songName;
	}
	public String getArtistId() {
		return artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public String getAlbumId() {
		return albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public int getCurrentRank() {
		return currentRank;
	}
	public int getPastRank() {
		return pastRank;
	}
	public int getPlayTime() {
		return playTime;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public boolean isTitleSong() {
		return isTitleSong;
	}
	public boolean isHitSong() {
		return isHitSong;
	}
	public boolean isAdult() {
		return isAdult;
	}
	public boolean isFree() {
		return isFree;
	}
}
