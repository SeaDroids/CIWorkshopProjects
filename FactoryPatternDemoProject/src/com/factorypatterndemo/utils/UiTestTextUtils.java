package com.factorypatterndemo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;

public class UiTestTextUtils {

	public static void setStringByKeyEvents(CharSequence input) {
		int len = input.length();
		for (int i=0; i<len; i++){
			char character = input.charAt(i);
			sendKeyEvent(character);
			
		}
	}
	
	public static String getTimeAsStringHHmm(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
		Date date = calendar.getTime();
		String cal= dateFormat.format(date);
		return cal;
	}
	
	private static void sendKeyEvent(char character) throws IllegalArgumentException {
		UiDevice device = UiDevice.getInstance();
        switch (character) {
        case 'a':
            device.pressKeyCode(KeyEvent.KEYCODE_A);
            break;
        case 'A':
            device.pressKeyCode(KeyEvent.KEYCODE_A, KeyEvent.META_SHIFT_ON);
            break;
        case 'b':
            device.pressKeyCode(KeyEvent.KEYCODE_B);
            break;
        case 'B':
            device.pressKeyCode(KeyEvent.KEYCODE_B, KeyEvent.META_SHIFT_ON);
            break;
        case 'c':
            device.pressKeyCode(KeyEvent.KEYCODE_C);
            break;
        case 'C':
            device.pressKeyCode(KeyEvent.KEYCODE_C, KeyEvent.META_SHIFT_ON);
            break;
        case 'd':
            device.pressKeyCode(KeyEvent.KEYCODE_D);
            break;
        case 'D':
            device.pressKeyCode(KeyEvent.KEYCODE_D, KeyEvent.META_SHIFT_ON);
            break;
        case 'e':
            device.pressKeyCode(KeyEvent.KEYCODE_E);
            break;
        case 'E':
            device.pressKeyCode(KeyEvent.KEYCODE_E, KeyEvent.META_SHIFT_ON);
            break;
        case 'f':
            device.pressKeyCode(KeyEvent.KEYCODE_F);
            break;
        case 'F':
            device.pressKeyCode(KeyEvent.KEYCODE_F, KeyEvent.META_SHIFT_ON);
            break;
        case 'g':
            device.pressKeyCode(KeyEvent.KEYCODE_G);
            break;
        case 'G':
            device.pressKeyCode(KeyEvent.KEYCODE_G, KeyEvent.META_SHIFT_ON);
            break;
        case 'h':
            device.pressKeyCode(KeyEvent.KEYCODE_H);
            break;
        case 'H':
            device.pressKeyCode(KeyEvent.KEYCODE_H, KeyEvent.META_SHIFT_ON);
            break;
        case 'i':
            device.pressKeyCode(KeyEvent.KEYCODE_I);
            break;
        case 'I':
            device.pressKeyCode(KeyEvent.KEYCODE_I, KeyEvent.META_SHIFT_ON);
            break;
        case 'j':
            device.pressKeyCode(KeyEvent.KEYCODE_J);
            break;
        case 'J':
            device.pressKeyCode(KeyEvent.KEYCODE_J, KeyEvent.META_SHIFT_ON);
            break;
        case 'k':
            device.pressKeyCode(KeyEvent.KEYCODE_K);
            break;
        case 'K':
            device.pressKeyCode(KeyEvent.KEYCODE_K, KeyEvent.META_SHIFT_ON);
            break;
        case 'l':
            device.pressKeyCode(KeyEvent.KEYCODE_L);
            break;
        case 'L':
            device.pressKeyCode(KeyEvent.KEYCODE_L, KeyEvent.META_SHIFT_ON);
            break;
        case 'm':
            device.pressKeyCode(KeyEvent.KEYCODE_M);
            break;
        case 'M':
            device.pressKeyCode(KeyEvent.KEYCODE_M, KeyEvent.META_SHIFT_ON);
            break;
        case 'n':
            device.pressKeyCode(KeyEvent.KEYCODE_N);
            break;
        case 'N':
            device.pressKeyCode(KeyEvent.KEYCODE_N, KeyEvent.META_SHIFT_ON);
            break;
        case 'o':
            device.pressKeyCode(KeyEvent.KEYCODE_O);
            break;
        case 'O':
            device.pressKeyCode(KeyEvent.KEYCODE_O, KeyEvent.META_SHIFT_ON);
            break;
        case 'p':
            device.pressKeyCode(KeyEvent.KEYCODE_P);
            break;
        case 'P':
            device.pressKeyCode(KeyEvent.KEYCODE_P, KeyEvent.META_SHIFT_ON);
            break;
        case 'q':
            device.pressKeyCode(KeyEvent.KEYCODE_Q);
            break;
        case 'Q':
            device.pressKeyCode(KeyEvent.KEYCODE_Q, KeyEvent.META_SHIFT_ON);
            break;
        case 'r':
            device.pressKeyCode(KeyEvent.KEYCODE_R);
            break;
        case 'R':
            device.pressKeyCode(KeyEvent.KEYCODE_R, KeyEvent.META_SHIFT_ON);
            break;
        case 's':
            device.pressKeyCode(KeyEvent.KEYCODE_S);
            break;
        case 'S':
            device.pressKeyCode(KeyEvent.KEYCODE_S, KeyEvent.META_SHIFT_ON);
            break;
        case 't':
            device.pressKeyCode(KeyEvent.KEYCODE_T);
            break;
        case 'T':
            device.pressKeyCode(KeyEvent.KEYCODE_T, KeyEvent.META_SHIFT_ON);
            break;
        case 'u':
            device.pressKeyCode(KeyEvent.KEYCODE_U);
            break;
        case 'U':
            device.pressKeyCode(KeyEvent.KEYCODE_U, KeyEvent.META_SHIFT_ON);
            break;
        case 'v':
            device.pressKeyCode(KeyEvent.KEYCODE_V);
            break;
        case 'V':
            device.pressKeyCode(KeyEvent.KEYCODE_V, KeyEvent.META_SHIFT_ON);
            break;
        case 'w':
            device.pressKeyCode(KeyEvent.KEYCODE_W);
            break;
        case 'W':
            device.pressKeyCode(KeyEvent.KEYCODE_W, KeyEvent.META_SHIFT_ON);
            break;
        case 'x':
            device.pressKeyCode(KeyEvent.KEYCODE_X);
            break;
        case 'X':
            device.pressKeyCode(KeyEvent.KEYCODE_X, KeyEvent.META_SHIFT_ON);
            break;
        case 'y':
            device.pressKeyCode(KeyEvent.KEYCODE_Y);
            break;
        case 'Y':
            device.pressKeyCode(KeyEvent.KEYCODE_Y, KeyEvent.META_SHIFT_ON);
            break;
        case 'z':
            device.pressKeyCode(KeyEvent.KEYCODE_Z);
            break;
        case 'Z':
            device.pressKeyCode(KeyEvent.KEYCODE_Z, KeyEvent.META_SHIFT_ON);
            break;
        case '0':
            device.pressKeyCode(KeyEvent.KEYCODE_0);
            break;
        case '1':
            device.pressKeyCode(KeyEvent.KEYCODE_1);
            break;
        case '2':
            device.pressKeyCode(KeyEvent.KEYCODE_2);
            break;
        case '3':
            device.pressKeyCode(KeyEvent.KEYCODE_3);
            break;
        case '4':
            device.pressKeyCode(KeyEvent.KEYCODE_4);
            break;
        case '5':
            device.pressKeyCode(KeyEvent.KEYCODE_5);
            break;
        case '6':
            device.pressKeyCode(KeyEvent.KEYCODE_6);
            break;
        case '7':
            device.pressKeyCode(KeyEvent.KEYCODE_7);
            break;
        case '8':
            device.pressKeyCode(KeyEvent.KEYCODE_8);
            break;
        case '9':
            device.pressKeyCode(KeyEvent.KEYCODE_9);
            break;
        case ' ':
            device.pressKeyCode(KeyEvent.KEYCODE_SPACE);
            break;
        default:
            throw new IllegalArgumentException("Cannot type character " + character);
        }
	}
}
