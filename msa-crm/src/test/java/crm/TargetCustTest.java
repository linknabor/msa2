package crm;

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

import com.eshequ.msa.crm.model.targetcust.CrmMarketingCust;
import com.eshequ.msa.crm.vo.targetcust.TargetCustVo;
import com.eshequ.msa.util.SnowFlake;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.eshequ.msa.crm.config.AppConfig.class)
@WebAppConfiguration
public class TargetCustTest {

	@Autowired
	private SnowFlake snowFlake;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ClientHttpRequestFactory factory;
	
	
	@Test
	public void testTargetCustAdd(){
		TargetCustVo vo = new TargetCustVo();
		vo.setCityId(snowFlake.nextId());
		vo.setCustAddr("新华路111号");
		vo.setCustName("新华里");
		vo.setIndustryId(snowFlake.nextId());
		vo.setProvinceId(snowFlake.nextId());
		vo.setRegionId(snowFlake.nextId());
		vo.setHouCount(new BigDecimal("888"));
		vo.setYearAmt(new BigDecimal("222.33"));
		vo.setQualificationLevel("2");
		vo.setSaleStatus("02");
		vo.setGroupCom("新华集团");
		vo.setSysOperid(snowFlake.nextId());
		vo.setRemark("无");
		String url = "http://localhost:9090/crm/targetcustAdd";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vo, String.class);
		System.out.println("添加成功！");
	}
	
	@Test
	public void testTargetCustQuery(){
		TargetCustVo vo = new TargetCustVo();
//		vo.setCustId(snowFlake.nextId());
//		vo.setCityId(snowFlake.nextId());
//		vo.setCustAddr("三林路158号1401室");
//		vo.setCustName("客户甲");
		vo.setMinHouCount(new BigDecimal("1001"));
		vo.setMaxHouCount(new BigDecimal("9998"));
//		vo.setIndustryId(snowFlake.nextId());
//		vo.setProvinceId(snowFlake.nextId());
//		vo.setRegionId(snowFlake.nextId());
//		vo.setHouCount(new BigDecimal("1000"));
//		vo.setYearAmt(new BigDecimal("10000.99"));
//		vo.setQualificationLevel("1");
//		vo.setSaleStatus("02");
//		vo.setGroupCom("农工商集团");
//		vo.setSysOperid(snowFlake.nextId());
//		vo.setRemark("无");
		String url = "http://localhost:9090/crm/targetcustQuery";
		restTemplate.postForEntity(url, vo, String.class);
		System.out.println("success");
	}
	
	@Test
	public void testTargetCustEdit(){
		TargetCustVo vo = new TargetCustVo();
		vo.setCustId(Long.valueOf("5396912157102080"));
		vo.setCityId(snowFlake.nextId());
		vo.setCustAddr("新华路2111号");
		vo.setCustName("新华里");
		vo.setIndustryId(snowFlake.nextId());
		vo.setProvinceId(snowFlake.nextId());
		vo.setRegionId(snowFlake.nextId());
		vo.setHouCount(new BigDecimal("888"));
		vo.setYearAmt(new BigDecimal("222.33"));
		vo.setQualificationLevel("1");
		vo.setSaleStatus("02");
		vo.setGroupCom("新华集团");
		vo.setSysOperid(snowFlake.nextId());
		vo.setRemark("备注2");
		String url = "http://localhost:9090/crm/targetcustEdit";
		restTemplate.postForEntity(url, vo, String.class);
		System.out.println("success");
	}
	
	@Test
	public void testTargetCustDel(){
		String url = "http://localhost:9090/crm/targetcustDel";
		TargetCustVo vo = new TargetCustVo();
		vo.setCustId(Long.valueOf("5089867834986496"));
		restTemplate.postForEntity(url, vo, String.class);
		System.out.println("success");
	}
	
	@Test
	public void testTargetCustDelById(){
		String url = "http://localhost:9090/crm/targetcustDelById/5179307483860992";
		restTemplate.postForEntity(url, null, String.class);
		System.out.println("success");
	}
	
	@Test
	public void testTargetCustByExample() throws IOException{
		TargetCustVo vo = new TargetCustVo();
		vo.setCustName("甲");
		String url = "http://localhost:9090/crm/targetcustQueryByExample";
		ResponseEntity<String> response = restTemplate.postForEntity(url, vo, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		String requestJsonStr = mapper.writeValueAsString(response);	//map 转json
		mapper.readValue(requestJsonStr, CrmMarketingCust.class);
		System.out.println(requestJsonStr);
	}
	
	@Test
	public void testTargetCustByAll(){
		String url = "http://localhost:9090/crm/targetcustQueryAll";
		ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
	}
}
