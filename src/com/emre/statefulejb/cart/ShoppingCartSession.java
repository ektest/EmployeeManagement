package com.emre.statefulejb.cart;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ShoppingCartSession {

	public abstract void addItem(String item);

	public abstract List<String> getAllItems();

	public abstract void checkout();
}