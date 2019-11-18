package com.wk.sys.task;

import com.wk.sys.constast.SysConstast;
import com.wk.sys.utils.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 定时任务 清理临时文件
 */
@Component
@EnableScheduling       //启用定时任务
public class CleanTempFile {

    /**
     * 每天0点清除临时文件
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void cleanTempFile(){
        File file = new File(AppFileUtils.PATH);
        deleteFile(file);
    }

    public void deleteFile(File file){
        if (null != file) {
            //获取路径下的所有文件
            File[] files = file.listFiles();
            if(null != files && files.length>0){
                for (File f : files) {
                    if (f.isFile()){        //如果是文件，判断是否以_temp结尾
                        if (f.getName().endsWith(SysConstast.FILE_UPLOAD_TEMP)){
                            f.delete();
                        }
                    }else {
                        deleteFile(f);  //如果是文件夹，就递归调用本方法
                    }

                }
            }
        }
    }
}
