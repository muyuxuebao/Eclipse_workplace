package com.yinliang.DP.Demo15_Observer;

public interface Subject {
	/*���ӹ۲���*/  
    public void add(Observer observer);  
      
    /*ɾ���۲���*/  
    public void del(Observer observer);  
      
    /*֪ͨ���еĹ۲���*/  
    public void notifyObservers();  
      
    /*�����Ĳ���*/  
    public void operation();  
}