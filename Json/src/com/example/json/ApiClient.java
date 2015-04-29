package com.example.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiClient {

	public static List<BriefPic> initBrief(){
		String result = HttpUtil.getInstance().doGetStr("http://222.186.10.147:8080/MTProject/MTContr?action=MTListAll");
		List<BriefPic> list = new ArrayList<BriefPic>();
		if(result == null){
			return null;
		}
		try {
			JSONArray ja = new JSONObject(result).getJSONArray("MTListAll");
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.optJSONObject(i);
				BriefPic beiefPic = new BriefPic();
				beiefPic.setId(jo.getString("id"));
				beiefPic.setTitle(jo.getString("title"));
				beiefPic.setChannel_id(jo.getString("channel_id"));
				beiefPic.setImage(jo.getString("image"));
				beiefPic.setWidth(jo.getString("imageW"));
				beiefPic.setHeight(jo.getString("imageH"));
				list.add(beiefPic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public static List<BriefPic> loadMoreBrief(String id){
		String result = HttpUtil.getInstance().doGetStr("http://222.186.10.147:8080/MTProject/MTContr?action=MTListAllUp&id="+id);
		List<BriefPic> list = new ArrayList<BriefPic>();
		if(result == null){
			return null;
		}
		try {
			JSONArray ja = new JSONObject(result).getJSONArray("MTListAllUp");
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.optJSONObject(i);
				BriefPic beiefPic = new BriefPic();
				beiefPic.setId(jo.getString("id"));
				beiefPic.setTitle(jo.getString("title"));
				beiefPic.setChannel_id(jo.getString("channel_id"));
				beiefPic.setImage(jo.getString("image"));
				beiefPic.setWidth(jo.getString("imageW"));
				beiefPic.setHeight(jo.getString("imageH"));
				list.add(beiefPic);                        
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	

	
	
	

	
	
}
