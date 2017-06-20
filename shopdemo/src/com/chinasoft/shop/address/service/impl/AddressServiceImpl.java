package com.chinasoft.shop.address.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.chinasoft.shop.address.dao.impl.AddressDaoImpl;
import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.address.service.AddressService;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.base.dao.BaseDao;

public class AddressServiceImpl implements AddressService {

	AddressDaoImpl addressDao= new AddressDaoImpl();

	@Override
	public void add(Address address) {
		
		if((addressDao.findById(address.getId()))!=null){
			addressDao.delete(address.getId());
		}
			addressDao.add(address);
	}

	@Override
	public List findAllByUid(String uid) {

		List<Address> userAdress=addressDao.findAllByUid(uid);
		return userAdress;
	}

	@Override
	public void cancelSelected(String uid) {//È¡ÏûÄ¬ÈÏ
		addressDao.cancelSelected(uid);
		
	}

	@Override
	public void setSelected(String id) {
		addressDao.setSelected(id);
		
	}

	@Override
	public void deleteAddress(String id) {
		
		addressDao.delete(id);
		
	}

	@Override
	public Address findAllByid(String id) {
		
		
		return addressDao.findById(id);
	}


}
