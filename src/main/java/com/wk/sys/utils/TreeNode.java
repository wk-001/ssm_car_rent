package com.wk.sys.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TreeNode {

    private Integer id;

    @JsonProperty("parentId")       //pid名称序列化为parentId
    private Integer pid;            //父节点ID

    private String title;

    private String icon;            //图标

    private String href;

    private Boolean spread;         //是否展开

    private String target;          //是否在新标签打开页面

    private List<TreeNode> children = new ArrayList<>();    //子节点

    private String checkArr = "0";   //0：不选中；1：选中

    //带有复选框的树
    public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String target) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.target = target;
    }

}
