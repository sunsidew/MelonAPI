package com.json.parse;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @brief json parser
 * @detail 설정한 depth에 따라 넘어온 자료를 파싱한다.
 *
 */
public class ParseJsonObject {
	public JSONObject trimJobj (JSONObject inputjson, String[] depth) {
		try {
			String jsonstr = "";
			JSONObject jsonobj = inputjson;
			
			for(int i = 0 ; i < depth.length ; i++)
			{
				jsonstr = jsonobj.getString(depth[i]);
				jsonobj = new JSONObject(jsonstr);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}		
		
		return jsonobj;
	}
}
