package com.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommodityClassification {
private String pri = new String();
private Set<String> sec=new HashSet<>();

private List<String> result=new ArrayList<>();
	public CommodityClassification() {
		// TODO Auto-generated constructor stub
	}
	
	public String getPri() {
		return pri;
	}
	public void trans(){
		result.addAll(sec);
	}


	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}



	public Set<String> getSec() {
		return sec;
	}

	public void setSec(Set<String> sec) {
		this.sec = sec;
	}

	public void init(Set<String> pri){
		for(int i=0;i<pri.size();i++){
			
		}
	}

}
