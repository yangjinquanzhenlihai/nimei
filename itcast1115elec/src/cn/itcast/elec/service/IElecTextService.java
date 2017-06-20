package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.domain.ElecText;

public interface IElecTextService {

	public static final String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecTextServiceImpl";
	
	public void save(ElecText elecText);

	public List<ElecText> findCollectionByConditionNoPage(ElecText elecText);
}
