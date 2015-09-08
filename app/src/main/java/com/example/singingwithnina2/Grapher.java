package com.example.singingwithnina2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.*;



public class Grapher extends Activity{
	
	
	
	
	private ArrayList<Integer> melodyData;
	private ArrayList<Integer> singData;
	private int score;
	
	
	int i = 200; //placeholder
	private GraphViewData[] data = new GraphViewData[i];
	
	public Grapher(ArrayList<Integer> melodyData,ArrayList<Integer> singData){
		this.melodyData = melodyData;
		this.singData = singData;
		
		score = 0;
		for (int x = 0 ; x < melodyData.size() && x < singData.size(); x++){
			score = score + Math.abs(melodyData.get(x) - singData.get(x)); //the greater the score, the worse you did, we can then use this somehow
		}
	}
	
	public void Graph(){

		setContentView(R.layout.activity_grapher);

		
		i = melodyData.size();
		data = 	new GraphViewData[i];
		for (int element = 0;element<melodyData.size(); element++){
			int current = melodyData.get(element);
			double currentD = (double) current;
			data[element] = new GraphViewData(element, currentD);
					
					
			}
				
		GraphViewSeries melodySeries = new GraphViewSeries("Melody", null, data);
		
		i = singData.size();
		data = 	new GraphViewData[i];
		for (int element = 0;element<singData.size(); element++){
			int current = melodyData.get(element);
			double currentD = (double) current;
			data[element] = new GraphViewData(element, currentD);
					
					
			}
		
		//im worried that it will create the graphs with a different number of elements, I think we can trunk it to say 200
		//by using a for loop quite easily, need further testing
		
		
		
		
		
		
		GraphViewSeries singSeries = new GraphViewSeries("Voice", null, data);
		
		GraphView graphView = new BarGraphView(this, "Results");
		
		graphView.addSeries(melodySeries);
		graphView.addSeries(singSeries);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.subLayout);
		layout.addView(graphView);
		
		
		
	
	}
	
	
	public int getScore(){
		return score;
	}
	
}
