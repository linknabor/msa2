package com.eshequ.msa.crm.service.repairmng.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.crm.mapper.repairmng.FileMngMapper;
import com.eshequ.msa.crm.mapper.repairmng.RepairAssignMapper;
import com.eshequ.msa.crm.mapper.repairmng.RepairOrderMapper;
import com.eshequ.msa.crm.model.repairmng.FileMng;
import com.eshequ.msa.crm.model.repairmng.RepairAssign;
import com.eshequ.msa.crm.model.repairmng.RepairOrder;
import com.eshequ.msa.crm.model.repairmng.UserInfo;
import com.eshequ.msa.crm.service.repairmng.RepairOrderService;
import com.eshequ.msa.crm.util.FileUtil;
import com.eshequ.msa.crm.util.QiYeWeiXinUtil;
import com.eshequ.msa.crm.vo.repairmng.RepairAndFileVo;
import com.eshequ.msa.util.SnowFlake;

@Service
@Transactional
public class RepairOrderServiceImpl implements RepairOrderService {
	private final String REPAIR_STATUS_UNASSINGED = "0"; // 未分配
	private final String REPAIR_STATUS_ASSINGED = "1";  //  已分配
	private final String REPAIR_STATUS_CLOSERD = "2";  //   关闭
	private final String REPAIR_STATUS_SUCCESSED="3"; //    已完工
	private final String IS_LOOK="0";//未查看
	@Autowired
	private RepairOrderMapper repairOrderMapper;
	@Autowired
	private RepairAssignMapper repairAssignMapper;
	
	
	@Autowired
	private FileMngMapper fileMngMapper;

	@Autowired
	private SnowFlake snowFlake;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private QiYeWeiXinUtil qiYeWeiXinUtil;
	
	@Autowired
	private FileUtil fileUtil;


	@Override
	public List<RepairOrder> getRepairOrderList(String userId) {
		return repairOrderMapper.getRepairOrderList(userId);
	}

	@Override
	public BaseResult<?> addRepairOrder(RepairAndFileVo repairAndFileVo,String userId) {
		String repairId = String.valueOf(snowFlake.nextId());
		RepairOrder repairOrder = repairAndFileVo.getRepairOrder();
		repairOrder.setRepairId(repairId);
		repairOrder.setRepairDate(new Date());
		repairOrder.setServiceLook(IS_LOOK);;
		repairOrder.setRepairLook(IS_LOOK);
		repairOrder.setRepairStatus(REPAIR_STATUS_UNASSINGED);
		//添加当前人信息
	    UserInfo   user=(UserInfo) redisTemplate.opsForValue().get(userId);
	    repairOrder.setRepairPepoleId(user.getUserid());
	    repairOrder.setRepairPepoleName(user.getName());
	    repairOrder.setRepairPepoleImg(user.getAvatar());
		int count = repairOrderMapper.insertSelective(repairOrder);
		if (count > 0) {
			fileUtil.upload2Qiniu(repairAndFileVo.getRepairOrder().getServerIds(),repairId);
             /* for (FileMng fileMng : repairAndFileVo.getList()) {
            	  String fileId = String.valueOf(snowFlake.nextId());
            	  fileMng.setRepairId(repairId);
            	  fileMng.setCreateDate(new Date());
            	  fileMng.setFileId(fileId);
            	  fileMngMapper.insertSelective(fileMng);
			}*/
              
              return BaseResult.successResult("添加成功！");   
		}
		return BaseResult.fail("添加失败！");
	}
    
	@Override
	public BaseResult<?> updateRepairOrder(RepairAndFileVo repairAndFileVo) {
		int count = repairOrderMapper.updateByPrimaryKeySelective(repairAndFileVo.getRepairOrder());
		int result=fileMngMapper.seleteByRepairId(repairAndFileVo.getRepairOrder().getRepairId());
		if(count > 0 && result>0){
			if(repairAndFileVo.getList()!=null && repairAndFileVo.getList().size()>0 ){
				 for (FileMng fileMng : repairAndFileVo.getList()) {
					 String fileId = String.valueOf(snowFlake.nextId());
	            	  fileMng.setRepairId(repairAndFileVo.getRepairOrder().getRepairId());
	            	  fileMng.setCreateDate(new Date());
	            	  fileMng.setFileId(fileId);
	            	  fileMngMapper.insertSelective(fileMng);
				}
			}
		}
		return BaseResult.fail("编辑失败！");
	}

