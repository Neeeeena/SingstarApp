package com.example.singingwithnina2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Information extends Activity{
	
	private Button back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		back = (Button) findViewById(R.id.backButton);

	}
	
	
public void onClick(View v){
	
		finish();
		Intent intent = new Intent(this, Start.class);
		startActivity(intent);
	}	

@Override
public void onBackPressed(){
	finish();
	Intent intent = new Intent(Information.this, Start.class);
	startActivity(intent);
}
	
}
