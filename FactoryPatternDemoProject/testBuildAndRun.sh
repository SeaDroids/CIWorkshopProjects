ant -f ./build.xml clean build

adb devices

DEVICE_SERIAL="015d1689510bfa13" #asus nexus 7 2012
#DEVICE_SERIAL="c43ed3b1" #samsung galaxy S5 

adb -s $DEVICE_SERIAL push ./bin/Demo.jar /sdcard/

adb -s $DEVICE_SERIAL shell uiautomator runtest /sdcard/Demo.jar