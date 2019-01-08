/*package com.example.mybleproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class ConnectManager {

	  public  static final int REQUEST_ENABLE_BT = 1;
	  private BluetoothAdapter btAdapter         = null;   
	  private BluetoothSocket  btSocket          = null;
	  private OutputStream     outStream         = null;
	  private InputStream      inStream          = null;

	  // Well known SPP UUID
	  private static final UUID MY_UUID =
	      UUID.fromString("00001101-0000-1000-8000-74F06DCF9AC9");

	  // Insert your server's MAC address B4527EC28F36 
	  private static String address = "74:F0:6D:CF:9A:C9";

	  //Contractor   ...........................
	  public  ConnectManager()
	  {
		  btAdapter = BluetoothAdapter.getDefaultAdapter();
	  }
	  
	  //to discovery a server and connect to via bluetooth .............................
	  public boolean Discovery()
	  {
		    
		    // Set up a pointer to the remote node using it's address.
		   
		    BluetoothDevice device = btAdapter.getRemoteDevice(address);
                
		    
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
		    btAdapter.cancelDiscovery();

		    // Establish the connection.  This will block until it connects.
	         try {
		        btSocket.connect();
		        System.out.print("\n...Connection established and data link opened...");
		    } catch (IOException e) {
		      try {
		        btSocket.close();
		        return false;
		      } catch (IOException e2) {
		    	  System.out.print("Fatal Error In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
		      }
	         }
	         return true;
	         
      }
	  
	  
	  //..........................................
	  //NOTE    client (andriod App)    server(java App)
	  //..........................................
	  
	  
	  //to send message of client to server ...............................
	  public void sendMessage(String message)
	  {
		  try {
		       outStream = btSocket.getOutputStream();
		    } catch (IOException e) {
		      System.out.print("Fatal Error In onResume() and output stream creation failed:" + e.getMessage() + ".");
		    }

		    byte[] msgBuffer = message.getBytes();
		    try {
		        outStream.write(msgBuffer);
		    } catch (IOException e) {
		      String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
		      if (address.equals("00:00:00:00:00:00")) 
		        msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 37 in the java code";
		      msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

		      System.out.print("Fatal Error"+msg);       
		    }
	  }

	  //to receive message of server to client  ..........................
	  public String reciveMessage() throws IOException

	  {
		        inStream = btSocket.getInputStream();
		        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
		        String lineRead=bReader.readLine();
		        return lineRead;
	  }
    
	  //to end connect ..............................
      public void close(){
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
}

	 //setter   &    getter   .........................
      public BluetoothAdapter getBtAdapter() {
		return btAdapter;
	}

	
      public void setBtAdapter(BluetoothAdapter btAdapter) {
		this.btAdapter = btAdapter;
	}

	
      public BluetoothSocket getBtSocket() {
		return btSocket;
	}

	
      public void setBtSocket(BluetoothSocket btSocket) {
		this.btSocket = btSocket;
	}

	
      public OutputStream getOutStream() {
		return outStream;
	}

	
      public void setOutStream(OutputStream outStream) {
		this.outStream = outStream;
	}

	
      public InputStream getInStream() {
		return inStream;
	}


      public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}
      
      
}*/
