package com.util;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Value;


public class FormatTestDataToArray {
	//public TestDemo testDemo[]=new TestDemo[2];
	public FormatTestDataToArray() {
		// TODO Auto-generated constructor stub
	}
	public static TestDemo[] GengeratedArray(String name,double price,String priC){
		TestDemo testDemo[]=new TestDemo[2];
		for(int i=0;i<2;i++){
			testDemo[i]=new TestDemo();
		}
		//System.out.println(priC);
		testDemo[0].setPriClassification(priC);
		testDemo[1].setPriClassification(priC);
		testDemo[0].setName(name+"-��װ");
		testDemo[1].setName(name+"-ɢ��");
		testDemo[0].setSecClassification(name);
		testDemo[1].setSecClassification(name);
		testDemo[0].setPrice(price);
		testDemo[1].setPrice(price/5);
		testDemo[0].setPackMethod("��װ");
		testDemo[1].setPackMethod("ɢ��");
		return testDemo;	
	}
	/**
	 * 分解记录，并封装进单个零食对象
	 * @param tempString
	 * @return
	 */
	/*
	public static Snacks getSnacksForm(String tempString){
		  String []temp=tempString.split(",");
		  if(temp.length==4)
		  System.out.println(temp[0]);
		  Snacks snacks=new Snacks();
		  snacks.setName(temp[0]);
		  snacks.setPriClassification(temp[1]);
		  snacks.setSecClassification(temp[2]);
		  snacks.setPackMethod(temp[3]);
		  snacks.setPrice(Double.parseDouble(temp[4]));
		  return snacks;
	 }
	public static void getSnacksFormArray(String tempAllString,List list){
		String []temp=tempAllString.split("/n"); 
		for(int i= 0;i<temp.length;i++){
			list.add(getSnacksForm(temp[i]));;
		}	
	}
	public static void FormatOut(TestDemo testDemos[],int ObjectSize){
		for(int i=0;i<ObjectSize;i++)
		System.out.println(testDemos[i].getName()+","+testDemos[i].getPriClassification()+","+testDemos[i].getSecClassification()+","+testDemos[i].getPackMethod()+","+testDemos[i].getPrice());
	}
	public static void  main(String args[]) {
		String string[]=new String[]{"夏威夷果-袋装,坚果炒货,夏威夷果,袋装,25"
				,"夏威夷果-散称,坚果炒货,夏威夷果,散称,5"
				,"松子-袋装,坚果炒货,松子,袋装,30"
				,"松子-散称,坚果炒货,松子,散称,6"
				,"开心果-袋装,坚果炒货,开心果,袋装,10"
				,"开心果-散称,坚果炒货,开心果,散称,2"
				,"腰果-袋装,坚果炒货,腰果,袋装,15"
				,"腰果-散称,坚果炒货,腰果,散称,2.5"
				,"松子-袋装,坚果炒货,松子,袋装,15"
				,"松子-散称,坚果炒货,松子,散称,20"
				,"花生-袋装,坚果炒货,花生,袋装,15"
				,"花生-散称,坚果炒货,花生,散称,2.5"
				,"牛肉干-袋装,肉类即食,牛肉干,袋装,30"
				,"牛肉干-散称,肉类即食,牛肉干,散称,40",
				"猪肉脯-袋装,肉类即食,猪肉脯,袋装,20",
				"猪肉脯-散称,肉类即食,猪肉脯,散称,30",
				"牛板筋-袋装,肉类即食,牛板筋,袋装,23.0",
				"牛板筋-散称,肉类即食,牛板筋,散称,4.6",
				"鸡肉-袋装,肉类即食,鸡肉,袋装,20.0",
				"鸡肉-散称,肉类即食,鸡肉,散称,4.0",
				"巧克力-袋装,进口食品,巧克力,袋装,50.0",
				"巧克力-散称,进口食品,巧克力,散称,10.0",
				"果冻-袋装,进口食品,果冻,袋装,35.0",
				"果冻-散称,进口食品,果冻,散称,7.0",
				"咖啡糖-袋装,进口食品,咖啡糖,袋装,25.0",
				"咖啡糖-散称,进口食品,咖啡糖,散称,5.0",
				"牛轧糖-袋装,进口食品,牛轧糖,袋装,28.0",
				"牛轧糖-散称,进口食品,牛轧糖,散称,5.6",
				"奶片-袋装,进口食品,奶片,袋装,29.0",
				"奶片-散称,进口食品,奶片,散称,5.8",
				"海苔-袋装,进口食品,海苔,袋装,30.0",
				"海苔-散称,进口食品,海苔,散称,6.0",
				"口香糖-袋装,进口食品,口香糖,袋装,32.0",
				"口香糖-散称,进口食品,口香糖,散称,6.4",
				"软糖-袋装,进口食品,软糖,袋装,33.0",
				"软糖-散称,进口食品,软糖,散称,6.6",
				"红枣-袋装,梅/果干,红枣,袋装,29.0",
				"红枣-散称,梅/果干,红枣,散称,5.8",
				"梅类-袋装,梅/果干,梅类,袋装,29.0",
				"梅类-散称,梅/果干,梅类,散称,5.8",
				"蜜饯-袋装,梅/果干,蜜饯,袋装,29.0",
				"蜜饯-散称,梅/果干,蜜饯,散称,5.8",
				"果脯-袋装,梅/果干,果脯,袋装,29.0",
				"果脯-散称,梅/果干,果脯,散称,5.8",
				"榴莲干-袋装,梅/果干,榴莲干,袋装,29.0",
				"榴莲干-散称,梅/果干,榴莲干,散称,5.8",
				"核桃仁-袋装,核桃,核桃仁,袋装,29.0",
				"核桃仁-散称,核桃,核桃仁,散称,5.8",
				"山核桃-袋装,核桃,山核桃,袋装,29.0",
				"山核桃-散称,核桃,山核桃,散称,5.8",
				"长寿果-袋装,核桃,长寿果,袋装,29.0",
				"长寿果-散称,核桃,长寿果,散称,5.8"};
		List<Snacks> list=new ArrayList<>();
		for(int i=0;i<string.length;i++)
		list.add(FormatTestDataToArray.getSnacksForm(string[i]));
		
		System.out.println(list.get(1).getPrice());
	}*/
}
