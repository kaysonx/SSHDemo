package me.utlight.ssh.converters;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class SSHDateconverter extends StrutsTypeConverter{

	private DateFormat dateFormat;
	
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
	}
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if(toClass == Date.class){
			try {
				return dateFormat.parse(values[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if(o instanceof Date){
			return dateFormat.format((Date)o);
		}
		return null;
	}

}
