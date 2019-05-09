package crm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.eshequ.msa.crm.vo.para.ParaVo;
import com.eshequ.msa.util.SnowFlake;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.eshequ.msa.crm.config.AppConfig.class)
@WebAppConfiguration
public class SysParaTest {

	@Autowired
	private SnowFlake snowFlake;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void testSysParaEdit(){
		ParaVo vo = new ParaVo();
		vo.setParaId(Long.valueOf("7938576457273344"));
		vo.setParaName("activityNumber333");
		
		String url = "http://localhost:9090/crm/paraEdit";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vo, String.class);
		System.out.println("编辑成功！");
	}
	
	@Test
	public void testSysParaAdd(){
		ParaVo vo = new ParaVo();
		vo.setParaName("activityNumber");
		vo.setParaValue("1");
		vo.setParaType("1");
		vo.setRemark("小区活跃度指数（收款操作（N次）=1次活跃）");
		String url = "http://localhost:9090/crm/paraAdd";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vo, String.class);
		System.out.println("添加成功！");
	}
}
