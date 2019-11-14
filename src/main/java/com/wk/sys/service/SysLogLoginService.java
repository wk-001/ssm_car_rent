package com.wk.sys.service;

import com.wk.sys.pojo.SysLogLogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysLogLoginVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysLogLoginService extends IService<SysLogLogin> {

    /**
     * 查询所有日志
     * @param sysLogLoginVo
     * @return
     */
    DataGrid queryAllLog(SysLogLoginVo sysLogLoginVo);

    /**
     * 添加日志
     * @param sysLogLogin
     */
    void addLog(SysLogLogin sysLogLogin);

    /**
     * 根据ID删除日志
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除日志
     * @param ids
     */
    void batchDelete(Integer[] ids);
}
