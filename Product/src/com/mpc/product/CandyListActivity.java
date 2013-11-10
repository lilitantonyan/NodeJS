package com.mpc.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class CandyListActivity extends Activity {
	ListView mCandyListView;
	CandyAdapter mAdapter;
	ArrayList<Candy> candysArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_candy_list);

		candysArray = new ArrayList<Candy>();

		mCandyListView = (ListView) findViewById(R.id.candy_list);
		mAdapter = new CandyAdapter(getApplicationContext());
		mCandyListView.setAdapter(mAdapter);
		GetCaandyTask task = new GetCaandyTask();
		task.execute();
	}

	class GetCaandyTask extends AsyncTask<Void, Void, Void> {

		
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			JSONArray result = doHttpRequest();
			Log.i("result", result.toString());
			try {
				for (int i = 0; i < result.length(); ++i) {
					JSONObject tmpJSON = result.getJSONObject(i);
					Log.i("current", tmpJSON.toString());
					Candy candy = new Candy();
					candy.setCandyBrand(tmpJSON.getString("brand"));
					candy.setCandyName(tmpJSON.getString("name"));
					candysArray.add(candy);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	static JSONArray doHttpRequest() {
		final String SERVER_URL = "http://192.168.1.2:3001/getcandy";
		URL url;
		JSONArray candyJSONArray = new JSONArray();
		try {
			url = new URL(SERVER_URL);
			
			
			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection)url.openConnection();
			InputStream in = urlConnection.getInputStream();
			BufferedReader streamReader = new BufferedReader(
					new InputStreamReader(in, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			
			while((inputStr = streamReader.readLine()) != null){
				responseStrBuilder.append(inputStr);
			}
			
			candyJSONArray = new JSONArray(responseStrBuilder.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candyJSONArray;
	}
}
