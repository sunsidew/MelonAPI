package org.nhnnext.json;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @brief json parser
 * @detail 설정한 depth에 따라 넘어온 자료를 파싱한다.
 *
 */
public class JsonParse {
	public JSONObject stripJson (JSONObject inputjson, String[] depth) {
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
