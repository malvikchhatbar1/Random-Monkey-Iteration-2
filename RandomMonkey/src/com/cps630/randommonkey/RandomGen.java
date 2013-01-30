package com.cps630.randommonkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
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
        
		show.setOnClickListener(new View.OnClickListener() 
		{
            @Override
            public void onClick(View v) 
            {
            	
            	InputStreamReader reader1 = null;
				try {
					reader1 = new InputStreamReader(getAssets().open("facts.txt"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                BufferedReader reader = new BufferedReader(reader1);
                // Read in the file into a list of strings
        		/*try {
        			reader = new BufferedReader(new FileReader("facts.txt"));
        		} catch (FileNotFoundException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}*/
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
			    }
			
			    // Choose a random one from the list
			    Random r = new Random();
			    int arraySize = lines.size();
			    String randomString = lines.get(r.nextInt(arraySize));
			    tv.setText(randomString);
			    tv.setVisibility(0);
            }
		});
		
		pause.setOnClickListener(new View.OnClickListener() 
		{
            @Override
            public void onClick(View v) 
            {
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
			toast.show();
			break;
	
			case R.id.autoPlay:
			CharSequence text1 = "AutoPlay will now begin! Use Pause to stop AutoPlay.";
			int duration1 = Toast.LENGTH_SHORT;

		    pause.setVisibility(0);
			Toast toast1 = Toast.makeText(getApplicationContext(), text1, duration1);
			toast1.show();
			
			break;
	
			case R.id.preferences:
			CharSequence text2 = "To be added later.";
			int duration2 = Toast.LENGTH_SHORT;

			Toast toast2 = Toast.makeText(getApplicationContext(), text2, duration2);
			toast2.show();
			break;
		}
		return false;
		
	}
	
	

}
