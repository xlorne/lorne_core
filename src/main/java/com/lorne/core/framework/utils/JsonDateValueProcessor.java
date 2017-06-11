package com.lorne.core.framework.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsonDateValueProcessor implements JsonValueProcessor {
	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {
		super();
	}

	public Object processArrayValue(Object paramObject,
                                    JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	public Object processObjectValue(String paramString, Object paramObject,
                                     JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	private Object process(Object value) {
		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			return sdf.format(value);
		} else {
			return value;
		}
	}

}
