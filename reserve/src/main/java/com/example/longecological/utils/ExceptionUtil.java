package com.example.longecological.utils;

public class ExceptionUtil {

	public static String getExceptionAllinformation(Exception e){
        String sOut = "";
        sOut += e.getMessage() + "\r\n";
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
	}
}
