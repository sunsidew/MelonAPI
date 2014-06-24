package org.simple.melon;
/**
 * 
 * @brief genre data class
 * @detail 장 정보가 가지고 있는 자료형에 맞는 생성자 및 각각의 자료들을 가진다.
 *
 */
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
