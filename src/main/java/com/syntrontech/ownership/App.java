package com.syntrontech.ownership;

import java.sql.SQLException;
import java.util.List;

public class App {
	public static void main(String[] args) throws SQLException{

		List<StringBuilder> values = new CIPOwnershipJDBC().getAll();

		values.forEach(v -> new FriendOwnershipJDBC().insert(v));

		System.out.println("HI~");
	}
}
