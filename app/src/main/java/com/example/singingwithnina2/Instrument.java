package com.example.singingwithnina2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Instrument extends Activity{
	private ImageView guitar;
	private ImageView piano;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instrument);
		
		guitar = (ImageView) findViewById(R.id.guitarButton);
		piano= (ImageView) findViewById(R.id.pianoButton);

		guitar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				Intent intent = new Intent(Instrument.this, PlaySingActivity.class);
				intent.putExtra("instrument", "guitar");
				startActivity(intent);
				
			}
		});
		
		piano.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(Instrument.this, PlaySingActivity.class);
				intent.putExtra("instrument", "piano");
				startActivity(intent);
			}
		});
	}
}
