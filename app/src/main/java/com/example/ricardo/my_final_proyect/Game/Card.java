package com.example.ricardo.my_final_proyect.Game;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;import java.lang.Math;


public class Card extends FrameLayout {

	private int value;
	//Tablero
	//2
	//4
	private int[] color = {
			0xffbbada0,
			0xfff7da64,
			0xfffac51c,
			0xfffba026,
			0xfff37934,
			0xff54acd2,
			0xff3d8eb9,
			0xff1abc9c,
			0xff9365b8,
			0xff553982,
			0xffEE3B3B,
			0xffEE2C2C,
			0xffEEC900,
			0xffEEEE00,
			0xffEEEE00,
			0xffEEEE00
	};
	private TextView label = new TextView(getContext());
	public Card(Context context) {super(context);init();}

	public void init(){
		value = 0;
		label.setTextSize(36);
		label.setBackgroundColor(color[value]);
		label.setGravity(Gravity.CENTER);
		label.setTextColor(0xffffffff);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.setMargins(5, 5, 5, 5);
		addView(label, lp);
	}

	public boolean isEqual(Card other) {
		if (value == other.getValue())
			return true;
		else
			return false;
	}

	public void plus() {
		value++;
		return;
	}

	public void setValue(int task){
		value = task;
		return;
	}

	public void refresh() {
		if (value == 0) {
			label.setText("");
		} else {
			int number = (int) Math.pow(2, value);
			label.setText("" + number);
		}
		label.setBackgroundColor(color[value]);
	}

	public int getValue() {
		return value;
	}

}
