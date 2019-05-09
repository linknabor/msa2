package crm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.eshequ.msa.codes.OrgStatus;
import com.eshequ.msa.crm.vo.org.OrgVo;
import com.eshequ.msa.util.SnowFlake;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.eshequ.msa.crm.config.AppConfig.class)
@WebAppConfiguration
public class OrgInfoTest {

	@Autowired
	private SnowFlake snowFlake;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void testorgAdd(){
		OrgVo vo = new OrgVo();
		vo.setOrgId(snowFlake.nextId());
		vo.setOrgName("奈博科技");
		vo.setOrgAddr("三林路128号1401室");
		vo.setOrgType("02");
		vo.setLinkMan("刘总");
		vo.setLinkPhone("1383838438");
		vo.setOrgStatus(OrgStatus.ZhengChang.toString());
		vo.setLoginName("zzjg");
		vo.setRemark("");
		String url = "http://localhost:9090/crm/orgAdd";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vo, String.class);
		System.out.println("添加成功！");
	}
	
	@Test
	public void testorgEdit(){
		OrgVo vo = new OrgVo();
		vo.setOrgId(Long.valueOf("7274101186105344"));
		vo.setOrgName("奈博科技");
		vo.setOrgAddr("三林路128号1401室");
		vo.setOrgType("02");
		vo.setLinkMan("刘总");
		vo.setLinkPhone("1383838438");
		vo.setOrgStatus(OrgStatus.ZhengChang.toString());
		vo.setLoginName("zzjg");
		vo.setRemark("呵呵");
		String url = "http://localhost:9090/crm/orgEdit";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vo, String.class);
		System.out.println("编辑成功！");
	}
	
	@Test
	public void testOrgDelById(){
		String url = "http://localhost:9090/crm/orgDelById/7212450344734720";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, null, String.class);
		System.out.println("删除成功！");
	}
	
	@Test
	public void testOrgQuery(){
		OrgVo vo = new OrgVo();
		vo.setOrgName("明教");
		String url = "http://localhost:9090/crm/orgQueryByExample";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vo, String.class);
	}
}
