package com.gplio.rrrobot.common;


import android.content.Context;
import android.os.Bundle;
import android.support.test.uiautomator.UiDevice;

public interface IRRRobotTest {
    String run(Context context, UiDevice device, Bundle arguments);
}
