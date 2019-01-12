package com.gplio.rrrobot.common;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;

public class HelloLog implements IRRRobot {
	
    @Override
    public String run(Context context) {
		Log.d("RRRobot.MainActivity", "I'm saying Hello from logcat");
		Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        return "Hello World!!!";
    }
}