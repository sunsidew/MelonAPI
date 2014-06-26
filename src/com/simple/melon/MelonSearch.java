package com.simple.melon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.json.parse.ParseJsonObject;
import com.skp.openplatform.android.sdk.api.APIRequest;
import com.skp.openplatform.android.sdk.common.PlanetXSDKException;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.CONTENT_TYPE;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.HttpMethod;
/**
 * 
 * @brief 앨범/곡 관련 검색
 * @detail 사용자가 입력한 키워드에 따라서 검색한 결과를 가져온다.
 * getSearchAlbum 앨범 검색결과를 가져온다.
 * getSearchArtist 아티스트 검색결과를 가져온다.
 * getSearchSong 곡 검색결과를 가져온다.
 * getLastetSongByGenre 장르별 최신곡을 가져온다.
 *
 */
public class MelonSearch {
	APIRequest api = new APIRequest();
	Map<String, Object> map = new HashMap<String, Object>();
	
	String apiresult = "";
	String apistatuscode = "";
	
	RequestListener listener = new RequestListener() {
    	@Override
    	public void onPlanetSDKException(PlanetXSDKException e) {
    		apiresult = e.toString();
    	}
    	 
    	@Override
    	public void onComplete(ResponseMessage response) {
    		apistatuscode = response.getStatusCode();
    		apiresult = response.toString();
    	}
    };
	
	public MelonSearch(String app_key) {
		APIRequest.setAppKey(app_key); 
	}
	
	public ArrayList<Album> getSearchAlbum(int page, int count, String searchKeyword) {
		
		ArrayList<Album> result = new ArrayList<Album>();
		try{
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	map.put("searchKeyword", searchKeyword);
			
	    	String URL = "http://apis.skplanetx.com/melon/albums";
	    	
	    	RequestBundle req = new RequestBundle();
	    	req.setUrl(URL);
	    	req.setParameters(map);
	    	req.setHttpMethod(HttpMethod.GET);
			req.setResponseType(CONTENT_TYPE.JSON);
		
    	    api.request(req,listener);
	 		
			while(apiresult.equals(""))
			{
				Log.i("test", "");
			}
			
			String depth[] = {"melon", "albums"};
		
			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			ParseJsonObject jp = new ParseJsonObject();
			jsonobj = jp.trimJobj(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("album");
			String artist_depth[] = {"artists"};
						
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.trimJobj(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				
				Album album = new Album(
					musics.getJSONObject(i).getString("albumId"),
					musics.getJSONObject(i).getString("albumName"),
					null,
					null,
					artist.getJSONObject(0).getString("artistId"),
					artist.getJSONObject(0).getString("artistName"),
					musics.getJSONObject(i).getString("issueDate"),
					null,
					musics.getJSONObject(i).getString("averageScore")
				);
					
				result.add(album);
			}
		} catch (PlanetXSDKException e) {
    	    e.printStackTrace();
    	} catch (JSONException e) {
    		e.printStackTrace();
    	} catch (Exception e) {
			e.getMessage();
		}
    	
		return result;
	}
	
	public ArrayList<Artist> getSearchArtist(int page, int count, String searchKeyword) {
		
		ArrayList<Artist> result = new ArrayList<Artist>();
		
		try{
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	map.put("searchKeyword", searchKeyword);
			
	    	String URL = "http://apis.skplanetx.com/melon/artists";
	    	
	    	RequestBundle req = new RequestBundle();
	    	req.setUrl(URL);
	    	req.setParameters(map);
	    	req.setHttpMethod(HttpMethod.GET);
			req.setResponseType(CONTENT_TYPE.JSON);

    	    api.request(req,listener);
		
			while(apiresult.equals(""))
			{
				Log.i("test", "");
			}
			
			String depth[] = {"melon", "artists"};
			
			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			ParseJsonObject jp = new ParseJsonObject();
			jsonobj = jp.trimJobj(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("artist");
						
			for(int i = 0 ; i < musics.length() ; i++) {
				
				Artist artist = new Artist(
					musics.getJSONObject(i).getString("artistId"),
					musics.getJSONObject(i).getString("artistName"),
					musics.getJSONObject(i).getString("sex"),
					musics.getJSONObject(i).getString("nationalityName"),
					musics.getJSONObject(i).getString("actTypeName"),
					musics.getJSONObject(i).getString("genreNames")
				);
					
				result.add(artist);
			}
		} catch (PlanetXSDKException e) {
    	    e.printStackTrace();
    	} catch (JSONException e) {
    		e.printStackTrace();
    	} catch (Exception e) {
			e.getMessage();
		}
    	
		return result;
	}
	
	public ArrayList<Song> getSearchSong(int page, int count, String searchKeyword) {
		
		ArrayList<Song> result = new ArrayList<Song>();
		
		try{
				
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	map.put("searchKeyword", searchKeyword);
	    	
	    	String URL = "http://apis.skplanetx.com/melon/songs";
	    	
	    	RequestBundle req = new RequestBundle();
	    	req.setUrl(URL);
	    	req.setParameters(map);
	    	req.setHttpMethod(HttpMethod.GET);
			req.setResponseType(CONTENT_TYPE.JSON);
		
    	    api.request(req,listener);
	    	
			while(apiresult.equals(""))
			{
				Log.i("test", "");
			}
			
			String depth[] = {"melon", "songs"};
			
			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			ParseJsonObject jp = new ParseJsonObject();
			jsonobj = jp.trimJobj(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("song");
			String artist_depth[] = {"artists"};
						
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.trimJobj(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				
				Song song = new Song(
					musics.getJSONObject(i).getString("songId"),
					musics.getJSONObject(i).getString("songName"),
					artist.getJSONObject(0).getString("artistId"),
					artist.getJSONObject(0).getString("artistName"),
					musics.getJSONObject(i).getString("albumId"),
					musics.getJSONObject(i).getString("albumName"),
					0,
					0,
					musics.getJSONObject(i).getInt("playTime"),
					musics.getJSONObject(i).getString("issueDate"),
					musics.getJSONObject(i).getBoolean("isTitleSong"),
					musics.getJSONObject(i).getBoolean("isHitSong"),
					musics.getJSONObject(i).getBoolean("isAdult"),
					musics.getJSONObject(i).getBoolean("isFree")
				);
					
				result.add(song);
			}
		} catch (PlanetXSDKException e) {
    	    e.printStackTrace();
    	} catch (JSONException e) {
    		e.printStackTrace();
    	} catch (Exception e) {
			e.getMessage();
		}
		
		return result;
	}
}
