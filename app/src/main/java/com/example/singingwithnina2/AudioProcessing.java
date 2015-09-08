package com.example.singingwithnina2;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import android.widget.Button;
import android.widget.ImageView;

import com.example.singingwithnina2.RealDoubleFFT;

public class AudioProcessing extends Activity{
	
	int frequency = 8000;
    int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    private RealDoubleFFT transformer;
    int blockSize = 164;
    ArrayList<Integer> input;
    
    PlaySingActivity parent;
    Handler handler;

    boolean started = false;

    RecordAudio recordTask;
    

    //AudioRecord audioRecord;

    public AudioProcessing(final PlaySingActivity parent, Handler handler) {

        transformer = new RealDoubleFFT(blockSize);
        this.parent = parent;
        this.handler = handler;


    }

    public class RecordAudio extends AsyncTask<Void, double[], Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                // int bufferSize = AudioRecord.getMinBufferSize(frequency,
                // AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
                int bufferSize = AudioRecord.getMinBufferSize(frequency, 
                        channelConfiguration, audioEncoding); 

                AudioRecord audioRecord = new AudioRecord( 
                        MediaRecorder.AudioSource.MIC, frequency, 
                        channelConfiguration, audioEncoding, bufferSize); 

                short[] buffer = new short[blockSize];
                double[] toTransform = new double[blockSize];

                audioRecord.startRecording();

                // started = true; hopes this should true before calling
                // following while loop
                
                while (started) {
                	
                	
                    int bufferReadResult = audioRecord.read(buffer, 0,
                            blockSize);

                    for (int i = 0; i < blockSize && i < bufferReadResult; i++) {
                        toTransform[i] = (double) buffer[i] / 32768.0; // signed
                                                                        // 16
                    }                                       // bit
                        double[] fft = new double[toTransform.length];
                        for(int i=0; i<toTransform.length;i++){
                        	if(i % 2 == 0){
                        		fft[i] = toTransform[i];
                        	}
                        	else{
                        		fft[i] = 0;
                        	}
                        }
                    	transformer.ft(fft);
                    	double[] magnitude = new double[toTransform.length/2];
                    	for(int i = 0; i < magnitude.length-1; i++){
                    		double re = fft[i];
                    		double im = fft[i+1];
                    		magnitude[i] = Math.sqrt(re*re+im*im);
                    	}
                    	transformer.ft(toTransform);
                    	Integer frequency = findBiggest(magnitude) * 8000/toTransform.length;
                        Log.e("HZ values new",  frequency + " ");
                        


                        postToUI(frequency, toTransform);



                }

                audioRecord.stop();
                audioRecord.release();

            } catch (Throwable t) {
                t.printStackTrace();
                Log.e("AudioRecord", "Recording Failed");
            }
            return null;
        }

    }

    public int findBiggest(double[] toTransform){
    	double biggest = 0;
    	int biggestIndex = 0;
    	
    
    	for (int index = 0; index < toTransform.length ; index++){
    		if (toTransform[index] > biggest){
    			biggest = toTransform[index];
    			
    			biggestIndex = index;
    		
    			
    		}

    	}
    	
    	return biggestIndex;
    }
    
    private void postToUI(final Integer frequency, final double[] toTransform) {
		handler.post(new Runnable() {
			public void run() {
				parent.publishProgress(frequency, toTransform);
				
			}
		});
    }
      
    
    public void sing(){
    	started = true;
    	recordTask = new RecordAudio();
        recordTask.execute();
        new CountDownTimer (5000, 1000){
        	
        	public void onTick(long millisUntilFinished){
      
        	}
        	
        	public void onFinish(){
        		started = false;
        		recordTask.cancel(true);
        		parent.imReady(); // starts graph activity!
        	}
        
        }.start();
        

        
        
        
    }
}
