package com.chinasoft.shop.address.service;

import java.util.List;

import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.user.domain.User;

public interface AddressService {

	void add(Address address);

	List findAllByUid(String uid);

	void cancelSelected(String uid);


	void setSelected(String id);

	void deleteAddress(String id);

	Address findAllByid(String id);


	

}
