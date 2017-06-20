package com.chinasoft.shop.car.service.imlp;

import java.util.ArrayList;
import java.util.List;

import com.chinasoft.shop.car.dao.impl.CarDaoImpl;
import com.chinasoft.shop.car.domain.Car;
import com.chinasoft.shop.car.domain.SpareCar;
import com.chinasoft.shop.car.service.CarService;
import com.chinasoft.shop.goods.dao.impl.GoodsDaoImpl;
import com.chinasoft.shop.goods.domain.Goods;

public class CarServiceImpl implements CarService{

	CarDaoImpl cd=new CarDaoImpl();
	GoodsDaoImpl gd=new GoodsDaoImpl();
	@Override
	public void add(Car car) {
		cd.add(car);
		
	}
	@Override
	public List<SpareCar> findAllByUid(String uid) {
		
		List<SpareCar> scarList=new ArrayList<SpareCar>();
		List<Car> carList=cd.findAllByUid(uid);
		for (Car car : carList) {
			String id=car.getId();
			//String uid=car.getUid();
			int quantity=car.getQuantity();
			double subtotal=car.getSubtotal();
			Goods goods=gd.findById(car.getGid());
			
			SpareCar scar=new SpareCar();
			scar.setId(id);
			scar.setQuantity(quantity);
			scar.setSubtotal(subtotal);
			scar.setGoods(goods);
			
			scarList.add(scar);
			
		}
		
	return	scarList;
		
	}
	@Override
	public double calculateTotalPay(List<SpareCar> scarList) {
		double totalPay=0;
		for (SpareCar spareCar : scarList) {
			totalPay+=spareCar.getSubtotal();
		}
		return totalPay;
	}

}
