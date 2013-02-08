package com.cps630.randommonkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RandomGen extends Activity {
	TextView tv;
	Button show;
	Button pause;
	Button exit;
	String pauseFlag;
	
    final ArrayList<String> facts = new ArrayList<String>();
	private class LoadJsonTask extends AsyncTask<String, Void, ArrayList<String> > {
	       ProgressDialog dialog ;
	       protected void onPreExecute (){
	            dialog = ProgressDialog.show(RandomGen.this ,"Random Monkey","Getting FACTS!!!");

	       }
	       protected ArrayList<String> doInBackground (String... params){
	           return doGetJson(params[0]);
	       }
	       protected void onPostExecute(ArrayList<String> mylist){
	            dialog.dismiss();
	       }
	    }
	
	public ArrayList<String> doGetJson(String url) 
    {
	    //JSONArray json = JSONfunctions.getJSONfromURL("http://10.0.2.2:3000/facts.json");
	    JSONArray json = JSONfunctions.getJSONfromURL(url);
	    try{
	        for(int i=0;i<json.length();i++){
				JSONObject e = json.getJSONObject(i);
				facts.add(e.getString("fact"));
				Collections.shuffle(facts);
			}		
	    }catch(JSONException e)        {
	    	 Log.e("log_tag", "Error parsing data "+e.toString());
	    }
	    show.setOnClickListener(new View.OnClickListener() 
		{
            @Override
            public void onClick(View v) 
            {
            	
            	/*InputStreamReader reader1 = null;
				try {
					reader1 = new InputStreamReader(getAssets().open("facts.txt"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                BufferedReader reader = new BufferedReader(reader1);
			    List<String> lines = new ArrayList<String>();
			
			    String line = null;
				try {
					line = reader.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    while( line != null ) {
			        lines.add(line);
			        try {
						line = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }*/
				
			    // Choose a random one from the list
			    Random r = new Random();
			    int arraySize = facts.size();
			    String randomString = facts.get(r.nextInt(arraySize));
			    tv.setText(randomString);
			    tv.setVisibility(0);
            }
		});
	    return facts;
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_gen);
        
        tv =(TextView)findViewById(R.id.textView1);
        show = (Button)findViewById(R.id.button1);
        pause = (Button)findViewById(R.id.button2);
        exit = (Button)findViewById(R.id.button3);
		

        tv.setVisibility(0);
        show.setVisibility(0);
        pause.setVisibility(4);

        /**Stuff was here**/

        new LoadJsonTask().execute("http://10.0.2.2:3000/facts.json");
		
		pause.setOnClickListener(new View.OnClickListener() 
		{
            @Override
            public void onClick(View v) 
            {
            	pauseFlag = "pause";
            	pause.setVisibility(4);
            	show.setVisibility(0);
            }
		});
		
		exit.setOnClickListener(new View.OnClickListener() 
		{
	        public void onClick(View v) 
	    	{
		        finish();
		        System.exit(0);
	        }
        });
		
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.activity_random_gen, menu);
		return true;
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
			case R.id.aboutUs:
			CharSequence text = "Created By: Malvik Chhatbar, Timothy Quon, Lakshiya Rajagulasingam & Hardeep Nijher";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(getApplicationContext(), text, duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			break;
	
			case R.id.autoPlay:
			CharSequence text1 = "AutoPlay will now begin! Use Pause to stop AutoPlay.";
			int duration1 = Toast.LENGTH_SHORT;
        	pauseFlag="play";
		    pause.setVisibility(0);
		    show.setVisibility(4);
			Toast toast1 = Toast.makeText(getApplicationContext(), text1, duration1);
			toast1.setGravity(Gravity.CENTER, 0, 0);
			toast1.show();
		    tv.setVisibility(0);
		    new Thread(new Runnable(){
		        public void run() 
		        {
			        // TODO Auto-generated method stub
					Random r = new Random();
				    int arraySize = facts.size();
			        while(pauseFlag.equals("play"))
			        {
			        	try 
			        	{
			        		final String randomString = facts.get(r.nextInt(arraySize));
							runOnUiThread(new Runnable() 
							{
							    public void run() 
							    {
								    tv.setText(randomString);
							    }
							});
						    Thread.sleep(4000);
						    
			        	} 
			        	catch (InterruptedException e) 
			        	{
			        		// TODO Auto-generated catch block
						    e.printStackTrace();
			        	} 
		        	}
                }
		    }).start();
			break;
	
			case R.id.preferences:
			CharSequence text2 = "To be added later.";
			int duration2 = Toast.LENGTH_SHORT;

			Toast toast2 = Toast.makeText(getApplicationContext(), text2, duration2);
			toast2.setGravity(Gravity.CENTER, 0, 0);
			toast2.show();
			break;
		}
		return false;
		
	}
	
	

}
