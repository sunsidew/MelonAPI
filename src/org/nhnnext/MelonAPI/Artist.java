package org.nhnnext.MelonAPI;

public class Artist {
	private String artistId;
	private String artistName;
	private String sex;
	private String nationalityName;
	private String actTypeName;
	private String genreNames;
	
	public Artist(String artistId, String artistName, String sex,
			String nationalityName, String actTypeName, String genreNames) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.sex = sex;
		this.nationalityName = nationalityName;
		this.actTypeName = actTypeName;
		this.genreNames = genreNames;
	}
	
	public String getArtistId() {
		return artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public String getSex() {
		return sex;
	}
	public String getNationalityName() {
		return nationalityName;
	}
	public String getActTypeName() {
		return actTypeName;
	}
	public String getGenreNames() {
		return genreNames;
	}
	
}
