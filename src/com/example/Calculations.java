package com.example;

import java.sql.Timestamp;

public class Calculations {
	
	public static int compareToMinutes(Timestamp tA, Timestamp tB) {
		int diff = Math.round(Math.abs(tA.getTime() - tB.getTime()) / 60000);
		return diff;
	}
	
	public static long computeDistance(float latA, float lonA, float latB, float lonB) {
		int r = 6371; // km
		double phiA = Math.toRadians(latA);
		double phiB = Math.toRadians(latB);
		double deltaPhi = Math.toRadians(latB-latA);
		double deltaLambda = Math.toRadians(lonB-lonA);

		double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) +
		        Math.cos(phiA) * Math.cos(phiB) *
		        Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return Math.round(r * c);
	}
	
	public static int diff (float a, float b) {
		return Math.round(Math.abs(a - b));
	}
}
