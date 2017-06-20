package com.chinasoft.shop.car.service;

import java.util.List;

import com.chinasoft.shop.car.domain.Car;
import com.chinasoft.shop.car.domain.SpareCar;

public interface CarService {

	void add(Car car);

	List<SpareCar> findAllByUid(String uid);

	double calculateTotalPay(List<SpareCar> scarList);

}
