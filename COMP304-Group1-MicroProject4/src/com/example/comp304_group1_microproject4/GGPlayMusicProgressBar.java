package com.example.comp304_group1_microproject4;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GGPlayMusicProgressBar extends Activity {

	final Context context = this;
	
	Random randNum = new Random();
	int num = randNum.nextInt(100) + 1;
	
	int score = 100;
	int chance = 10;
	
	MediaPlayer mp;
	ProgressBar progress;
	TextView txtProgress;
	Button btnPause;
	
	int currentPosition;
	int length;	
	
	GGPlayMusicProgressBar self = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ggplay_music_progress_bar);
		
		mp = MediaPlayer.create(this, R.raw.gummybear);
		progress = (ProgressBar) findViewById(R.id.pbProgress);	
		
		mp.setLooping(true);
		mp.start();
		
		btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mp.isPlaying()) {
					mp.pause();
					self.btnPause.setText("Play Song");
				}
				else {
					mp.start();
					self.btnPause.setText("Pause Song");
				}
					
			}
		});
        
		new Thread(new Runnable() {
            public void run() {
        	   	currentPosition = 0;
            	length = mp.getDuration();
            	
        		progress.setMax(length);
        		
            	while (mp != null && currentPosition < length) {
        			try {
        				Thread.sleep(1000);
        				currentPosition = mp.getCurrentPosition();
        			}
        			catch (Exception e) {
        				return;
        			}
        			progress.setProgress(currentPosition);
        		}
            }
        }).start();
		
		TextView tv = (TextView)findViewById(R.id.lblSongLength);
		tv.setText("Song Length: " + mp.getDuration() + "ms");
		
		Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				TextView lblMessage = (TextView) findViewById(R.id.lblMessage);
				EditText txtInput = (EditText) findViewById(R.id.txtInput);
				
				if (txtInput.getText().toString().equalsIgnoreCase("")) {
		        	lblMessage.setText("Please enter a number between 0 and 100!");
		        }
				else
				{
					int guess = Integer.parseInt(txtInput.getText().toString());
			        
			        if (guess < 0 || guess > 100) {
			        	lblMessage.setText("Please enter a number between 0 and 100!");
			        }
			        else {
			        	if (guess == num) {
			        		mp.stop();
				        	showWinMessageBox();
				        }
				        else {     	
				        	score -= 10;
				        	chance--;
				        	
				        	if (guess > num) {
				        		lblMessage.setText("The number you entered is greater than the number chosen by the program. Try again!");
				        	}
				        	else {
				        		lblMessage.setText("The number you entered is less than the number chosen by the program. Try again!");
				        	}
				        	
				        	TextView lblScore = (TextView) findViewById(R.id.lblScore);
				        	lblScore.setText("Score: " + score + "%");
				        	TextView lblChance = (TextView) findViewById(R.id.lblChance);
				        	lblChance.setText("Chances Left: " + chance);
				        }
			        }
			        
			        if (chance == 0) {
			        	mp.stop();
			        	showLoseMessageBox();
			        }
				}
		        
			
			}
        });
		        
	}

	private void showWinMessageBox() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	builder.setTitle("Guessing Game");
    	builder.setMessage("Congratulations! You won with a score of " + score + "%!");
    	builder.setCancelable(false);
    	
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GGPlayMusicProgressBar.this, MainGGPlayMusicProgressBar.class);
				startActivity(intent);
	        	
			}
		});
    	
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	
    }

	private void showLoseMessageBox() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	builder.setTitle("Guessing Game");
    	builder.setMessage("Game Over!");
    	builder.setCancelable(false);
    	
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GGPlayMusicProgressBar.this, MainGGPlayMusicProgressBar.class);
				startActivity(intent);
	        	
			}
		});
    	
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	
    }

	@Override
	protected void onPause() { 
		super.onPause(); 
		mp.stop(); 
		Intent intent = new Intent(GGPlayMusicProgressBar.this, MainGGPlayMusicProgressBar.class);
		startActivity(intent);
	}
	
	public void songClick(View view)
	{
		mp.stop();
		progress = (ProgressBar) findViewById(R.id.pbProgress);
		progress.setProgress(0);

		if (view.getId() == R.id.rbtnGummy)
		{
			mp = MediaPlayer.create(this, R.raw.gummybear);
		}
		else if (view.getId() == R.id.rbtnLetItGo)
		{
			mp = MediaPlayer.create(this, R.raw.letitgo);
		}

		mp.setLooping(true);
		mp.start();
		btnPause.setText("Pause Song");
		
		new Thread(new Runnable() {
            public void run() {
            	currentPosition = 0;
            	length = mp.getDuration();
        		
        		progress.setMax(length);
        		
            	while (mp != null && currentPosition < length) {
        			try {
        				Thread.sleep(1000);
        				currentPosition = mp.getCurrentPosition();	
        			}
        			catch (Exception e) {
        				return;
        			}
		 			progress.setProgress(currentPosition);
        		}
            }
        }).start();
	
		TextView tv = (TextView)findViewById(R.id.lblSongLength);
		tv.setText("Song Length: " + mp.getDuration() + "ms");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
