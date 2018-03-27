package com.arloid.alarmcall;

import com.google.common.io.Files;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(MockitoJUnitRunner.class)
public class AlarmCallApplicationTests {

	@Test
	public void contextLoads() {
        System.out.println(Files.getFileExtension("asd.xml"));
	}

}
