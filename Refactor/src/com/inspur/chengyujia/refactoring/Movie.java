package com.inspur.chengyujia.refactoring;

/**
 * 价格抽象类<br/>
 * created on 2018年8月20日<br/>
 * description: 描述价格策略<br/>
 * author: cedric<br/>
 * modified on 2018年8月20日<br/>
 * description: 增加了抽象方法getCharge<br/>
 * author: cedric<br/>
 * modified on 2018年8月21日<br/>
 * description: 增加了公共方法getFrequentRenderPoints<br/>
 * author: cedric<br/>
 */
abstract class Price {
	protected abstract int getPriceCode();
	protected abstract double getCharge(int daysRented);
	protected int getFrequentRenderPoints(int daysRented) {
		if((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}
/**
 * 儿童类价格策略<br/>
 * created on 2018年8月20日<br/>
 * description: 描述儿童电影<br/>
 * author: cedric<br/>
 * modified on 2018年8月20日<br/>
 * description: 重写了getCharge方法，策略模式<br/>
 * author: cedric<br/>
 * modified on 2018年8月21日<br/>
 * description: 重写了getFrequentRenderPoints方法<br/>
 * author: cedric<br/>
 */
class ChildrenPrice extends Price {
	@Override
	protected int getPriceCode() {
		return Movie.CHILDREN;
	}
	@Override
	protected double getCharge(int daysRented) {
		double result = 2;
		if(daysRented > 2) {
			result += (daysRented - 2) * 1.5;
		}
		return result;
	}
	@Override
	protected int getFrequentRenderPoints(int daysRented) {
		return (daysRented > 1) ? 2 : 1;
	}
}
/**
 * 新类策略<br/>
 * created on 2018年8月20日<br/>
 * description: 描述新电影<br/>
 * author: cedric<br/>
 * modified on 2018年8月20日<br/>
 * description: 重写了getCharge方法，策略模式<br/>
 * author: cedric<br/>
 */
class NewReleasePrice extends Price {
	@Override
	protected int getPriceCode() {
		return Movie.NEW_RELEASE;
	}
	@Override
	protected double getCharge(int daysRented) {
		return daysRented * 3;
	}
}
/**
 * 普通类策略<br/>
 * created on 2018年8月20日<br/>
 * description: 描述普通电影<br/>
 * author: cedric<br/>
 * modified on 2018年8月20日<br/>
 * description: 重写了getCharge方法，策略模式<br/>
 * author: cedric<br/>
 * modified on 2018年8月21日<br/>
 * description: 重写了getFrequentRenderPoints方法<br/>
 * author: cedric<br/>
 */
class RegularPrice extends Price {
	@Override
	protected int getPriceCode() {
		return Movie.REGULAR;
	}
	@Override
	protected double getCharge(int daysRented) {
		double result = 1.5;
		if(daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}
		return result;
	}
	@Override
	protected int getFrequentRenderPoints(int daysRented) {
		return 1;
	}
}
/**
 * 电影类<br/>
 * created on 2018年8月15日<br/>
 * description: 描述电影的基本类<br/>
 * author: cedric<br/>
 * modified on 2018年8月19日<br/>
 * description: 将getCharge与getFrequentRenderPoints方法放在Movie类中，表现为根据Movie类型变化的“动作”<br/>
 * author: cedric<br/>
 * modified on 2018年8月20日<br/>
 * description: 增加了_price策略代理对象，将getPriceCode和getCharge方法转发给_price对象<br/>
 * author: cedric<br/>
 */
public class Movie {
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	
	private String _title;
	private Price _price;
	
	public Movie(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode);
	}
	public int getPriceCode() {
		return _price.getPriceCode();
	}
	public void setPriceCode(int priceCode) {
		switch(priceCode) {
		case REGULAR:
			_price = new RegularPrice();
			break;
		case NEW_RELEASE:
			_price = new NewReleasePrice();
			break;
		case CHILDREN:
			_price = new ChildrenPrice();
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}
	public String getTitle() {
		return _title;
	}
	double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}
	public int getFrequentRenderPoints(int daysRented) {
		return _price.getFrequentRenderPoints(daysRented);
	}
}