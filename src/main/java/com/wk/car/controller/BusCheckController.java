package com.wk.car.controller;


import com.wk.car.pojo.BusCheck;
import com.wk.car.service.BusCheckService;
import com.wk.car.vo.BusCheckVo;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("check")
public class BusCheckController {

	@Autowired
	private BusCheckService checkService;

	/**
	 * 根据出租单号加载检查单的表单数据
	 * @param rentid
	 * @return
	 */
	@RequestMapping("initCheckFormData")
	public Map<String,Object> initCheckFormData(String rentid){
		return checkService.initCheckFormData(rentid);
	}

	/**
	 * 新增检查单
	 * @param check
	 * @return
	 */
	@RequestMapping("saveCheck")
	public ResultObj saveCheck(BusCheck check){
		check.setCreatetime(new Date());
		try {
			checkService.saveCheck(check);
			return ResultObj.OPERAT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	/**
	 * 查询所有检查单
	 * @param checkVo
	 * @return
	 */
	@RequestMapping("checkList")
	public DataGrid checkList(BusCheckVo checkVo){
		return checkService.queryAllCheck(checkVo);
	}

	/**
	 * 根据ID修改检查单
	 * @param check
	 * @return
	 */
	@RequestMapping("updateById")
	public ResultObj updateById(BusCheck check){
		try {
			checkService.updateById(check);
			return ResultObj.OPERAT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}
}
