package junit;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;

public class TestService {

	/**淇濆瓨*/
	@Test
	public void save(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextName("娴嬭瘯Service鍚嶇О");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("娴嬭瘯Service澶囨敞");
		elecTextService.save(elecText);
	}
	
	/**妯℃嫙Action灞傦紝璋冪敤Service灞�*/
	@Test
	public void findCollectionByConditionNoPage(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		ElecText elecText = new ElecText();
//		elecText.setTextName("寮�);
//		elecText.setTextRemark("寮�);
		List<ElecText> list = elecTextService.findCollectionByConditionNoPage(elecText);
		if(list!=null && list.size()>0){
			for(ElecText text:list){
				System.out.println(text.getTextName()+"    "+text.getTextDate()+"    "+text.getTextRemark());
			}
		}
		
	}
}
