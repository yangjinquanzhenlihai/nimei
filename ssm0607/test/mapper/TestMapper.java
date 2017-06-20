package mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinasoft.ssm.mapper.UserMapper;

public class TestMapper {
	@Test
	public void testMapper() throws Exception {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		UserMapper mapper = (UserMapper)ac.getBean("userMapper");
		System.out.println(mapper);
	}
}
