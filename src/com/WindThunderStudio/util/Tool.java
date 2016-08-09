package com.WindThunderStudio.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;

public class Tool {
	
	/**
	 * Method to determine if the working schedule of summer is applied.
	 * The implementation is assumed, but can change.
	 * @return true if the summer working hour is applied, otherwise false.
	 */
	public static boolean horarioVerano(Locale locale) {
		if (locale != null) {
			Calendar todayCal = Calendar.getInstance(locale);
			Properties prop = null;
			try {
				prop =  new PropertiesLoader().getSettings();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (prop != null) {
				String firstDayOfSummer = prop.getProperty(Constants.CONFIG_KEY_FIRST_DAY_SUMMER);
				String lastDayOfSummer = prop.getProperty(Constants.CONFIG_KEY_LAST_DAY_SUMMER);
				String pattern = prop.getProperty(Constants.CONFIG_KEY_DATE_FORMAT_PATTERN);
				try {
					SimpleDateFormat f = new SimpleDateFormat(pattern);
					//NOTE: if we contruct as dd/MM and let SDF to parse,
					//the Date will not contain the year part,
					//thus it is set in year 1970.
					//so, we must copy the year of today to get a correct comparasion.
					Date first = f.parse(firstDayOfSummer);
					Date last = f.parse(lastDayOfSummer);
					
					Calendar firstCal = new GregorianCalendar(locale);
					//get the day and month part, year is 1970
					firstCal.setTime(first);
					//set the year field of firstCal to be current year
					firstCal.set(Calendar.YEAR,todayCal.get(Calendar.YEAR));
					
					Calendar lastCal = Calendar.getInstance(locale);
					//get the day and month part, year is 1970
					lastCal.setTime(last);
					//set the year field of lastCal to be current year.
					lastCal.set(Calendar.YEAR,todayCal.get(Calendar.YEAR));
					//I think summer begins at the first day, this day inclusive, while
					//the last day is exclusive. Actually it may be called the 
					//first day of winter.
					if (todayCal.compareTo(firstCal)>=0 && todayCal.before(lastCal)) {
						return true;
					} else {
						return false;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	
	/** Method to convert minutes into hours and minutes, in "xx:xx" form.
	 * if is negative, "-xx:xx".
	 * 
	 * @param mins the value of minutes to convert.
	 * @return "(-)xx:xx" form representation of time.
	 */
	public static String convertMinsToHours(int minutes) {
		int mins = (minutes) % 60;
		int hora = (int) (minutes - mins) / 60;
		
		//Si el restado es menos 0, hay que mirar el valor absoluto de los minutos,
		//para evitar -xx:-xx. la hora ya tiene "-".
		//
		//si la parte de minutos es 0, hay que agregar otro 0 delante.
		String minPart = "";
		String hourPart = "";
		if (minutes > 0){
			minPart = ((mins < 10) ? "0" : "") + String.valueOf(mins);
			hourPart = String.valueOf(hora);
		} else {
			//la hora puede ser 0 o -x. 
			hourPart += "-";
			hourPart += String.valueOf(Math.abs(hora));
			minPart = ((Math.abs(mins) < 10) ? "0" : "") + String.valueOf(Math.abs(mins));
			
		}
		
		return hourPart + ":" + minPart;
		
	}
}
