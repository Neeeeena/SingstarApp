package com.example.singingwithnina2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


import android.os.Bundle;
import android.app.Activity;

import android.media.SoundPool;
import android.media.AudioManager;

import android.os.CountDownTimer;
import android.util.Log;


public class MelodyGenerator{

	private SoundPool soundPool;
	private int C1;
	private int D1;
	private int E1;
	private int F1;
	private int G1;
	private int A1;
	private int B1;
	
	private ArrayList<Integer> generatedMelody;
	private ArrayList<Integer> freq;
	private HashMap<Integer, Integer> toneValues;




	private int streamID;
	

	public MelodyGenerator(Activity activity, String instrument){
		
		generatedMelody = new ArrayList<Integer>();
		freq = new ArrayList<Integer>();
		toneValues = new HashMap<Integer, Integer>();
		toneValues.put(0, 262);
		toneValues.put(1, 294);
		toneValues.put(2, 330);
		toneValues.put(3, 349);
		toneValues.put(4, 392);
		toneValues.put(5, 440);
		toneValues.put(6, 494);
		
		
		
		
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		
		if(instrument.equals("guitar")) {
			C1 = soundPool.load(activity, R.raw.guitar_c, 1);
			D1 = soundPool.load(activity, R.raw.guitar_d, 1);
			E1 = soundPool.load(activity, R.raw.guitar_e, 1);
			F1 = soundPool.load(activity, R.raw.guitar_f, 1);
			G1 = soundPool.load(activity, R.raw.guitar_g, 1);
			A1 = soundPool.load(activity, R.raw.guitar_a, 1);
			B1 = soundPool.load(activity, R.raw.guitar_b, 1);
		}
		else {
			C1 = soundPool.load(activity, R.raw.piano_c, 1);
			D1 = soundPool.load(activity, R.raw.piano_d, 1);
			E1 = soundPool.load(activity, R.raw.piano_e, 1);
			F1 = soundPool.load(activity, R.raw.piano_f, 1);
			G1 = soundPool.load(activity, R.raw.piano_g, 1);
			A1 = soundPool.load(activity, R.raw.piano_a, 1);
			B1 = soundPool.load(activity, R.raw.piano_b, 1);
		}
		
		Log.e("TONENUMEBR", B1 + "");
		

		
	}
	
	public ArrayList<Integer> getToneValues(ArrayList<Integer> melodyData){
		ArrayList<Integer> toneData = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++){
			toneData.add(toneValues.get(melodyData.get(i)));
		}
		return toneData;
	}
			
			
	public ArrayList<Integer> generateNumbers(){
		Random r = new Random();
		ArrayList<Integer> melodyData = new ArrayList<Integer>();
		for(int i = 0 ; i<4; i++){
			melodyData.add(r.nextInt(6));
		}
		return melodyData;
	
	}
			
		public void DetNextTone(int toneNumber) {
			switch(toneNumber) {
				case 0: streamID = soundPool.play(C1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 1: streamID = soundPool.play(D1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 2: streamID = soundPool.play(E1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 3: streamID = soundPool.play(F1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 4: streamID = soundPool.play(G1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 5: streamID = soundPool.play(A1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 6: streamID = soundPool.play(B1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
			}
		}
	
			
	public ArrayList<Integer> Generate(){
		generatedMelody = generateNumbers();
		return getToneValues(generatedMelody);
	}
	
	public void Play(){
		new CountDownTimer(5000, 1000) {
			int counter = 0;

		     public void onTick(long millisUntilFinished) {
					DetNextTone(generatedMelody.get(counter));
					counter++;
		     }

		     public void onFinish() {
		    	 soundPool.stop(streamID);
		     }
		  }.start();
		
	}
	
	public void stop(){
		soundPool.stop(streamID);
	}
	
}
