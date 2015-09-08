package com.example.singingwithnina2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Start extends Activity{
	
	private Button start;
	private Button scores;
	private Button info;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		start = (Button) findViewById(R.id.startButton);
		scores= (Button) findViewById(R.id.scoresButton);
		info = (Button) findViewById(R.id.infoButton);
		
		
		
		
		SharedPreferences scoreData = this.getSharedPreferences(
			      "com.example.singingwithnina2", Context.MODE_PRIVATE);
		
	
		
		
}
	
public void onClick(View v){
	
		
				
		switch(v.getId()) {
		default:
			break;
		case R.id.startButton:
			finish();
			Intent intent = new Intent(this, Instrument.class);
			startActivity(intent);
			break;
			
		case R.id.scoresButton:
			finish();
			Intent intent2 = new Intent(this, HighScores.class);
			startActivity(intent2);
			break;
			
		case R.id.infoButton:
			finish();
			Intent intent3 = new Intent(this, Information.class);
			startActivity(intent3);
			break;
		
		
	}
}


public void onQuit(View v) {
	

	
	
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
   
    builder.setMessage("Exit Singing with Nina?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) { 
                        	
                        	
                        	
                        	Toast toast = Toast.makeText(getApplicationContext(), "See you soon!",
                        			   Toast.LENGTH_SHORT);
                        	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        	
                        	toast.show();
                        	finish();
                        	
                        	
                        	
                        	

                        }
                    })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
    AlertDialog alert = builder.create();
    alert.show();
    
    
}

}