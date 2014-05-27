package com.emre.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.emre.statefulejb.cart.ShoppingCartSession;

public class StatefulClientTest
{
	public static void main(String [] args)
	{
		try{
			Context jndi = new InitialContext();
			ShoppingCartSession cart = (ShoppingCartSession)jndi.lookup("java:global/EmployeeManagement/ShoppingCartSessionImplementation");
			
			cart.addItem("Tennis Racquet");
			cart.addItem("Toaster");
			cart.addItem("Some books");
			
			List<String> items = cart.getAllItems();
			
			for(String next : items)
			{
				System.out.println(next);
			}
			
			cart.checkout();			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}
}
