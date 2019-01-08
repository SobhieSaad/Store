package com.example.mybleproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Thread aa = new Thread() {
		    public void run() {
		                try {Thread.sleep(5000);}
		                
		                catch (Exception ex) {
		                	
		                }
		                
		                finally{
		                	
		                Intent i1=new Intent(MainActivity.this,Second.class);  	
		                startActivity(i1);	
		                };
		            };
		        };
				aa.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
