package com.example.melontester;

import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.nhnnext.MelonAPI.MelonChart;
import org.nhnnext.MelonAPI.MineJsonObject;
import org.nhnnext.MelonAPI.Song;

import com.skp.openplatform.android.sdk.api.APIRequest;
import com.skp.openplatform.android.sdk.common.PlanetXSDKException;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.CONTENT_TYPE;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.HttpMethod;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {
	
	Button btn;
	TextView tv;
	APIRequest api = new APIRequest();
	Map<String, Object> map = new HashMap<String, Object>();
	String URL = "http://apis.skplanetx.com/melon/charts/realtime";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    
    public void setResult(String result)
	{
		tv.setText(result);
	}
    
    public void initUI() {
		btn = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.textView1);
		btn.setOnClickListener(this);
	}
    
    String result = "";
    
    Handler handler = new Handler();
    
    RequestListener listener = new RequestListener() {
    	@Override
    	public void onPlanetSDKException(PlanetXSDKException e) {
    		result = e.toString();
    		handler.post(new Runnable() {
    			public void run() {
    				tv.setText(result);
    			}
    		});
    	}
    	 
    	@Override
    	public void onComplete(ResponseMessage response) {
    		result = response.toString();
    		//response.getStatusCode() + "\n" + 
    		handler.post(new Runnable() {
    			public void run() {
    				parse(result);
    				MelonChart melonchart = new MelonChart("a857db0d-38f7-3375-a5ad-4d4b306a8945");
    			}
    		});
    		//Log.i("result",response.toString());
    	}
    };
    
    public void apireq() {
    	APIRequest.setAppKey("a857db0d-38f7-3375-a5ad-4d4b306a8945");
    	
    	map.put("version", "1");
    	map.put("page", "1");
    	map.put("count", "10");
    	
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

    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			apireq();
		}
		// TODO Auto-generated method stub
	}

	private void parse(String result2) {
		try {
			JSONObject jsonobj = new JSONObject(result);
			
			String depth[] = {"melon", "songs"};
			
			JsonParse jp = new JsonParse();
			jsonobj = jp.mineJsonobj(jsonobj, depth);
			
			JSONArray musics = jsonobj.getJSONArray("song");
			String artist_depth[] = {"artists"};
			
			for(int i = 0 ; i < musics.length() ; i++) {
				JSONArray artist = jp.mineJsonobj(musics.getJSONObject(i), artist_depth).getJSONArray("artist");
				tv.setText(tv.getText() + "\n" + 
						musics.getJSONObject(i).getString("songId")+
						musics.getJSONObject(i).getString("songName")+
						musics.getJSONObject(i).getString("albumId")+
						musics.getJSONObject(i).getString("albumName")
						);
				// 이렇게 일일히...
				
//				Song song = new Song(
//					musics.getJSONObject(i).getString("songId"),
//					musics.getJSONObject(i).getString("songName"),
//					artist.getJSONObject(0).getString("artistId"),
//					artist.getJSONObject(0).getString("artistName"),
//					musics.getJSONObject(i).getString("albumId"),
//					musics.getJSONObject(i).getString("albumName"),
//					musics.getJSONObject(i).getInt("currentRank"),
//					musics.getJSONObject(i).getInt("pastRank"),
//					musics.getJSONObject(i).getInt("playTime"),
//					musics.getJSONObject(i).getString("issueDate"),
//					musics.getJSONObject(i).getBoolean("isTitleSong"),
//					musics.getJSONObject(i).getBoolean("isHitSong"),
//					musics.getJSONObject(i).getBoolean("isAdult"),
//					musics.getJSONObject(i).getBoolean("isFree")
//				);
					
				//result.add(song);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}
}
