package com.lu.other;

/**
 * 
 * @author lu
 * 
 * @description 静态内部类式单例
 * 
 */
public class Singleton {

	private Singleton() {
	}
	
	private static class SingletonHolder {
		private final static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
	
}
