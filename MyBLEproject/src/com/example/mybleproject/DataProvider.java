package com.example.mybleproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProvider {
	public static HashMap<String,List<String>> getInfo()
	{
		HashMap<String, List<String>> shopDetails = new HashMap<String, List<String>>();
		List<String> children_clothes = new ArrayList<String>();
		children_clothes.add("scoop");
		children_clothes.add("Haddad Baby Fation");
		List<String> Women_clothes = new ArrayList<String>();
		Women_clothes.add("Gate 7");
		Women_clothes.add("Design Fation");
		Women_clothes.add("Oxegyn Fation");
		Women_clothes.add("Vatrine Fation");
		Women_clothes.add("Farfasha Fation");
		List<String> Men_clothes= new ArrayList<String>();
		Men_clothes.add("Al Tahle");
		Men_clothes.add("Hajjo");
		List<String> mobiles_computers = new ArrayList<String>();
		mobiles_computers.add("Gogo phone");
		mobiles_computers.add("Decran");
		mobiles_computers.add("Al Brj");
		List<String> cafe = new ArrayList<String>();
		cafe.add("Gusto");
		cafe.add("New Moon");
		cafe.add("Lucky");
		cafe.add("cello");
		shopDetails.put("children clothes",children_clothes);
		shopDetails.put("Women clothes",Women_clothes);
		shopDetails.put("Men clothes",Men_clothes);
		shopDetails.put("mobiles&Labtops",mobiles_computers);
		shopDetails.put("cafe", cafe);
		return shopDetails;
		
	}

}
