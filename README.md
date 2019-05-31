# msa2  

开发注意事项：  
1.pom文件中添加<dependency></dependency>时，不要写入确切版本号，用占位符代替，例如：${springboot.version}。具体版本在<properties></properties>标签下定义。  
2.用mabatis反向工程工具生成实体和mapper的，请修改resources文件下的generatorConfig.xml。然后在Util包下找到MybatisGeneratorUtill这个类，执行main函数。  
3.Mybatis反向工程由于generatorConfig.xml文件设置了属性modelOnly，不会自动生成SQL及其对应的mapper，请自行创建该实体对应的Mapper文件并继承com.eshequ.msa.common.CommonMapper。  
  如果需要使用selectByPrimaryKey、updateByPrimaryKey的请在自动生成的实体上注入@ID，不然识别不到主键。  
4.单元测试请在src/test/java目录下写测试用例。  
5.使用maven编译时可以用公司的maven仓库，速度会比较快。仓库地址:http://svn-service.chinacloudapp.cn:8081/nexus/content/groups/public/  
6.判断非空一律使用OjbectUtil.isEmpty();  
7.时间相关函数调用DateUtil工具包  
8.对象与Json互换请使用ObjectMapper。ObjectMapper不要自己new，注入即可。线程安全。使用方法：  
  Someobj somObj = objectMapper.readValue(respJson, Someobj.class);	//json 转对象  
  String requestJsonStr = mapper.writeValueAsString(map);	// 对象转json  
9.基本http调用请使用restTemplate,post一个map的时候需要使用LinkedMultiValueMap。直接post一个hashmap或者treemap，postentity将不会组装其中的参数。用法如下：  
    Map<String, String> map = new TreeMap<>();  
    map.put...  
    ....  
	LinkedMultiValueMap<String, String> multiMap = new LinkedMultiValueMap<>();  
	multiMap.setAll(map);  
	String resp = restTemplate.postForObject(reqUrl, multiMap, String.class);  
10.业务处理类放在service包下  
