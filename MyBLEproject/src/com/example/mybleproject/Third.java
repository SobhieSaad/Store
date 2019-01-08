package com.example.mybleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.xml.datatype.Duration;

import org.apache.http.impl.conn.Wire;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class Third extends Activity {
	
	
	   private OutputStream     outStream         = null;
	   private InputStream      inStream          = null;
	   private BluetoothSocket  btSocket          = null;
	   ImageButton bf,bt,bi;
		  
	   private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-74F06DCF9AC9");

		  // Insert your server's MAC address 

//private static String address = "24:EC:99:2B:38:9C";	
	   private static String address="74:F0:6D:CF:9A:C9";
	   private BluetoothAdapter BA;
	   ArrayAdapter<String> adapter2;
	   ListView vv;
	   TextView tv1;
	   Handler mHandler= new Handler() {
		   @Override
		   public void handleMessage(Message msg) {
		   byte[] writeBuf = (byte[]) msg.obj;
		   
		   switch(msg.what) {
		   case 1:
		   String writeMessage = new String(writeBuf);
	
		   AlertDialog.Builder builder = new AlertDialog.Builder(Third.this);
		   builder.setMessage(writeMessage);
		  builder.setCancelable(false);
		   builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
	
			}
		});
	
		   AlertDialog a=builder.create(); 
		   a.show();

		   }
		   }
		   };
		  // String  writeMessage2;
		   Handler newHandler= new Handler() {
			   @Override
			   public void handleMessage(Message msg) {
			   byte[] writeBuf = (byte[]) msg.obj;
			   
			   switch(msg.what) {
			   case 1:
		      String writeMessage2 = new String(writeBuf);
			 if(writeMessage2.contains("noev") || writeMessage2.length()==0)
			 {  Toast.makeText(getApplicationContext(),"There is no more news in Al Madina Mall",Toast.LENGTH_LONG).show();}
			 else{ 
			   Toast.makeText(getApplicationContext(),writeMessage2,Toast.LENGTH_LONG).show();
//			   if(!writeMessage2.equals("noev"))
//			   {
				   NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				   Notification notify= new Notification(android.R.drawable.stat_notify_more,"New Event ",System.currentTimeMillis() );
				   Context context=Third.this;
				   CharSequence title = "You have been notified";
				   Intent i3=new Intent(context,Third.class);  
				   PendingIntent pa=PendingIntent.getActivity(context, 0, i3, 0); 
				   notify.setLatestEventInfo(context, title, writeMessage2, pa);
				   notify.sound=Uri.parse("android.resource://com.leebrimelow.status/"+R.raw.beep);
				   nm.notify(0, notify);
				//   Toast.makeText(getApplicationContext(),writeMessage2,Toast.LENGTH_LONG).show();
			   adapter2.add(writeMessage2);
//			   }
				if(vv.getAdapter().getCount()>0)
				{tv1.setVisibility(View.INVISIBLE);
				//Toast.makeText(getApplicationContext(), "rawaa",Toast.LENGTH_LONG).show();
				}
			   }
			  
			   }
			   }
			   };
	

	
	Button b,b2;
	//String[] myitems= {"Soldes in Design Fashion" , "New collection in Scoop" , "Happy news in Al Madina Mall" , "New Department in Al Madina Mall" , "Big event soon in Al Madina Mall"};
	ArrayList<String> items = new ArrayList<String>() ;
	
	String messageSending;
	String s;
	
    HashMap<String, List<String>> mall_categories;
    List<String> Shop_list;
    ExpandableListView Exp_list;
    ShopAdapter adapter;
    public  static final int REQUEST_ENABLE_BT = 1;
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		 adapter2 = new ArrayAdapter<String>(
					this, R.layout.s , items);
		 BA = BluetoothAdapter.getDefaultAdapter();
		 /*************/
		 //Discovery();
		 /*************/
	    tv1=(TextView)findViewById(R.id.textViewhint);
		TabHost th = (TabHost)findViewById(R.id.tabhost);
		th.setup();
		TabSpec ts = th.newTabSpec("tag1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Stores ");
		th.addTab(ts);
		 ts = th.newTabSpec("tag2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Latest news");
		th.addTab(ts);
		 ts = th.newTabSpec("tag3");
			ts.setContent(R.id.tab3);
			ts.setIndicator("about");
			th.addTab(ts);
	        b2=(Button)findViewById(R.id.button1);
	   /*     b2.setOnClickListener(new OnClickListener() {
				String t="title";
				String t2="scoop";
				@Override
				public void onClick(View arg0) {
			
					sendrefreshmessage(t);
					//items.add(writeMessage);
				}
			});*/
		
	
	        // tab2 element
  //  items = new ArrayList<String>();
	  /*      try {
				Scanner s = new Scanner(new File("data.txt"));
				while (s.hasNextLine()){
				    items.add(s.nextLine());
				}
				s.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	     
/*    items.add("Soldes in Design Fashion");
    items.add("New collection in Scoop");
    items.add("Happy news in Al Madina Mall");
    items.add("New Department in Al Madina Mall");
    items.add("Big event soon in Al Madina Mall");*/
 
    
    
    Exp_list=(ExpandableListView)findViewById(R.id.expandableListView1);	
	mall_categories=DataProvider.getInfo();
	Shop_list=new ArrayList<String>(mall_categories.keySet());
	adapter=new ShopAdapter(this, mall_categories, Shop_list);
	Exp_list.setAdapter(adapter);
    Exp_list.setOnGroupExpandListener(new OnGroupExpandListener() {
		
		@Override
		public void onGroupExpand(int groupPosition) {
		//	  Toast.makeText(getBaseContext(),Shop_list.get(groupPosition)+"is epanded",Toast.LENGTH_LONG).show();
			/************/
			//Discovery();
			/************/
		}
	});
    
    Exp_list.setOnGroupCollapseListener(new OnGroupCollapseListener() {
		
		@Override
		public void onGroupCollapse(int groupPosition) {
			// TODO Auto-generated method stub
		//	  Toast.makeText(getBaseContext(),Shop_list.get(groupPosition)+"is collapse",Toast.LENGTH_LONG).show();
				
			
		}
	});
	
    Exp_list.setOnChildClickListener(new OnChildClickListener() {
		/***********/
		@SuppressLint("NewApi")
		/***********/
		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
				int childPosition, long id) {
		
			/*************/
		    Discovery();
		    /*************/
			messageSending= mall_categories.get(Shop_list.get(groupPosition)).get(childPosition);
		
			
			
			sendmessage(messageSending);
			
			/*****************/
			if(btSocket.isConnected()==true)
			{	
				try {
					btSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/*****************/
			//messageSending=null;
			/****************/
			
			 return true;
		}
	});
    
    ss();
   /*******************/ 
    int delay=8000;
    int period=30000;
    Timer timer=new Timer();
    timer.scheduleAtFixedRate(new MyTask(), delay, period);
    /********************/
    	
    
    
    
    
    
   /* Timer timer=new Timer();
    timer.schedule(new TimerTask(){
    	public void run(){
    		sendrefreshmessage("title");
    	}
    },0,30000);
    */
	}
	/*******************/
	private class MyTask extends TimerTask{
		@SuppressLint("NewApi")
		public void run(){
			final String t="title";
			Discovery();
			sendrefreshmessage(t);
			if(btSocket.isConnected()==true)
			{	
				try {
					btSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
	/******************/

	private void ss() {
	
	
	adapter2.notifyDataSetChanged();
	vv=(ListView)findViewById(R.id.listView1);
	vv.setAdapter(adapter2);
	vv.setOnItemClickListener(new OnItemClickListener() {

		/**************/
		@SuppressLint("NewApi")
		/**************/
		@Override
		public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
				long id) {
			// TODO Auto-generated method stub
			TextView textView=(TextView)viewClicked;
			String  message = "you clicked" + position +"which is :" + textView.getText().toString();;
			String sl=(String)(vv.getItemAtPosition(position));
		//	Toast.makeText(getApplicationContext(), sl, Toast.LENGTH_LONG).show();
/*			switch (position) {
			case 0:
				message = "m1";
				break;
			case 1:
				message = "m2";
				break;
			case 2:
				message = "m3";
				break;
			case 3:
				message = "m4";
				break;
			case 4:
				message = "m5";
				break;
        case 5:
        	  String d="details";
       	   sendmessage(d);
       	   break;
        	   
			default:
				 message="details";
				break;
			}*/
		
		

			/***************/
			Discovery();
			/***************/
			
			sendmessage(sl);
			
			/*****************/
			if(btSocket.isConnected()==true)
			{	
				try {
					btSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/*****************/
		
		}
	});
	

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}
	
	//////////////////////////////// new section
	/*String ti="title";
	public void refresh(View v) {
		
		sendrefreshmessage(ti);
		
		

	}*/
	
	
	
	 public  void sendmessage(String messageSending){
    	 
/*		   BA = BluetoothAdapter.getDefaultAdapter();	
  	   BluetoothDevice device = BA.getRemoteDevice(address);
         
		    
		    //   Two things are needed to make a connection:
		    //   A MAC address, which we got above.
		    //   A Service ID or UUID.  In this case we are using the
		    //   UUID for SPP.
		    
		    try {
		    	 
		         btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
		    } catch (IOException e) {
		        System.out.print("Fatal Error In onResume() and socket create failed: " + e.getMessage() + ".");
		    }

		    // Discovery is resource intensive.  Make sure it isn't going on
		    // when you attempt to connect and pass your message.
		    BA.cancelDiscovery();

		    // Establish the connection.  This will block until it connects.
	         try {
		        btSocket.connect();
		        System.out.print("\n...Connection established and data link opened...");
		    } catch (IOException e) {
		      try {
		        btSocket.close();
		      
		      } catch (IOException e2) {
		    	  System.out.print("Fatal Error In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
		      }
	         }*/
	         
	         try {
			       outStream = btSocket.getOutputStream();
			    } catch (IOException e) {
			      System.out.print("Fatal Error In onResume() and output stream creation failed:" + e.getMessage() + ".");
			    }

			
				byte[] msgBuffer =   messageSending.getBytes();
			    try {
			        outStream.write(msgBuffer);
			        /***************/
			        outStream.flush();
			        messageSending=null;
			        msgBuffer=null;
			        /***************/
			    } catch (IOException e) {
			      String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
			      if (address.equals("00:00:00:00:00:00")) 
			        msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 37 in the java code";
			      msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

			      System.out.print("Fatal Error"+msg);       
			    }
			    try {
					reciveMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		    try {
//			    	String dd=reciveMessage();
//			    //	CharSequence cc=dd.subSequence(0, dd.length());
//					//t1.setText(cc);
//			    	System.out.println(dd);
//			    	
//			    	  Toast.makeText(getApplicationContext(),dd ,Toast.LENGTH_LONG).show();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
// close();
    }
   public void reciveMessage() throws IOException

	  {
       inStream = btSocket.getInputStream();
	        byte[] buf=new byte[1024];
	        int begin = 0;
			int bytes = 0;
			bytes +=inStream.read(buf, bytes, buf.length - bytes);
			mHandler.obtainMessage(1, begin, 0, buf).sendToTarget();
			/**************/
			btSocket.close();
			/**************/
	  }
   ////////////////
   
   public void Discovery()
	  {
		    
		    // Set up a pointer to the remote node using it's address.
		   
		    BluetoothDevice device = BA.getRemoteDevice(address);
             
		    
		    //   Two things are needed to make a connection:
		    //   A MAC address, which we got above.
		    //   A Service ID or UUID.  In this case we are using the
		    //   UUID for SPP.
		    
		    try {
		    	 
		         btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
		    } catch (IOException e) {
		        System.out.print("Fatal Error In onResume() and socket create failed: " + e.getMessage() + ".");
		    }

		    // Discovery is resource intensive.  Make sure it isn't going on
		    // when you attempt to connect and pass your message.
		    BA.cancelDiscovery();

		    // Establish the connection.  This will block until it connects.
	         try {
		        btSocket.connect();
		        System.out.print("\n...Connection established and data link opened...");
		    } catch (IOException e) {
		      try {
		        btSocket.close();
		       // return false;
		      } catch (IOException e2) {
		    	  System.out.print("Fatal Error In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
		      }
	         }
	        // return true;
	         
   }
	  /////////////////////////////////
   
   
   
   
   
   public  void sendrefreshmessage(String messageSending){
  	 

	   	         
	   	         try {
	   			       outStream = btSocket.getOutputStream();
	   			    } catch (IOException e) {
	   			      System.out.print("Fatal Error In onResume() and output stream creation failed:" + e.getMessage() + ".");
	   			    }

	   			 //   String message="hello server";
	   				byte[] msgBuffer =   messageSending.getBytes();
	   			    try {
	   			        outStream.write(msgBuffer);
	   			    } catch (IOException e) {
	   			      String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
	   			      if (address.equals("00:00:00:00:00:00")) 
	   			        msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 37 in the java code";
	   			      msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

	   			      System.out.print("Fatal Error"+msg);       
	   			    }
	   			    reciverefreshMessage();
	       }
	private void reciverefreshMessage() {
		// TODO Auto-generated method stub
	       try {
			inStream = btSocket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        byte[] buf=new byte[1024];
	        int begin = 0;
			int bytes = 0;
			try {
				bytes +=inStream.read(buf, bytes, buf.length - bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newHandler.obtainMessage(1, begin, 0, buf).sendToTarget();
	}
	/// notification
	//   Toast.makeText(getApplicationContext(),writeMessage,Toast.LENGTH_LONG).show();
	 
/*		   NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		   Notification notify= new Notification(android.R.drawable.stat_notify_more,"Shop information ",System.currentTimeMillis() );
		   Context context=Third.this;
		   CharSequence title = "You have been notified";
		   Intent i3=new Intent(context,Third.class);  
		   PendingIntent pa=PendingIntent.getActivity(context, 0, i3, 0); 
		   notify.setLatestEventInfo(context, title, writeMessage, pa);
		   notify.sound=Uri.parse("android.resource://leebrimelow.status/"+R.raw.beep);
		   nm.notify(0, notify);
		   Toast.makeText(getApplicationContext(),writeMessage,Toast.LENGTH_LONG).show();*/

	
	/////// close methode
	/*    public void close(){
	    if (outStream != null) {
	      try {
	        outStream.flush();
	      } catch (IOException e) {
	        System.out.print("Fatal Error In onPause() and failed to flush output stream: " + e.getMessage() + ".");
	      }
	    }

	    try     {
	      btSocket.close();
	    } catch (IOException e2) {
	    	 System.out.print("Fatal Error In onPause() and failed to close socket." + e2.getMessage() + ".");
	    
  }
}*/
}
