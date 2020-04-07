package com.testutility;

import java.util.ArrayList;

import com.excel.utility.Xls_Reader;

public class testutil {
	static Xls_Reader reader;
	public static ArrayList<Object[]>  getdatafromexcel(){
		ArrayList<Object[]> mydata  = new ArrayList<Object[]>();
		try {
		 reader = new Xls_Reader("C:\\Users\\om sai ram\\eclipse-workspace\\Gmail\\src\\com\\testdata\\gmail.xlsx");
		}
		 catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		 }
		int rowcount = reader.getRowCount("Regdata");
		
		for (int rownum = 2; rownum <= rowcount; rownum++) {
			String firstname = reader.getCellData("Regdata", "firstname", rownum);
			System.out.println(firstname);
			String lastname = reader.getCellData("Regdata", "lastname", rownum);
			System.out.println(lastname);
			String gmailaddress = reader.getCellData("Regdata", "gmailaddress", rownum);
			System.out.println(gmailaddress);
			String password = reader.getCellData("Regdata", "password", rownum);
			System.out.println(password);
			String confirmpassword = reader.getCellData("Regdata", "confirmpassword", rownum);
			System.out.println(confirmpassword);
			
			Object ob[] = {firstname, lastname, gmailaddress, password, confirmpassword};
			mydata.add(ob);
		}
		return mydata;
	}

}
