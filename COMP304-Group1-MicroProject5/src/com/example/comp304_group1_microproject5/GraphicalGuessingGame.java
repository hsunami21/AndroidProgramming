package com.example.comp304_group1_microproject5;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GraphicalGuessingGame extends Activity {

	final Context context = this;
	
	Random randNum = new Random();
	int num = randNum.nextInt(100) + 1;
	
	int guess;
	int score = 100;
	int chance = 10;
	TextView lblMessage;
	TextView tv1;
	
	MediaPlayer mp;
	ProgressBar progress;
	TextView txtProgress;
	Button btnPause;
	
	int currentPosition;
	int length;	
	
	GraphicalGuessingGame self = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphical_guessing_game);
		
		final Paint paint = new Paint();
        final Bitmap bg = Bitmap.createBitmap(480, 400, Bitmap.Config.ARGB_8888);
        final Canvas can = new Canvas(bg);
       
//        // HEAD
//        paint.setStyle(Style.FILL);
//        paint.setColor(Color.BLACK);
//        can.drawOval(new RectF(90, 20, 210, 160), paint);
//        paint.setColor(Color.WHITE);
//        can.drawOval(new RectF(110, 60, 140, 75), paint);
//        can.drawOval(new RectF(160, 60, 190, 75), paint);
//        can.drawOval(new RectF(143, 85, 157, 105), paint);
//        can.drawOval(new RectF(125, 115, 175, 135), paint);	
//        
//        // EARS
//        paint.setStyle(Style.FILL);
//        paint.setColor(Color.BLACK);
//        can.drawOval(new RectF(70, 65, 100, 115), paint);
//        can.drawOval(new RectF(200, 65, 230, 115), paint);
//        
//        // NECK
//        paint.setStyle(Style.FILL);
//        paint.setColor(Color.BLACK);
//        can.drawRect(140, 150, 160, 190, paint);
//        
//        // BODY
//        paint.setStyle(Style.FILL);
//        paint.setColor(Color.BLACK);
//        can.drawRect(120, 180, 180, 370, paint);
//        
//        // ARMS
//        paint.setStyle(Style.FILL);
//        paint.setColor(Color.BLACK);
//        
//        Path path1 = new Path();
//        path1.reset();
//        path1.moveTo(120, 180);
//        path1.lineTo(15, 320);
//        path1.lineTo(25, 330);
//        path1.lineTo(130, 190);
//        
//        can.drawPath(path1, paint);
//        
//        Path path2 = new Path();
//        path2.reset();
//        path2.moveTo(180, 180);
//        path2.lineTo(285, 320);
//        path2.lineTo(275, 330);
//        path2.lineTo(170, 190);
//        
//        can.drawPath(path2, paint);
//        
//        // LEGS
//        paint.setStyle(Style.FILL);
//        paint.setColor(Color.BLACK);
//        
//        Path path3 = new Path();
//        path3.reset();
//        path3.moveTo(120, 370);
//        path3.lineTo(85, 540);
//        path3.lineTo(100, 540);
//        path3.lineTo(140, 360);
//        
//        can.drawPath(path3, paint);
//        
//        Path path4 = new Path();
//        path4.reset();
//        path4.moveTo(180, 370);
//        path4.lineTo(215, 540);
//        path4.lineTo(200, 540);
//        path4.lineTo(160, 360);
//        
//        can.drawPath(path4, paint);
//
//
//        TextView l = (TextView) findViewById(R.id.drawarea);
//        l.setBackgroundDrawable(new BitmapDrawable(bg));
        
        
        
        
        
