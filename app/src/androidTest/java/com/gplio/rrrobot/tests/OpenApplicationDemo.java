package com.gplio.rrrobot.tests;

import android.content.Context;
import android.os.Bundle;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;
import com.gplio.rrrobot.common.IRRRobotTest;

public class OpenApplicationDemo implements IRRRobotTest {
    @Override
    public String run(Context context, UiDevice device, Bundle arguments) {
        boolean res = device.openNotification();
        Log.d("RRRobot.HELLO", "Open Notification shade" + res);
        return "Hello World " + device.getCurrentPackageName();
    }
}
