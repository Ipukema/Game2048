package com.example.ricardo.my_final_proyect.Game;

import java.util.LinkedList;
import java.util.Random;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import com.example.ricardo.my_final_proyect.Game.Card;


public class GameLayout extends GridLayout {
	private static Card[][] cardMap = new Card[4][4];
	private boolean haveBlank;
	private boolean merged;
	private boolean moved;
	public boolean canMove[] = { true, true, true, true };
	private int score = 0;
	private int cardWidth;

	public GameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView(context);
	}

	public GameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView(context);
	}

	public GameLayout(Context context) {
		super(context);
		initGameView(context);
	}
	public void setCardMapValue(int[] values) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cardMap[i][j].setValue(values[4*i+j]);
			}
		}
	}

	public int[] getCardMapValue(){
		int[] a = new int[16];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				a[4*i+j] = cardMap[i][j].getValue();
			}
		}
		return a;
	}

	public void setScore(int currentScore) {
		this.score = currentScore;
	}

	public int getScore() {
		return score;
	}

	public boolean[] getCanMove() {
		boolean [] result = new boolean[4];
		for(int i = 0; i < 4 ; i++)
		{
			result[i] = canMove[i];
		} 
		return result;
	}
	public void setCanMove(boolean[] canMove) {
		for(int i = 0; i < 4; i++)
		{
			this.canMove[i] = canMove[i];
		}	
	}

	public void initGameView(Context context) {
		initCardMap();
		this.setOnTouchListener((OnTouchListener) context);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		cardWidth = (Math.min(w, h) - 10) / 4;

		addCardView();
	}
		private void initCardMap() {
		Card c;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				c = new Card(getContext());
				cardMap[i][j] = c;
			}
		}
	}
	public void clearCardMap() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cardMap[i][j].setValue(0);
			}
		}
	}

	public void randomCard() {
	LinkedList<Integer> ll = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cardMap[i][j] != null) {
					if (cardMap[i][j].getValue() == 0) {
						ll.add(4 * i + j);
					}
				}
			}
		}
		Random rd = new Random();
		if (ll.size() == 0)
		return;
		int index = rd.nextInt(ll.size());
		int x = ll.get(index) / 4;
		int y = ll.get(index) - 4 * x;
		int value;
		if (rd.nextInt(10) == 4) {
			value = 2;
		} else {
			value = 1;
		}
		cardMap[x][y].setValue(value);
	}

	public void refreshView() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cardMap[i][j].refresh();
			}
		}
	}

	public void addCardView() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cardMap[i][j].refresh();
				addView(cardMap[i][j], cardWidth, cardWidth);
			}
		}
	}

	boolean gameLeft() {
		if (!canMove[2]){
			return true;
		}
		haveBlank = false;
		merged = false;
		moved = false;
		int fir, sec;
		for (int i = 0; i < 4; i++) {
			sec = 0;
			fir = 0;
			while (fir < 4) {
				if (cardMap[i][fir].getValue() != 0) {
					sec = fir + 1;
					while (sec < 4) {
						if (cardMap[i][sec].getValue() != 0) {
							if (cardMap[i][fir].isEqual(cardMap[i][sec])) {
								score = score + (int) Math.pow(2, cardMap[i][fir].getValue() + 1);
								cardMap[i][fir].plus();
								cardMap[i][sec].setValue(0);
								fir = sec + 1;
								merged = true;
							} else
								fir = sec;
							break;
						}
						else sec++;
					}
				}
				else fir++;
				if(sec == 4)
					break;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (fir = 0; fir < 4; fir++) {
				if (cardMap[i][fir].getValue() == 0) {
					haveBlank = true;
					sec = fir + 1;
					while (sec < 4) {
						if (cardMap[i][sec].getValue() != 0) {
							moved = true;
							cardMap[i][fir]
									.setValue(cardMap[i][sec].getValue());
							cardMap[i][sec].setValue(0);
							fir += 1;
						}
						sec++;
					}
				}
			}
		}
		if (merged == false) {
			if (moved == false) {
				canMove[2] = false;
				if(haveBlank == false){
					if (!verticalJudge()) {
						canMove[0] = canMove[1] = canMove[3] = false;
						return false;
					}
					else{
						canMove[3] = false;
						canMove[0] = canMove[1] = true;
						return true;
					}
				}
				else{
					canMove[0] = canMove[1] = canMove[3] = true;
					return true;
				}				
			}
			else{
				canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
				return true;
			}			
		}
		else{
			canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
			return true;
		}
	}


	boolean gameRight() {
		if (!canMove[3]){
			return true;
		}
		haveBlank = false;
		merged = false;
		moved = false;
		int fir, sec;
		for (int i = 0; i < 4; i++) {
			sec = 3;
			fir = 3;
			while (fir >= 0) {
				if (cardMap[i][fir].getValue() != 0) {
					sec = fir - 1;
					while (sec >= 0) {
						if (cardMap[i][sec].getValue() != 0) {
							if (cardMap[i][fir].isEqual(cardMap[i][sec])) {
                     			score = score + (int) Math.pow(2, cardMap[i][fir].getValue() + 1);
					     		cardMap[i][fir].plus();
								cardMap[i][sec].setValue(0);
								fir = sec - 1;
								merged = true;
							} else
								fir = sec;
							break;
						}
						else sec--;
					}
				}
				else fir--;
				if(sec < 0)
					break;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (fir = 3; fir >= 0; fir--) {
				if (cardMap[i][fir].getValue() == 0) {
					haveBlank = true;
					sec = fir - 1;
					while (sec >= 0) {
						if (cardMap[i][sec].getValue() != 0) {
							moved = true;
							cardMap[i][fir]
									.setValue(cardMap[i][sec].getValue());
							cardMap[i][sec].setValue(0);
							fir -= 1;
						}
						sec--;
					}
				}
			}
		}

		if (merged == false) {
			if (moved == false) {
				canMove[3] = false;
				if(haveBlank == false){
					if (!verticalJudge()) {
						canMove[0] = canMove[1] = canMove[2] = false;
						return false;
					}
					else{
						canMove[2] = false;
						canMove[0] = canMove[1] = true;
						return true;
					}
				}
				else{
					canMove[0] = canMove[1] = canMove[2] = true;
					return true;
				}				
			}
			else{
				canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
				return true;
			}			
		}
		else{
			canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
			return true;
		}
	}

	boolean gameUp() {
		if (!canMove[0]){
			return true;
		}
		haveBlank = false;
		merged = false;
		moved = false;
		int fir, sec;
		for (int i = 0; i < 4; i++) {
			sec = 0;
			fir = 0;
			while (fir < 4) {
				if (cardMap[fir][i].getValue() != 0) {
					sec = fir + 1;
					while (sec < 4) {
						if (cardMap[sec][i].getValue() != 0) {
							if (cardMap[fir][i].isEqual(cardMap[sec][i])) {
								score = score + (int) Math.pow(2, cardMap[fir][i].getValue() + 1);
  							cardMap[fir][i].plus();
								cardMap[sec][i].setValue(0);
								fir = sec + 1;
								merged = true;
							} else
								fir = sec;
							break;
						}
						else sec++;
					}
				}
				else fir++;
				if(sec == 4)
					break;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (fir = 0; fir < 4; fir++) {
				if (cardMap[fir][i].getValue() == 0) {
					haveBlank = true;
					sec = fir + 1;
					while (sec < 4) {
						if (cardMap[sec][i].getValue() != 0) {
							moved = true;
							cardMap[fir][i].setValue(cardMap[sec][i].getValue());
							cardMap[sec][i].setValue(0);
							fir += 1;
						}
						sec++;
					}
				}
			}
		}
		if (merged == false) {
			if (moved == false) {
				canMove[0] = false;
				if(haveBlank == false){
					if (!horizontalJudge()) {
						canMove[2] = canMove[1] = canMove[3] = false;
						return false;
					}
					else{
						canMove[1] = false;
						canMove[2] = canMove[3] = true;
						return true;
					}
				}
				else{
					canMove[2] = canMove[1] = canMove[3] = true;
					return true;
				}				
			}
			else{
				canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
				return true;
			}			
		}
		else{
			canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
			return true;
		}
	}
    boolean gameDown() {
		if (!canMove[1]){
			return true;
		}
		haveBlank = false;
		merged = false;
		moved = false;
		int fir, sec;
		for (int i = 0; i < 4; i++) {
			sec = 3;
			fir = 3;
			while (fir >= 0) {
				if (cardMap[fir][i].getValue() != 0) {
					sec = fir - 1;
					while (sec >= 0) {
						if (cardMap[sec][i].getValue() != 0) {
							if (cardMap[fir][i].isEqual(cardMap[sec][i])) {
								score = score + (int) Math.pow(2, cardMap[fir][i].getValue() + 1);
								cardMap[fir][i].plus();
								cardMap[sec][i].setValue(0);
								fir = sec - 1;
								merged = true;
							} else
								fir = sec;
							break;
						}
						else sec--;
					}
				}
				else fir--;
				if(sec < 0)
					break;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (fir = 3; fir >= 0; fir--) {
				if (cardMap[fir][i].getValue() == 0) {
					haveBlank = true;
					sec = fir - 1;
					while (sec >= 0) {
						if (cardMap[sec][i].getValue() != 0) {
							moved = true;
							cardMap[fir][i].setValue(cardMap[sec][i].getValue());
							cardMap[sec][i].setValue(0);
							fir -= 1;
						}
						sec--;
					}
				}
			}
		}
		if (merged == false) {
			if (moved == false) {
				canMove[1] = false;
				if(haveBlank == false){
					if (!horizontalJudge()) {
						canMove[2] = canMove[0] = canMove[3] = false;
						return false;
					}
					else{
						canMove[0] = false;
						canMove[2] = canMove[3] = true;
						return true;
					}
				}
				else{
					canMove[2] = canMove[0] = canMove[3] = true;
					return true;
				}				
			}
			else{
				canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
				return true;
			}			
		}
		else{
			canMove[0] = canMove[1] = canMove[2] = canMove[3] = true;
			return true;
		}
	}
	boolean verticalJudge() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (cardMap[j][i].isEqual(cardMap[j + 1][i])) {
					return true;
				}
			}
		}
		return false;
	}

	boolean horizontalJudge() {
	for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (cardMap[i][j].isEqual(cardMap[i][j + 1])) {
					return true;
				}
			}
		}
		return false;
	}

}
