package com.cxp.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringConvertrDate implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf =new SimpleDateFormat("");
		return null;
	}

}
