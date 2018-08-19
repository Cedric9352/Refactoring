package com.inspur.chengyujia.refactoring;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 电影类
 * @author cedric
 * created on 2018年8月15日
 * description: 描述电影的基本类
 * @author cedric
 * modified on 2018年8月19日
 * description: 将getCharge与getFrequentRenderPoints方法放在Movie类中，表现为根据Movie类型变化的“动作”
 */
public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	
	private String _title;
	private int _priceCode;
	
	public Movie(String title, int priceCode) {
		_title = title;
		_priceCode = priceCode;
	}
	public int getPriceCode() {
		return _priceCode;
	}
	public void setPriceCode(int priceCode) {
		_priceCode = priceCode;
	}
	public String getTitle() {
		return _title;
	}
	public double getCharge(int daysRented) {
		double result = 0;
		switch(getPriceCode()) {
		case Movie.REGULAR:
			result += 2;
			if(daysRented > 2) {
				result += (daysRented - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			result += daysRented * 3;
			break;
		case Movie.CHILDRENS:
			result += 1.5;
			if(daysRented > 3) {
				result += (daysRented - 3) * 1.5;
			}
			break;
		}
		return result;
	}
	public int getFrequentRenderPoints(int daysRented) {
		if((getPriceCode() == Movie.NEW_RELEASE) &&
				 daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}
/**
 * 租赁类
 * @author cedric
 * created on 2018年8月15日
 * description: 描述租赁的基本类
 * @author cedric
 * modified on 2018年8月15日
 * description: 将Customer类的代码抽取出来后作为方法放入本类
 * @author cedric
 * modified on 2018年8月17日
 * description: 将Customer的积分计算代码抽取出来形成getFrequentRenderPoints方法
 * @author cedric
 * modified on 2018年8月19日
 * description: 将随Movie类变化的方法放入Movie类中
 */
class Rental {
	private Movie _movie;
	private int _daysRented;
	
	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}
	public int getDaysRented() {
		return _daysRented;
	}
	public Movie getMovie() {
		return _movie;
	}
	public double getCharge() {
		return _movie.getCharge(_daysRented);
	}
	public int getFrequentRenterPoints() {
		return _movie.getFrequentRenderPoints(_daysRented);
	}
}
/**
 * 顾客类
 * @author cedric
 * created on 2018年8月15日
 * description: 描述顾客的基本类
 * @author cedric
 * modified on 2018年8月15日
 * description: 将switch-case抽取出作为Rental类的方法，并替换了本类的临时变量
 * @author cedric
 * modified on 2018年8月17日
 * description: 将积分计算代码抽取出来作为Rental类的方法，并将局部变量frequentRentalPoints和totalAmount替换为查询方法
 */
class Customer {
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