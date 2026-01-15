package com.fbt.utility;

import java.time.LocalTime;

public class OverlapChecker {
	public static boolean isOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
		return start1.isBefore(end2) && end1.isAfter(start2);
	}
}
