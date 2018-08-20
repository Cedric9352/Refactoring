package com.inspur.chengyujia.refactoring;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客类<br/>
 * created on 2018年8月15日<br/>
 * description: 描述顾客的基本类<br/>
 * author: cedric<br/>
 * modified on 2018年8月15日<br/>
 * description: 将switch-case抽取出作为Rental类的方法，并替换了本类的临时变量<br/>
 * author: cedric<br/>
 * modified on 2018年8月17日<br/>
 * description: 将积分计算代码抽取出来作为Rental类的方法，并将局部变量frequentRentalPoints和totalAmount替换为查询方法<br/>
 * author: cedric<br/>
 */
public class Customer {
	private String _name;
	private Vector<Rental> _rentals = new Vector<>();
	
	public Customer(String name) {
		_name = name;
	}
	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}
	public String getName() {
		return _name;
	}
	public String statement() {
		Enumeration<Rental> rentals = _rentals.elements();	// Vector的迭代器
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		while(rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result.append("\t").append(each.getMovie().getTitle()).append("\t").append(String.valueOf(each.getCharge())).append("\n");
		}
		result.append("Amount owed is ").append(String.valueOf(getTotalCharge())).append("\n");
		result.append("You earned ").append(String.valueOf(getTotalFrequentRenterPoints())).append(" frequent renter points");
		return result.toString();
	}
	private double getTotalCharge() {
		double result = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		while(rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}
	private int getTotalFrequentRenterPoints() {
		int result = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		while(rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}
}
