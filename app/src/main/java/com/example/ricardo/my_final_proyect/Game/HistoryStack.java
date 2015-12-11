package com.example.ricardo.my_final_proyect.Game;

import java.util.Stack;

public class HistoryStack extends Stack<int[]> {


	@Override
	public synchronized int[] pop() {
		return super.pop();
	}

	@Override
	public int[] push(int[] object) {
		clearStack();
		return super.push(object);
	}
	
	public void clearStack()
	{
		if(!empty())
		{
			pop();
		}
	}
	

}
