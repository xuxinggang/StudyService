package com.xxg.study.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    private String id;
    private String parentId;
    private String name;
    private List<TreeNode> treeNodes;

    public TreeNode(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public TreeNode(String id, TreeNode parent, String name) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
    }
}
