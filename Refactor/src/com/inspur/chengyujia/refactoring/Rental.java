package com.inspur.chengyujia.refactoring;

/**
 * 租赁类<br/>
 * created on 2018年8月15日<br/>
 * description: 描述租赁的基本类<br/>
 * author: cedric<br/>
 * modified on 2018年8月15日<br/>
 * description: 将Customer类的代码抽取出来后作为方法放入本类<br/>
 * author: cedric<br/>
 * modified on 2018年8月17日<br/>
 * description: 将Customer的积分计算代码抽取出来形成getFrequentRenderPoints方法<br/>
 * author: cedric<br/>
 * modified on 2018年8月19日<br/>
 * description: 将随Movie类变化的方法放入Movie类中<br/>
 * author: cedric<br/>
 */
public class Rental {
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