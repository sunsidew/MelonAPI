package org.nhnnext.MelonAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nhnnext.json.JsonParse;

import android.util.Log;

import com.skp.openplatform.android.sdk.api.APIRequest;
import com.skp.openplatform.android.sdk.common.PlanetXSDKException;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.CONTENT_TYPE;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.HttpMethod;

public class MelonLatest {
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
	
	public MelonLatest(String app_key) {
		APIRequest.setAppKey(app_key);
	}
	
	public ArrayList<Album> getLatestAlbum(int page, int count) {
		
		ArrayList<Album> result = new ArrayList<Album>();
		
		map.put("version", "1");
    	map.put("page", page);
    	map.put("count", count);
		
    	String URL = "http://apis.skplanetx.com/melon/newreleases/albums";
    	
    	RequestBundle req = new RequestBundle();
    	req.setUrl(URL);
    	req.setParameters(map);
    	req.setHttpMethod(HttpMethod.GET);
		req.setResponseType(CONTENT_TYPE.JSON);
		
		try {
    	    api.request(req,listener);
    	} catch(PlanetXSDKException e) {
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
			
			JsonParse jp = new JsonParse();
			jsonobj = jp.stripJson(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("album");
			String artist_depth[] = {"repArtists"};
						
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.stripJson(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				
				Album album = new Album(
					musics.getJSONObject(i).getString("albumId"),
					musics.getJSONObject(i).getString("albumName"),
					musics.getJSONObject(i).getString("repSongId"),
					musics.getJSONObject(i).getString("repSongName"),
					artist.getJSONObject(0).getString("artistId"),
					artist.getJSONObject(0).getString("artistName"),
					musics.getJSONObject(i).getString("issueDate"),
					musics.getJSONObject(i).getString("totalSongCount"),
					musics.getJSONObject(i).getString("averageScore")
				);
					
				result.add(album);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return result;
	}
	
	public ArrayList<Album> getLatestAlbumByGenre(String genreid, int page, int count) {
		ArrayList<Album> result = new ArrayList<Album>();
		
		map.put("version", "1");
    	map.put("page", page);
    	map.put("count", count);
    	
    	String URL = "http://apis.skplanetx.com/melon/newreleases/albums/" + genreid;
    	
    	RequestBundle req = new RequestBundle();
    	req.setUrl(URL);
    	req.setParameters(map);
    	req.setHttpMethod(HttpMethod.GET);
		req.setResponseType(CONTENT_TYPE.JSON);
		
		try {
    	    api.request(req,listener);
    	} catch(PlanetXSDKException e) {
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
			
			JsonParse jp = new JsonParse();
			jsonobj = jp.stripJson(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("album");
			String artist_depth[] = {"repArtists"};
						
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.stripJson(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				
				Album album = new Album(
						musics.getJSONObject(i).getString("albumId"),
						musics.getJSONObject(i).getString("albumName"),
						musics.getJSONObject(i).getString("repSongId"),
						musics.getJSONObject(i).getString("repSongName"),
						artist.getJSONObject(0).getString("artistId"),
						artist.getJSONObject(0).getString("artistName"),
						musics.getJSONObject(i).getString("issueDate"),
						musics.getJSONObject(i).getString("totalSongCount"),
						musics.getJSONObject(i).getString("averageScore")
					);
					
				result.add(album);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Song> getLatestSong(int page, int count) {
		ArrayList<Song> result = new ArrayList<Song>();
		
		map.put("version", "1");
    	map.put("page", page);
    	map.put("count", count);
    	
    	String URL = "http://apis.skplanetx.com/melon/newreleases/songs";
    	
    	RequestBundle req = new RequestBundle();
    	req.setUrl(URL);
    	req.setParameters(map);
    	req.setHttpMethod(HttpMethod.GET);
		req.setResponseType(CONTENT_TYPE.JSON);
		
		try {
    	    api.request(req,listener);
    	} catch(PlanetXSDKException e) {
    	    e.printStackTrace();
    	}
		
		while(apiresult.equals(""))
		{
			Log.i("test", "");
		}
		
		String depth[] = {"melon", "songs"};
		try {
			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			JsonParse jp = new JsonParse();
			jsonobj = jp.stripJson(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("song");
			String artist_depth[] = {"artists"};
						
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.stripJson(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Song> getLastetSongByGenre(String genreid, int page, int count) {
		ArrayList<Song> result = new ArrayList<Song>();
		
		map.put("version", "1");
    	map.put("page", page);
    	map.put("count", count);
    	
    	String URL = "http://apis.skplanetx.com/melon/newreleases/songs/" + genreid;
    	
    	RequestBundle req = new RequestBundle();
    	req.setUrl(URL);
    	req.setParameters(map);
    	req.setHttpMethod(HttpMethod.GET);
		req.setResponseType(CONTENT_TYPE.JSON);
		
		try {
    	    api.request(req,listener);
    	} catch(PlanetXSDKException e) {
    	    e.printStackTrace();
    	}
		
		while(apiresult.equals(""))
		{
			Log.i("test", "");
		}
		
		String depth[] = {"melon", "songs"};
		try {
			Log.i("test","apiresult:" + apiresult);
			JSONObject jsonobj = new JSONObject(apiresult);
			
			JsonParse jp = new JsonParse();
			jsonobj = jp.stripJson(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("song");
			String artist_depth[] = {"artists"};
						
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.stripJson(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