//		mp = MediaPlayer.create(this, R.raw.gummybear);
//		progress = (ProgressBar) findViewById(R.id.pbProgress);	
//		
//		mp.setLooping(true);
//		mp.start();
//		
//		btnPause = (Button) findViewById(R.id.btnPause);
//        btnPause.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (mp.isPlaying()) {
//					mp.pause();
//					self.btnPause.setText("Play Song");
//				}
//				else {
//					mp.start();
//					self.btnPause.setText("Pause Song");
//				}
//					
//			}
//		});
//        
//		new Thread(new Runnable() {
//            public void run() {
//        	   	currentPosition = 0;
//            	length = mp.getDuration();
//            	
//        		progress.setMax(length);
//        		
//            	while (mp != null && currentPosition < length) {
//        			try {
//        				Thread.sleep(1000);
//        				currentPosition = mp.getCurrentPosition();
//        			}
//        			catch (Exception e) {
//        				return;
//        			}
//        			progress.setProgress(currentPosition);
//        		}
//            }
//        }).start();
//		
//		TextView tv = (TextView)findViewById(R.id.lblSongLength);
//		tv.setText("Song Length: " + mp.getDuration() + "ms");
		
		Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				lblMessage = (TextView) findViewById(R.id.lblMessage);
				EditText txtInput = (EditText) findViewById(R.id.txtInput);
				
				if (txtInput.getText().toString().equalsIgnoreCase("")) {
		        	lblMessage.setText("Please enter a number between 0 and 100!");
		        }
				else
				{
					guess = Integer.parseInt(txtInput.getText().toString());
			        
			        if (guess < 0 || guess > 100) {
			        	lblMessage.setText("Please enter a number between 0 and 100!");
			        }
			        else {
			        	if (guess == num) {
//			        		mp.stop();
				        	showWinMessageBox();
				        }
				        else {     	
				        	score -= 10;
				        	chance--;
				        	drawHangMan(paint, bg, can);
				        	
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
//			        	mp.stop();
			        	showLoseMessageBox();
			        }
				}
		        
			
			}
        });
        
        
        // CLICKING ON TABLE CELL 1
        tv1 = (TextView) findViewById(R.id.tv1);
		guess = Integer.parseInt(tv1.getText().toString());
			
		tv1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lblMessage = (TextView) findViewById(R.id.lblMessage);

				if (guess == num) {
//		    		mp.stop();
		        	showWinMessageBox();
		        }
		        else {     	
		        	score -= 10;
		        	chance--;
		        	
		        	if (guess > num) {
		        		lblMessage.setText("The number you entered is greater than the number chosen by the program. Try again!");
		        		tv1.setBackgroundColor(Color.RED);
		        	}
		        	else {
		        		lblMessage.setText("The number you entered is less than the number chosen by the program. Try again!");
		        		tv1.setBackgroundColor(Color.YELLOW);
		        	}
		        	
		        	TextView lblScore = (TextView) findViewById(R.id.lblScore);
		        	lblScore.setText("Score: " + score + "%");
		        	TextView lblChance = (TextView) findViewById(R.id.lblChance);
		        	lblChance.setText("Chances Left: " + chance);
		        }
				
				if (chance == 0) {
//		        	mp.stop();
		        	showLoseMessageBox();
		        }
			}
		});
		
        
    
	}

	private void drawHangMan(Paint paint, Bitmap bg, Canvas can) {
		if (chance == 9) {
			// HEAD
	        paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        can.drawOval(new RectF(90, 20, 210, 160), paint);
	        paint.setColor(Color.WHITE);
	        can.drawOval(new RectF(110, 60, 140, 75), paint);
	        can.drawOval(new RectF(160, 60, 190, 75), paint);
	        can.drawOval(new RectF(143, 85, 157, 105), paint);
	        can.drawOval(new RectF(125, 115, 175, 135), paint);	
		} else if (chance == 8) {	        
	        // EARS
	        paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        can.drawOval(new RectF(70, 65, 100, 115), paint);
	        can.drawOval(new RectF(200, 65, 230, 115), paint);
		} else if (chance == 7) {	        
	        // NECK
	        paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        can.drawRect(140, 150, 160, 190, paint);
		} else if (chance == 6) {
	        // BODY
	        paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        can.drawRect(120, 180, 180, 370, paint);
		} else if (chance == 5) {	        
	        // LEFT ARM
	        paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        
	        Path path1 = new Path();
	        path1.reset();
	        path1.moveTo(120, 180);
	        path1.lineTo(15, 320);
	        path1.lineTo(25, 330);
	        path1.lineTo(130, 190);
	        
	        can.drawPath(path1, paint);
		} else if (chance == 4) {
	        // RIGHT ARM
			paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        
	        Path path2 = new Path();
	        path2.reset();
	        path2.moveTo(180, 180);
	        path2.lineTo(285, 320);
	        path2.lineTo(275, 330);
	        path2.lineTo(170, 190);
	        
	        can.drawPath(path2, paint);
		} else if (chance == 3) {
	        // LEFT LEG
	        paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        
	        Path path3 = new Path();
	        path3.reset();
	        path3.moveTo(120, 370);
	        path3.lineTo(85, 540);
	        path3.lineTo(100, 540);
	        path3.lineTo(140, 360);
	        
	        can.drawPath(path3, paint);
		} else if (chance == 2) {
			// RIGHT LEG
			paint.setStyle(Style.FILL);
	        paint.setColor(Color.BLACK);
	        
	        Path path4 = new Path();
	        path4.reset();
	        path4.moveTo(180, 370);
	        path4.lineTo(215, 540);
	        path4.lineTo(200, 540);
	        path4.lineTo(160, 360);
	        
	        can.drawPath(path4, paint);
		}
		
		LinearLayout l = (LinearLayout) findViewById(R.id.drawarea);
        l.setBackgroundDrawable(new BitmapDrawable(bg));
		
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
				Intent intent = new Intent(GraphicalGuessingGame.this, MainGraphicalGuessingGame.class);
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
				Intent intent = new Intent(GraphicalGuessingGame.this, MainGraphicalGuessingGame.class);
				startActivity(intent);
	        	
			}
		});
    	
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graphical_guessing_game, menu);
		return true;
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
	
	public void cellClick(View v) {
		tv1 = (TextView) findViewById(R.id.tv1);
		guess = Integer.parseInt(tv1.getText().toString());
			
		if (guess == num) {
//    		mp.stop();
        	showWinMessageBox();
        }
        else {     	
        	score -= 10;
        	chance--;
        	
        	if (guess > num) {
        		lblMessage.setText("The number you entered is greater than the number chosen by the program. Try again!");
//        		tv1.setBackgroundColor(Color.RED);
        	}
        	else {
        		lblMessage.setText("The number you entered is less than the number chosen by the program. Try again!");
//        		tv1.setBackgroundColor(Color.YELLOW);
        	}
        	
        	TextView lblScore = (TextView) findViewById(R.id.lblScore);
        	lblScore.setText("Score: " + score + "%");
        	TextView lblChance = (TextView) findViewById(R.id.lblChance);
        	lblChance.setText("Chances Left: " + chance);
        }
	}
}


