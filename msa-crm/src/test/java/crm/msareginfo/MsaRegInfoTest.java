package crm.msareginfo;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.eshequ.msa.crm.model.msareginfo.MsaRegInfo;
import com.eshequ.msa.util.SnowFlake;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.eshequ.msa.crm.config.AppConfig.class)
@WebAppConfiguration
public class MsaRegInfoTest {

	@Autowired
	private SnowFlake snowFlake;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ClientHttpRequestFactory factory;

	@Test
	public void addMsaInfo() {
		MsaRegInfo m = new MsaRegInfo();
		m.setEmail("1500732323@163.com");
		m.setEnterpriseName("奈博科技");
		m.setLoginName("bobcjx");
		m.setOrgAddr("三林路");
		m.setLinkMan("张三");
		m.setOrgTel("15007336127");
		m.setPostCode("20021");
		m.setProductVersion("1");
		m.setRandomCode("1233");
		m.setRemark("正常");
		String url = "http://localhost:9090/crm/addMsaInfo";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, m, String.class);
		System.out.println("添加成功！");
	}

}
