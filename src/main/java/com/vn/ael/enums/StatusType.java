package com.vn.ael.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum StatusType {

	NO(0,"no"),
	YES(1,"yes");
	
	private int value;
	private String label;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	private StatusType(int value, String label) {
		this.value = value;
		this.label = label;
	}
	 @Override
	public String toString() {
		 return String.valueOf(this.value);
	}
	 
	 public static Map<Integer, String> getLabelsMap(){
			Map<Integer, String> map = new LinkedHashMap<>();
			for (StatusType servicesType : StatusType.values()){
					map.put(servicesType.getValue(), servicesType.getLabel());
			}
			return map;
		}
		
		public static StatusType fromValue(int value){
			for (StatusType servicesType : StatusType.values()){
				if (servicesType.getValue() == value){
					return servicesType;
				}
			}
			return null;
		}
}
