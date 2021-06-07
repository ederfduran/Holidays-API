package com.example.entity;

import java.util.Date;

public class EntityUtil {
	protected boolean exists(String value) {
		return value != null && !value.isEmpty();
	}
	
	protected boolean exists(float value) {
		return value != 0.0f;
	}
	
	protected boolean exists(Date value) {
		return value != null;
	}


}
