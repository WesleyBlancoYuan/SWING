package com.WindThunderStudio.logic;

import java.util.Locale;

import com.WindThunderStudio.util.Tool;

public class Calculator {
	
	private String[] horas1;
	
	
	private String[] minutos1;
	
	
	private String[] horas2;
	
	
	private String[] minutos2;

	
	public String[] getHoras1() {
		return horas1;
	}

	/**
	 * Entry time hour part, of 7 days.
	 */
	public void setHoras1(String[] horas1) {
		this.horas1 = horas1;
	}


	public String[] getMinutos1() {
		return minutos1;
	}

	/**
	 * Entry time minutes part, of 7 days.
	 */
	public void setMinutos1(String[] minutos1) {
		this.minutos1 = minutos1;
	}


	public String[] getHoras2() {
		return horas2;
	}

	/**
	 * Leaving time hour part, of 7 days.
	 */
	public void setHoras2(String[] horas2) {
		this.horas2 = horas2;
	}


	public String[] getMinutos2() {
		return minutos2;
	}

	/**
	 * Leaving time minutes part, of 7 days.
	 */
	public void setMinutos2(String[] minutos2) {
		this.minutos2 = minutos2;
	}

	/**
	 * Method to calculate working time of seven days of this week between the 
	 * leaving time and the entry time. The result is in minutes.
	 * If the working hours surpasses 8 hours a day, in summer should minus half an hour.
	 * In winter, an hour.
	 * @return an Array of 7 elements containing the working time measured by minutes.
	 */
	public int[] calHorasImputadas(Locale locale) {
		int horas1Int = 0;
		int mins1Int = 0;
		int horas2Int = 0;
		int mins2Int = 0;
		
		int[] result = new int[7];
		
		for (int i=0; i<7; i++){
			if (horas1[i] != null && !horas1[i].isEmpty()){
				horas1Int = Integer.parseInt(horas1[i]);
			} else {
				horas1Int = 0;
			}
			if (minutos1[i] != null && !minutos1[i].isEmpty()){
				mins1Int = Integer.parseInt(minutos1[i]);
			} else {
				mins1Int = 0;
			}
			if (horas2[i] != null && !horas2[i].isEmpty()){
				horas2Int = Integer.parseInt(horas2[i]);
			} else {
				horas2Int = 0;
			}
			if (minutos2[i] != null && !minutos2[i].isEmpty()){
				mins2Int = Integer.parseInt(minutos2[i]);
			} else {
				mins2Int = 0;
			}
			//validar si la hora de salida ha sido mas tarde que lo debido o no.
			
			if (i==0 || i==2) {
				//lunes y miercoles: mas tarde: 1600
				if ((horas2Int == 16 && mins2Int > 0) || horas2Int > 16) {
					horas2Int = 16;
					mins2Int = 0;
				}
			} else if (i == 1 || i == 3) {
				//martes y jueves: mas tardes: 1930
				if ((horas2Int == 19 && mins2Int > 30) || (horas2Int > 19)) {
					horas2Int = 19;
					mins2Int = 30;
				}
			} else if (i == 4) {
				//viernes: mas tarde: 1530
				if ((horas2Int == 15 && mins2Int > 30) || (horas2Int > 15)) {
					horas2Int = 15;
					mins2Int = 30;
				}
			}
			
			
			int minsTotal = horas2Int * 60 + mins2Int - (horas1Int * 60 + mins1Int);
			//si estamos en verano, las 8 horas enseguidas no se cuenta. Se quita 30 minutos.
			//si estamos en invierno, se quita una hora.
			if (minsTotal > (8 * 60)) {
				if (Tool.horarioVerano(locale)) {
					minsTotal -= 30;
				} else {
					minsTotal -= 60;
				}
			}
			result[i] = minsTotal;
			
		}
		
		return result; 
	}
	
}
