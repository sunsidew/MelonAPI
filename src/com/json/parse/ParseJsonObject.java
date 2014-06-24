package com.json.parse;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseJsonObject {
	public JSONObject trimJobj (JSONObject inputjson, String[] depth) {
		String jsonstr = "";
		JSONObject jsonobj = inputjson;
		
		try {	
			for(int i = 0 ; i < depth.length ; i++)
			{
				jsonstr = jsonobj.getString(depth[i]);
				jsonobj = new JSONObject(jsonstr);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonobj;
	}
}
