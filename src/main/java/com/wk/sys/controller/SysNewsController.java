package com.wk.sys.controller;


import com.wk.sys.pojo.SysNews;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.service.SysNewsService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.ResultObj;
import com.wk.sys.utils.WebUtils;
import com.wk.sys.vo.SysNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/news")
public class SysNewsController {

    @Autowired
    private SysNewsService newsService;
    
    /**
     * 日志列表
     */
    @RequestMapping("newsList")
    public DataGrid newsList(SysNewsVo sysNewsVo){
        return newsService.queryAllNews(sysNewsVo);
    }

    /**
     * 添加日志
     */
    @RequestMapping("addNews")
    public ResultObj addNews(SysNews sysNews){
        try {
            sysNews.setCreatetime(new Date());
            SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
            sysNews.setOpername(user.getRealname());
            newsService.addNews(sysNews);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 修改日志
     */
    @RequestMapping("updateNews")
    public ResultObj updateNews(SysNews sysNews){
        try {
            newsService.updateNews(sysNews);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }


    /**
     * 删除日志
     */
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(Integer id){
        try {
            newsService.deleteById(id);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 批量删除日志
     */
    @RequestMapping("batchDeleteNews")
    public ResultObj batchDeleteNews(Integer[] ids){
        try {
            newsService.batchDelete(ids);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    @RequestMapping("getNewsById")
    public SysNews getNewsById(Integer id){
        return newsService.getNewsById(id);
    }
    
}
