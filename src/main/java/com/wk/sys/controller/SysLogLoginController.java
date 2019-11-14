package com.wk.sys.controller;


import com.wk.sys.pojo.SysLogLogin;
import com.wk.sys.service.SysLogLoginService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.ResultObj;
import com.wk.sys.vo.SysLogLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/log")
public class SysLogLoginController {

    @Autowired
    private SysLogLoginService logService;
    
    /**
     * 日志列表
     */
    @RequestMapping("logList")
    public DataGrid logList(SysLogLoginVo sysLogLoginVo){
        return logService.queryAllLog(sysLogLoginVo);
    }

    /**
     * 添加日志
     */
    @RequestMapping("addLog")
    public ResultObj addLog(SysLogLogin sysLogLogin){
        try {
            logService.addLog(sysLogLogin);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }


    /**
     * 删除日志
     */
    @RequestMapping("deleteLog")
    public ResultObj deleteLog(Integer id){
        try {
            logService.deleteById(id);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 批量删除日志
     */
    @RequestMapping("batchDeleteLog")
    public ResultObj batchDeleteLog(Integer[] ids){
        try {
            logService.batchDelete(ids);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

}
