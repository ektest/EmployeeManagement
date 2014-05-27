package com.emre.statefulejb.cart;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class ShoppingCartSessionImplementation implements ShoppingCartSession 
{
	private List<String> items;
	
	public ShoppingCartSessionImplementation()
	{
		this.items = new ArrayList<String> ();
	}
	
	public void addItem(String item)
	{
		this.items.add(item);
	}
	
	public List<String> getAllItems()
	{
		return this.items;
	}
	
	@Remove
	public void checkout()
	{
		// add the items to a database
	}
}
