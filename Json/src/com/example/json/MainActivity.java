package com.example.json;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

public class MainActivity extends Activity {

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			}
		List<BriefPic> list1 = null;
		list1 = ApiClient.initBrief();
		System.out.println("list的长度是："+list1.size());
		for(int i=0;i<list1.size();i++){
		System.out.println("标题是："+list1.get(i).getTitle());
		
		
		}
		List<BriefPic> list2 = null;
		list2=ApiClient.loadMoreBrief("1");
		System.out.println("11"+list2.get(0).getTitle());
		//////////////////////12123212edwq
	}

	

}
