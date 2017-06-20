package junit;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;

public class TestDao {

	/**淇濆瓨*/
	@Test
	public void save(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextName("娴嬭瘯Dao鍚嶇О");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("娴嬭瘯Dao澶囨敞");
		elecTextDao.save(elecText);
	}
	
	/**鏇存柊*/
	@Test
	public void update(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextID("402881e442599916014259991c450001");
		elecText.setTextName("鏇存柊鍚嶇О");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("鏇存柊澶囨敞");
		elecTextDao.update(elecText);
	}
	
	/**浣跨敤涓婚敭ID鏌ヨ瀵硅薄*/
	@Test
	public void findObjectById(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		Serializable id = "402881e4425a729301425a734c2a0001";
		ElecText elecText = elecTextDao.findObjectByID(id);
		System.out.println(elecText.getTextName()+"    "+elecText.getTextDate()+"    "+elecText.getTextRemark());
	}
	
	/**鍒犻櫎锛堜娇鐢�涓富閿甀D鍜屽涓富閿甀D鐨勬暟缁勶級*/
	@Test
	public void deleteObjectByIDs(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		//Serializable [] ids = {"402881e442599916014259991c450001","402881e44259d1c0014259d1c48b0001"};
		Serializable ids = "402881e44259de3a014259de40e40001"; 
		elecTextDao.deleteBojectByIDs(ids);
	}
	
	/**鍒犻櫎锛堝皢瀵硅薄灏佽鎴愰泦鍚堬紝浣跨敤闆嗗悎鍒犻櫎闆嗗悎涓瓨鏀剧殑鎵�湁瀵硅薄锛�*/
	@Test
	public void deleteObjectByCollection(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText elecText1 = new ElecText();
		elecText1.setTextID("402881e44259dfc5014259dfcaa10001");
		ElecText elecText2 = new ElecText();
		elecText2.setTextID("402881e44259e338014259e36de70001");
		list.add(elecText1);
		list.add(elecText2);
		elecTextDao.deleteObjectByCollection(list);
	}
}
