package com.wk.sys.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeUtils {

    /**
     * 将简单的集合数据转换成有层级关系的树形结构集合数据
     * @param nodes
     * @param topPid
     * @return
     */
    public static List<TreeNode> builder(List<TreeNode> nodes, Integer topPid) {
        //组装数据
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode n1 : nodes) {
            if(n1.getPid()==topPid){
                treeNodes.add(n1);      //在新的集合中存放父节点
            }
            for (TreeNode n2 : nodes) {
                if(n2.getPid()==n1.getId()){    //如果集合2的pid等于集合1的ID 那么就把这条数据放到集合1的children集合中
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }
}
