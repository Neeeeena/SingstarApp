package com.example.singingwithnina2;

import android.app.Activity;
import android.graphics.PorterDuff;


import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.*;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;



public class GrapherTwo extends Activity{
	
	private Button back;
	private EditText nameText;
	private int score;
	private int finalscore;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grapher);
		
		
		SharedPreferences scoreData = this.getSharedPreferences(
			      "com.example.singingwithnina2", Context.MODE_PRIVATE);    //to store the high scores + names
		
		Map<String,Integer> things = (Map<String, Integer>) scoreData.getAll();
		
		
		ArrayList<Integer> melodyData;
		ArrayList<Integer> singData;
		int score;
		
		int i = 200; //placeholder
		GraphViewData[] data = new GraphViewData[i];
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		singData = extras.getIntegerArrayList("sing");
		melodyData = extras.getIntegerArrayList("melody");
		back = (Button) findViewById(R.id.backButton);
		
		
		
		int smallest = 0;
		if (singData.size() > melodyData.size()){smallest = melodyData.size();}
		else{smallest = singData.size();}
		
		for (int w = 0; w<smallest;w++){
			if (singData.get(w) > 1000){singData.set(w, (Integer) 1000);}
			if (melodyData.get(w) > 1000){melodyData.set(w, (Integer) 1000);}
			
		//This loop is an attempt to remove horrible spikes. Basically makes the biggest possible value 1000, or whatever we set there.
		}
		
		
		setContentView(R.layout.activity_grapher);

		
		//i = singData.size() ;
		i = smallest;
		data = 	new GraphViewData[i];
		for (int element = 0;element<singData.size() && element<melodyData.size(); element++){ //Replaced melody values for newmelody just for testing, reverse
			int current = singData.get(element);
			data[element] = new GraphViewData(element, current);
					
					
			}
		data[0] = new GraphViewData(0,0);
		
		
		GraphViewSeries singSeries = new GraphViewSeries("PseudoSing", new GraphViewSeriesStyle(Color.WHITE, 1), data);
		
		
		
	//	i = melodyData.size();
		i = smallest;
		data = 	new GraphViewData[i];
		for (int element = 0;element<melodyData.size() && element<singData.size(); element++){
			int current = melodyData.get(element);
			data[element] = new GraphViewData(element, (current));
					
					
			}
	
		data[0] = new GraphViewData(0,0);

		GraphViewSeries melodySeries = new GraphViewSeries("Melody", new GraphViewSeriesStyle(Color.rgb(255, 153, 0) , 3), data);
		LineGraphView graphView = new LineGraphView(this, "Results");	
		graphView.setDrawBackground(false);
		graphView.addSeries(singSeries);
		graphView.setDrawBackground(false);

		
		graphView.getGraphViewStyle().setGridColor(Color.BLACK);
		graphView.getGraphViewStyle().setNumHorizontalLabels(0);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		
		
		GraphView graphView2 = new LineGraphView(this, "Results 2");
		graphView.addSeries(melodySeries);
		graphView2.getGraphViewStyle().setGridColor(Color.BLACK);
		graphView2.getGraphViewStyle().setNumHorizontalLabels(0);
		graphView2.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		
		//
		LinearLayout layout = (LinearLayout) findViewById(R.id.subLayout);
		layout.addView(graphView);
		layout.addView(graphView2);
		
		
		score = 0;
		for (int x = 0 ; x < melodyData.size() && x < singData.size(); x++){
			score = score + Math.abs(melodyData.get(x) - singData.get(x)); //the greater the score, the worse you did, we can then use this somehow
		}
		
		double numberOfStars= 0.0;
		if (score > 100000){numberOfStars=0.5;}
		else if (score > 80000){numberOfStars=1;}
		else if (score > 70000){numberOfStars=1.5;}
		else if (score > 60000){numberOfStars=2;}
		else if (score > 50000){numberOfStars=2.5;}
		else if (score > 40000){numberOfStars=3;}
		else if (score > 30000){numberOfStars=3.5;}
		else if (score > 20000){numberOfStars=4;}
		else if (score > 10000){numberOfStars=4.5;}
		else{numberOfStars=5;}
		float numberOfStars1 = (float) numberOfStars;
		RatingBar scoreBar = (RatingBar) findViewById(R.id.scoreBar);
		
		scoreBar.setRating(numberOfStars1);
		LayerDrawable stars = (LayerDrawable) scoreBar.getProgressDrawable();
		stars.getDrawable(2).setColorFilter(Color.rgb(255, 153, 00), PorterDuff.Mode.SRC_ATOP);
		
		
		int max = 100000; //replace this by a really bad value, this will give one star and a score of too bad
		if (score>max){score = max;}
		
		score = (max - score) / 1000;
		
		
		
		
		TextView scoreSend = (TextView) findViewById(R.id.yourScore);
		Integer score1 = (Integer)score;
		String score2 = " " + (score1.toString() + "/100");
		CharSequence score3 = (CharSequence) score2;
		scoreSend.append(score3);
		
		
		finalscore = score;
		
		
		
	
	}
	
	public void onClick(View v){
		SharedPreferences scoreData = this.getSharedPreferences(
			      "com.example.singingwithnina2", Context.MODE_PRIVATE);
		
		
		
		
		
		
		switch(v.getId()) {
		default:
			break;
		case R.id.backButton:
			finish();
			Intent intent = new Intent(this, Instrument.class);
			startActivity(intent);
			break;
			
		case R.id.saveButton:
			
			nameText   = (EditText)findViewById(R.id.nameText);
			String name = nameText.getText().toString();
			
			
			scoreData.edit().putInt(name, finalscore).commit();
			finish();
			Intent intent2 = new Intent(this, HighScores.class);
			startActivity(intent2);
			break;
			
		
		
		
	}
	}
	
	@Override
	public void onBackPressed(){
		finish();
		Intent intent = new Intent(this, PlaySingActivity.class);
		startActivity(intent);
	}
	

	
}
