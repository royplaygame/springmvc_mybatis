package com.hy.ly.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		//  将日期串转成日期类型(格式是：yyyy-MM-dd)
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			//成功直接返回
			return sdf.parse(source);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//不成功返回空
		return null;
	}

}
