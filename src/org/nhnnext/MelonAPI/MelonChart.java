package org.nhnnext.MelonAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.skp.openplatform.android.sdk.api.APIRequest;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.CONTENT_TYPE;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.HttpMethod;
import com.skp.openplatform.android.sdk.common.PlanetXSDKException;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.common.RequestListener;
import com.skp.openplatform.android.sdk.common.ResponseMessage;

public class MelonChart {
	APIRequest api = new APIRequest();
	String URL = "http://apis.skplanetx.com/melon/charts/realtime";
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
		
		map.put("version", "1");
    	map.put("page", page);
    	map.put("count", count);
    	
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
		
		/*
		JSONObject jsonobj = (JSONObject) JSONValue.parse(result);
		
		JSONArray array = jsonobj.get("melon");
		
		for(String asd: array) {
			Song song = new Song("°¡¼ö¸í","°î¸í");
			result.add(song);
		}
		*/
		return result;
	}
}
