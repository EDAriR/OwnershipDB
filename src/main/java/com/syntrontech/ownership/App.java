package com.syntrontech.ownership;

import java.sql.SQLException;

public class App {
	public static void main(String[] args) throws SQLException{
		
		new UnitMetaJDBC().getUnitMetaById();
	
		System.out.println("Hello world");
	}

}
