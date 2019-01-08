package com.example.mybleproject;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity {
	 TextView t1,t2,t3,t4;
	 Button b1;
	   private BluetoothAdapter BA;
	   private static final int Discover_Duration=300;
	   private static final int Request_BLU=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		b1=(Button)findViewById(R.id.button1);
		// cm=new ConnectManager();
		  BA = BluetoothAdapter.getDefaultAdapter();
		  t1=(TextView)findViewById(R.id.T1);
		  t2=(TextView)findViewById(R.id.T2);
		  t3=(TextView)findViewById(R.id.T3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	public void start(View v) {
	   if (!BA.isEnabled()) {
	    	Toast.makeText(getApplicationContext(),"you must turn your bluetooth on to start this application ... !",Toast.LENGTH_LONG).show();
	           Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	           startActivityForResult(turnOn, Request_BLU);
	           
	        //   Toast.makeText(getApplicationContext(),"Turned on",Toast.LENGTH_LONG).show();
	        }
	        else
	        {
	          // Toast.makeText(getApplicationContext(),"Already on", Toast.LENGTH_LONG).show();
	            Intent i1=new Intent(Second.this,Third.class);  	
                startActivity(i1);
	        }
	    //    Intent i1=new Intent(Second.this,Third.class);  	
          //  startActivity(i1);
		
	}
	 private void errorExit(String title, String message){
		    Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
		    finish();
		  }

}
