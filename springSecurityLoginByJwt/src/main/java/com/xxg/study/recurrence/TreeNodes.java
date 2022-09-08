package com.xxg.study.recurrence;

import com.alibaba.fastjson.JSON;
import com.xxg.study.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodes {

    /**
     * 使用递归方法builder the tree
     */
    public static List<TreeNode> buildTreeNodeByRecursive(List<TreeNode> treeNodes){
        List<TreeNode> trees=new ArrayList<>();
        treeNodes.stream().forEach(treeNode -> {
            if ("0".equals(treeNode.getParentId())){
                trees.add(findChildreNodes(treeNode,treeNodes));
            }
        });
        return trees;
    }

    private static TreeNode findChildreNodes(TreeNode treeNode, List<TreeNode> treeNodes) {
        treeNodes.forEach(e->{
            if (treeNode.getId().equals(e.getParentId())){
                //相等就是存在子节点
                if (null==treeNode.getTreeNodes()){
                    treeNode.setTreeNodes(new ArrayList<>());
                }
                treeNode.getTreeNodes().add(findChildreNodes(e,treeNodes));
            }
        });
        return treeNode;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode("1", "0", "深圳");
        TreeNode treeNode2 =new TreeNode("2","0","广州");

        TreeNode treeNode3 =new TreeNode("3","2","天河区");
        TreeNode treeNode4 =new TreeNode("4","2","黄秀区");

        TreeNode treeNode5 =new TreeNode("5","1","龙岗区");
        TreeNode treeNode6 =new TreeNode("6","1","龙华区");

        TreeNode treeNode7 =new TreeNode("7","5","布吉");
        TreeNode treeNode8 =new TreeNode("8","6","民治");

        TreeNode treeNode9 =new TreeNode("9","3","天河");
        TreeNode treeNode10 =new TreeNode("10","4","黄秀");

//        TreeNode treeNode1 = new TreeNode("1", "0", "深圳");
//        TreeNode treeNode2 =new TreeNode("2","0","广州");
//
//        TreeNode treeNode3 =new TreeNode("3",treeNode2,"天河区");
//        TreeNode treeNode4 =new TreeNode("4",treeNode2,"黄秀区");
//
//        TreeNode treeNode5 =new TreeNode("5",treeNode1,"龙岗区");
//        TreeNode treeNode6 =new TreeNode("6",treeNode1,"龙华区");
//
//        TreeNode treeNode7 =new TreeNode("7",treeNode5,"布吉");
//        TreeNode treeNode8 =new TreeNode("8",treeNode6,"民治");
//
//        TreeNode treeNode9 =new TreeNode("9",treeNode3,"天河");
//        TreeNode treeNode10 =new TreeNode("10",treeNode4,"黄秀");

        List<TreeNode> treeNodes= new ArrayList<>();
        treeNodes.add(treeNode1);
        treeNodes.add(treeNode2);
        treeNodes.add(treeNode3);
        treeNodes.add(treeNode4);
        treeNodes.add(treeNode5);
        treeNodes.add(treeNode6);
        treeNodes.add(treeNode7);
        treeNodes.add(treeNode8);
        treeNodes.add(treeNode9);
        treeNodes.add(treeNode10);

        List<TreeNode> trees = buildTreeNodeByRecursive(treeNodes);
        System.out.println(trees);
        System.out.println(JSON.toJSONString(trees));;
    }
}
