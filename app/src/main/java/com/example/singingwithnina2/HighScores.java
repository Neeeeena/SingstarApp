package com.example.singingwithnina2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.example.singingwithnina2.R.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HighScores extends Activity{
	

	private Button back;
	private Map<String, Integer> topFive;
	private ArrayList<String> topFivePlayers = new ArrayList<String>();
	private ArrayList<Integer> topFiveScores = new ArrayList<Integer>();
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		Log.e("TRY ONE", "TRY ONE");
		back = (Button) findViewById(R.id.backButton);
		
		SharedPreferences scoreData = this.getSharedPreferences(
			      "com.example.singingwithnina2", Context.MODE_PRIVATE);
		
		
		//From here until next comment is all dedicated to finding the maximum 5 values of the stored data, and their corresponding players
		
		
		Map<String,Integer> scores = (Map<String, Integer>) scoreData.getAll();
		
		
			
		Set<String> players = scores.keySet();
		//CUT TWO
		
		String[] playersArray = (String[]) players.toArray(new String[players.size()]);
		
		Integer[] scoresArray = new Integer[players.size()];
		for (int a = 0;a < playersArray.length; a++){
			scoresArray[a] = scores.get(playersArray[a]);}
		
		
		ArrayList<String> playersList = new ArrayList<String>(Arrays.asList(playersArray));
		ArrayList<Integer> scoresList = new ArrayList<Integer>(Arrays.asList(scoresArray));
		
		
		
		for (int i = 0; i<players.size(); i++){
			scoresList.add(i, scores.get(playersArray[i]));
			
		}
		
		for (int i = 0; i<players.size(); i++){
			Log.e(playersList.get(i), scoresList.get(i) + "");
			
		}
		
		
		int five = 5;
		if (playersList.size() < 5){
			five = playersList.size();
			
		}
		
		
		
	
		for (int x=0; x < five; x++){
			
			int maxIndex = 0;
			
			for (int i = 1; i < playersList.size(); i++){
			   int newnumber = scoresList.get(i);
			   if ((newnumber > scoresList.get(maxIndex))){
			   maxIndex = i;
			   }

			}

			String best = playersList.get(maxIndex);
			Integer bestScore = scoresList.get(maxIndex);

			playersList.remove(maxIndex);
			scoresList.remove(maxIndex);

			Log.e(bestScore + "", bestScore + "");
			topFivePlayers.add(best);
			topFiveScores.add(bestScore);
			
			
		
			
			
		}

		//MAX FIVE VALUES HAVE BEEN FOUND, NOW WE SEND THEM
		
		
		
		
		for(int n= 0; n < topFivePlayers.size(); n++){
			
			if (n == 0){
				TextView playerSend = (TextView) findViewById(R.id.first);
				Integer score = (Integer)topFiveScores.get(n);
				String score2 = "1. " +topFivePlayers.get(n);
				CharSequence score3 = (CharSequence) score2;
				playerSend.append(score3);
				
				TextView scoreSend = (TextView) findViewById(R.id.firstScore);
				String score4 = (score.toString() + "/100");
				CharSequence score5 = (CharSequence) score4;
				scoreSend.append(score5);
						
				
				
				}
			if (n == 1){
				TextView playerSend = (TextView) findViewById(R.id.second);
				Integer score = (Integer)topFiveScores.get(n);
				String score2 = "2. " + topFivePlayers.get(n);
				CharSequence score3 = (CharSequence) score2;
				playerSend.append(score3);
				
				TextView scoreSend = (TextView) findViewById(R.id.secondScore);
				String score4 = (score.toString() + "/100");
				CharSequence score5 = (CharSequence) score4;
				scoreSend.append(score5);
				}
			if (n == 2){
				TextView playerSend = (TextView) findViewById(R.id.third);
				Integer score = (Integer)topFiveScores.get(n);
				String score2 = "3. " + topFivePlayers.get(n);
				CharSequence score3 = (CharSequence) score2;
				playerSend.append(score3);
				
				TextView scoreSend = (TextView) findViewById(R.id.thirdScore);
				String score4 = (score.toString() + "/100");
				CharSequence score5 = (CharSequence) score4;
				scoreSend.append(score5);
				}
			if (n == 3){
				TextView playerSend = (TextView) findViewById(R.id.fourth);
				Integer score = (Integer)topFiveScores.get(n);
				String score2 = "4. " + topFivePlayers.get(n);
				CharSequence score3 = (CharSequence) score2;
				playerSend.append(score3);
				
				TextView scoreSend = (TextView) findViewById(R.id.fourthScore);
				String score4 = (score.toString() + "/100");
				CharSequence score5 = (CharSequence) score4;
				scoreSend.append(score5);
				}
			if (n == 4){
				TextView playerSend = (TextView) findViewById(R.id.fifth);
				Integer score = (Integer)topFiveScores.get(n);
				String score2 = "5. " + topFivePlayers.get(n);
				CharSequence score3 = (CharSequence) score2;
				playerSend.append(score3);
				
				TextView scoreSend = (TextView) findViewById(R.id.fifthScore);
				String score4 = (score.toString() + "/100");
				CharSequence score5 = (CharSequence) score4;
				scoreSend.append(score5);
				}
			
			
		
		}
		
		
		
		
		
}
public void onClick(View v){
		finish();
		Intent intent = new Intent(this, Start.class);
		startActivity(intent);
		
		
	}

@Override
public void onBackPressed(){
	
}


public void onClear(View v) {
	
	
	
	
	final SharedPreferences scoreData = this.getSharedPreferences(
		      "com.example.singingwithnina2", Context.MODE_PRIVATE);
	
	final Intent intent5 = new Intent(this, Start.class);
	
	
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Are you sure you want to clear the data? This can not be undone.")
            .setCancelable(false)
            .setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) { 
                        	
                        	scoreData.edit().clear().commit();
                        	
                        	Toast toast = Toast.makeText(getApplicationContext(), "Scores cleared.",
                        			   Toast.LENGTH_SHORT);
                        	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        	toast.show();
                        	
                        	
                       
                        	
                        	
                        	finish();
                        	startActivity(intent5);
                        	
                        	
                        	
                        	
                        	

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