package com.mpc.product;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	
		EditText mName;
		EditText mBrand;
		EditText mType;
		EditText mPrice;
		
		Button mSubmitBtn;
		Button mGetBtn;
		
		Candy candy = new Candy();
		
		ArrayList<Candy> priceArray = new ArrayList<Candy>();
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mName = (EditText)findViewById(R.id.candy_name);
		mBrand = (EditText)findViewById(R.id.candy_brand);
		mType = (EditText)findViewById(R.id.candy_type);
		mPrice = (EditText)findViewById(R.id.candy_price);
		
		mSubmitBtn = (Button)findViewById(R.id.submit_button);
		mGetBtn = (Button)findViewById(R.id.get_button);
		
		mSubmitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				candy.setCandyBrand(mBrand.getText().toString());
				candy.setCandyName(mName.getText().toString());
				candy.setCandyType(mType.getText().toString());
				candy.setCandyPrice(Float.valueOf(mPrice.getText().toString()));
				
				priceArray.add(candy);
				ServerCommunicationTask task = new ServerCommunicationTask();
				task.execute(candy);
			}
		});
		
		mGetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent openCandyList = new Intent(MainActivity.this, CandyListActivity.class);
				startActivity(openCandyList);
				
			}
		});
		
	} 
	
	
	
	
	class ServerCommunicationTask extends AsyncTask<Candy, Void, String>{

		@Override
		protected String doInBackground(Candy... params) {
			Candy candy = params[0];
			JSONObject json = new JSONObject();
			try {
				json.put("name",candy.getCandyName() );
				json.put("brand", candy.getCandyBrand());
				json.put("type", candy.getCandyType());
				json.put("price", candy.getCandyPrice());
				Log.i("request", json.toString());
				doHttpRequest(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
					
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
	}
	
	
	static void doHttpRequest(JSONObject requestParam) {
		final String SERVER_URL = "http://192.168.1.2:3001/addCandy";
		URL url;
		try {
			url = new URL(SERVER_URL);
			String postString = requestParam.toString();
			
			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-type", "application/json;charset = utf-8");
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.connect();
			
			OutputStream outPut = new BufferedOutputStream(urlConnection.getOutputStream());
			outPut.write(postString.getBytes());
			outPut.flush();
			int responseCode = urlConnection.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}

