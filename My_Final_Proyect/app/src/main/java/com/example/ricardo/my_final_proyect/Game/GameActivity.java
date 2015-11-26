package com.example.ricardo.my_final_proyect.Game;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.example.ricardo.my_final_proyect.R;

public class GameActivity extends AppCompatActivity implements OnTouchListener {
	private GameLayout gameView;
	private HistoryStack historyRecord = new HistoryStack();
	private int[] lastCardMapValue;
	private boolean[] lastCanMove;
	private boolean[] tempCanMove;
	private SharedPreferences gameHistory;
	private SharedPreferences.Editor historyEditor;
	private int currentScore;
	private int highScore;
    private boolean isContinue = true;
	private float X, Y, offsetX, offsetY;
	private TextView currentScoreTextView;
	private TextView highScoreTextView;
	private AlertDialog.Builder gameOverDialog;
	private boolean isSaving = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		gameView = (GameLayout) findViewById(R.id.gameView);
		currentScoreTextView = (TextView) findViewById(R.id.currentScore);
		highScoreTextView = (TextView) findViewById(R.id.highScore);

		init();
	}
	private void init() {
		gameHistory = this.getSharedPreferences("gameHistory",Context.MODE_PRIVATE);
		historyEditor = gameHistory.edit();
		if(gameHistory.getBoolean("isSaved", false))
		{
			highScore = gameHistory.getInt("highScore", 0);
			this.highScoreTextView.setText(Integer.toString(highScore));
			int savedScore = gameHistory.getInt("SavedScore", 0);
			this.currentScoreTextView.setText("" + savedScore);
			gameView.setScore(savedScore);
			gameView.setCardMapValue(this.reloadCardMapValue());
			gameView.setCanMove(this.reloadCanMove());
			historyEditor.putBoolean("isSaved", false).commit();
		}
		else
		{
			highScore = gameHistory.getInt("highScore", 0);
			gameView.setScore(0);
			this.highScoreTextView.setText(" " + Integer.toString(highScore));
			this.currentScoreTextView.setText(" " + 0);
			gameView.setCanMove(new boolean[]{true,true,true,true});
			tempCanMove = lastCanMove = gameView.getCanMove();
			historyRecord.clearStack();
			gameView.clearCardMap();
			gameView.randomCard();
			gameView.randomCard();
		}
		gameView.refreshView();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
		case R.id.restart:
			this.init();
		break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		boolean isMove = false;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			X = event.getX();
			Y = event.getY();
			break;
		case MotionEvent.ACTION_UP:

			if (!historyRecord.empty())
				lastCardMapValue = historyRecord.peek();
			historyRecord.push(gameView.getCardMapValue());
			tempCanMove = gameView.getCanMove();
			offsetX = event.getX() - X;
			offsetY = event.getY() - Y;
			if (Math.abs(offsetX) > Math.abs(offsetY)) {
				if (offsetX > 5) {
					isContinue = gameView.gameRight();
					isMove = true;
				} else if (offsetX < -5) {
					isContinue = gameView.gameLeft();
					isMove = true;
				}
			} else if (Math.abs(offsetX) < Math.abs(offsetY)) {
				if (offsetY > 5) {
					isContinue = gameView.gameDown();
					isMove = true;
				} else if (offsetY < -5) {
					isContinue = gameView.gameUp();
					isMove = true;
				}
			}
			if (isMove) {
				if (gameView.canMove[0] && gameView.canMove[1]
						&& gameView.canMove[2] && gameView.canMove[3])
					gameView.randomCard();
				gameView.refreshView();
				currentScore = gameView.getScore();
				this.currentScoreTextView.setText(" "+ Integer.toString(currentScore));
				if (currentScore > highScore) {
					this.highScoreTextView.setText(" "+ Integer.toString(currentScore));
				}

				if (Arrays.equals(gameView.getCardMapValue(),
						historyRecord.peek())) {
					historyRecord.push(lastCardMapValue);
				}
				else
					lastCanMove = tempCanMove;
                 	if (currentScore > highScore) {
						historyEditor.putInt("highScore", currentScore);
						historyEditor.commit();
					}
				if (!isContinue) {
					gameOverDialog();
				}
			}
			break;
		default:
			break;
		}
		return true;
	}
	private void gameOverDialog() {
		gameOverDialog = new AlertDialog.Builder(this);
		gameOverDialog.setMessage("You Lose");
		gameOverDialog.setTitle("Game Over");
		gameOverDialog.setCancelable(false);

			gameOverDialog.setNeutralButton("Retry", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					GameActivity.this.init();
				}
			});
		gameOverDialog.setPositiveButton("Exit", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				isSaving = false;
				GameActivity.this.finish();
			}

		});

		gameOverDialog.create().show();
	}
	@Override
	protected void onStop() {

		super.onStop();
		if(isSaving)
		{
			this.uploadCardMapValue(gameView.getCardMapValue(),gameView.canMove);
		}	
	}
	private boolean uploadCardMapValue(int[]cardMapValue, boolean[]canMove)
	{
		historyEditor.putInt("SavedScore", gameView.getScore());
		for(int i = 0; i < 16; i++)
		{
			historyEditor.putInt("CardValue" + i,cardMapValue[i] );

		}
		for(int i = 0; i < 4; i++)
		{
			historyEditor.putBoolean("CanMove" + i,canMove[i]);
		}
		boolean isUploaded = historyEditor.commit();
		historyEditor.putBoolean("isSaved", isUploaded).commit();
		return isUploaded;
	}
	private int[] reloadCardMapValue()
	{
		int [] cardMapValue = new int[16];
		for(int i = 0; i < 16; i ++)
		{
			cardMapValue[i] =  gameHistory.getInt("CardValue" + i, 0);
		}
		return cardMapValue;
	}
	private boolean[] reloadCanMove()
	{
		boolean [] canMove = new boolean[4];
		for(int i = 0; i < 4; i ++)
		{
			canMove[i] =  gameHistory.getBoolean("CanMove" + i, true);
		}
		return canMove;
	}
}
