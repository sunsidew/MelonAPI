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
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.CONTENT_TYPE;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.HttpMethod;
import com.skp.openplatform.android.sdk.common.PlanetXSDKException;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
/**
 * 
 * @brief 차트 관련 검색
 * @detail 현재 차트 순위에 나오는 곡에 대한 정보를 가져온다.
 * getRealTimeChart 실시간 차트를 가져온다.
 * getdailyChart 데일리 차트를 가져온다.
 * getGenreChart 장르 차트를 가져온다.
 * getalbumChart 앨범 차트를 가져온다.
 *
 */
public class MelonChart {

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
	
	public MelonChart(String app_key) {
		APIRequest.setAppKey(app_key);
	}
	
	public ArrayList<Song> getRealTimeChart(int page, int count) {
		ArrayList<Song> result = new ArrayList<Song>();
		
		try{			
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	
	    	String URL = "http://apis.skplanetx.com/melon/charts/realtime";
	    	
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
					musics.getJSONObject(i).getInt("currentRank"),
					musics.getJSONObject(i).getInt("pastRank"),
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
			// TODO Auto-generated catch block	
	}

	public ArrayList<Song> getdailyChart(int page, int count) {
		ArrayList<Song> result = new ArrayList<Song>();
		
		try{
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	
	    	String URL = "http://apis.skplanetx.com/melon/charts/todaytopsongs";
	    	
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
					musics.getJSONObject(i).getInt("currentRank"),
					musics.getJSONObject(i).getInt("pastRank"),
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

	public ArrayList<Song> getnonGenreChart(int page, int count) {
		ArrayList<Song> result = new ArrayList<Song>();
		try{
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	
	    	String URL = "http://apis.skplanetx.com/melon/charts/topgenres";
	    	
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
					musics.getJSONObject(i).getInt("currentRank"),
					musics.getJSONObject(i).getInt("pastRank"),
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

	public ArrayList<Song> getGenreChart(String genreid, int page, int count) {
		ArrayList<Song> result = new ArrayList<Song>();
		try{
			map.put("version", "1");
	    	map.put("page", page);
	    	map.put("count", count);
	    	
	    	String URL = "http://apis.skplanetx.com/melon/charts/topgenres/" + genreid;
	    	
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
					musics.getJSONObject(i).getInt("currentRank"),
					musics.getJSONObject(i).getInt("pastRank"),
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

	public ArrayList<Album> getalbumChart(int page, int count) {
		 
		ArrayList<Album> result = new ArrayList<Album>();
		
		map.put("version", "1");
    	map.put("page", page);
    	map.put("count", count);
    	
    	String URL = "http://apis.skplanetx.com/melon/charts/topalbums";
    	
    	RequestBundle req = new RequestBundle();
    	req.setUrl(URL);
    	req.setParameters(map);
    	req.setHttpMethod(HttpMethod.GET);
		req.setResponseType(CONTENT_TYPE.JSON);
		
		try {
    	    api.request(req,listener);
    	} catch (PlanetXSDKException e) {
    	    e.printStackTrace();
    	}
		
		while(apiresult.equals(""))
		{
			Log.i("test", "");
		}
		
		String depth[] = {"melon", "albums"};
		try {
			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			ParseJsonObject jp = new ParseJsonObject();
			jsonobj = jp.trimJobj(jsonobj, depth);
			
			JSONArray albums = jsonobj.getJSONArray("album");
			String artist_depth[] = {"artists"};
						
			for(int i = 0 ; i < albums.length() ; i++) {
				JSONArray artist = jp.trimJobj(albums.getJSONObject(i), artist_depth).getJSONArray("artist");
				
				Album album = new Album(
					albums.getJSONObject(i).getString("albumId"),
					albums.getJSONObject(i).getString("albumName"),
					artist.getJSONObject(0).getString("artistId"),
					artist.getJSONObject(0).getString("artistName"),
					albums.getJSONObject(i).getString("repSongId"),
					albums.getJSONObject(i).getString("repSongName"),
					albums.getJSONObject(i).getInt("currentRank"),
					albums.getJSONObject(i).getInt("pastRank"),
					albums.getJSONObject(i).getInt("repSongCurrentRank"),
					albums.getJSONObject(i).getInt("repSongPastRank"),
					albums.getJSONObject(i).getString("issueDate"),
					albums.getJSONObject(i).getString("albumType")
				);
					
				result.add(album);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Genre> getGenreList() {
		ArrayList<Genre> result = new ArrayList<Genre>();
		try {
			map.put("version", "1");
	    	
	    	String URL = "http://apis.skplanetx.com/melon/genres";
	    	
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
			
			String depth[] = {"melon", "genres"};

			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			ParseJsonObject jp = new ParseJsonObject();
			jsonobj = jp.trimJobj(jsonobj, depth);
			
			JSONArray genres = jsonobj.getJSONArray("genre");
						
			for(int i = 0 ; i < genres.length() ; i++) {
				Genre genre = new Genre(
					genres.getJSONObject(i).getString("genreId"),
					genres.getJSONObject(i).getString("genreName")
				);
					
				result.add(genre);
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