	@Override
	public RepairAndFileVo getRepairOrderDetail(String repairId) {
		RepairAndFileVo vo=new RepairAndFileVo();
		RepairOrder repairOrder=repairOrderMapper.findRepairOrderById(repairId);
		if(repairOrder!=null && repairOrder.getRepairAssignId() != null){
			RepairAssign repairAssign=repairAssignMapper.selectByAssignPepoleId(repairOrder.getRepairAssignId());
				vo.setRepairAssign(repairAssign);
		}
		List<FileMng> fileList=fileMngMapper.getRepairOrderById(repairId);
		vo.setList(fileList);
		vo.setRepairOrder(repairOrder);
		return vo;
	}

	@Override
	public int getNotLookOrderCount(String userId) {
		
		return repairOrderMapper.getNotLookOrderCount(userId);
	}

	@Override
	public String getMaxTime(String userId) {
		
		return repairOrderMapper.getMaxTime(userId);
	}

	@Override
	public int updateIsLook(String userId) {
		
		return repairOrderMapper.updateIsLook(userId);
	}
    
	@Override
	public int updateIsLookById(String repairId,String userId) {
		RepairOrder repairOrder=repairOrderMapper.findRepairOrderById(repairId);
		if(userId != null){
			if("1".equals(repairOrder.getRepairLook())){
				return 0;
			}	
		}else{
			if("1".equals(repairOrder.getServiceLook())){
				return 0;
			}
		}
		return repairOrderMapper.updateIsLookById(repairId,userId);
	}

	@Override
	public BaseResult<?> getuserListByDepartMent() {
		// TODO Auto-generated method stub
		String message="你有一条报修信息需要处理";
	String result=	qiYeWeiXinUtil.sendMessage("TangYong","1",message);
	if("0".equals(result)){
		return BaseResult.successResult("发送成功！");
	}
		return BaseResult.fail("发送失败！");
	}

	@Override
	public BaseResult<?> closeRepairOrder(RepairOrder repairOrder) {
		repairOrder.setRepairStatus(REPAIR_STATUS_CLOSERD);
	int count=	repairOrderMapper.updateByPrimaryKeySelective(repairOrder);
	if(count > 0){
		/*RepairOrder ro=repairOrderMapper.selectOne(repairOrder);
		if(ro.getRepairAssignId() != null){
			String message="你有一条报修订单被关闭";
			qiYeWeiXinUtil.sendMessage(ro.getRepairAssignId(),ro.getRepairId(),message);
		}*/
		return BaseResult.successResult("关闭成功！");
	}
		return BaseResult.fail("关闭失败！");
	}

	@Override
	public BaseResult<?> addRepairAssignPepole(RepairAssign repairAssign,String repairId) {
		RepairOrder repairOrder=new RepairOrder();
		repairOrder.setRepairId(repairId);
		repairOrder.setRepairLook(IS_LOOK);
		repairOrder.setRepairAssignId(repairAssign.getAssignPepoleId());
		repairOrder.setRepairStatus(REPAIR_STATUS_ASSINGED);
		int count=repairOrderMapper.updateByPrimaryKeySelective(repairOrder);
		
		int result=repairAssignMapper.getByAssignPepoleId(repairAssign.getAssignPepoleId());
		if(result == 0){
			String repairAssignId = String.valueOf(snowFlake.nextId());
			repairAssign.setRepairAssignId(repairAssignId);
			repairAssignMapper.insertSelective(repairAssign);
		}
		if(count >0 ){
			String message="你有一条报修信息需要处理";
			qiYeWeiXinUtil.sendMessage(repairAssign.getAssignPepoleId(),repairId,message);
			return BaseResult.successResult("分配成功！");
		}
		
		return BaseResult.fail("分配失败！");
	}

	@Override
	public BaseResult<?> repairOrderSuccess(String repairId) {
		RepairOrder repairOrder=new RepairOrder();
		repairOrder.setRepairId(repairId);
		repairOrder.setRepairStatus(REPAIR_STATUS_SUCCESSED);
		int count=repairOrderMapper.updateByPrimaryKeySelective(repairOrder);
		if(count >0){
			return BaseResult.successResult("完工成功！");
		}
		return BaseResult.fail("完工失败！");
	}
	@Override
    public void testFile(String serverIds,String repairId){
		fileUtil.upload2Qiniu(serverIds,repairId);
	
}
}