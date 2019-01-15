# RRRobot


![Test running](https://github.com/goncalopalaio/RRRobot/blob/master/screenshots/dynamic_loading_dex_test_file.gif?raw=true)

This will dynamically load a dex file during runtime and run any UiAutomator code in it.

## Why is this kinda pointless

This is an exploration of how quick and dirty automation scripts can be done.
Since it's required to have almost all of Android SDK toolchain running and you still have to install an apk, it's kinda pointless.
I don't think dynamically loading a dex file and running some UI test code will bring much in this context.
Having a separate project with UIAutomator tests with custom parameters it's almost equivalent to this.
The main advantage of this is that you don't have to install a new apk each time you need to implement new functionality.
This might be useful to someone, I don't know.

## How to use it

Install the debug/test apk.

Compiling a single test:
./rrrobot-compile-test ../app/src/androidTest/java/com/gplio/rrrobot/tests/OpenApplicationDemo.java com.gplio.rrrobot.tests.OpenApplicationDemo.dex

This command will download the uiautomator dependencies, compile and create a .dex file.
If a device is connected it will copy the .dex file to the sdcard root.

To run the test from the .dex file in the sdcard run rrrobot-run-test by passing the .dex filename and the fully qualified name of the class.

./rrrobot-run-test com.gplio.rrrobot.tests.OpenApplicationDemo.dex com.gplio.rrrobot.tests.OpenApplicationDemo

This will trigger adb shell am instrument with custom parameters and dynamically load the dex and run the included code.
