package org.nhnnext.MelonAPI;

public class Genre {
	private String genreName;
	private String genreId;
	
	public Genre(String genreName, String genreId) {
		this.genreName = genreName;
		this.genreId = genreId;
	}
	
	public String getGenreName() {
		return genreName;
	}
	public String getGenreId() {
		return genreId;
	}
}
