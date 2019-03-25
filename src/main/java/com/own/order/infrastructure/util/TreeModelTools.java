package com.own.order.infrastructure.util;


import com.own.order.infrastructure.basetypes.TreeNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 树型结构相关工具组：
 * 1、自动添加 TREE_IDS 字段（树父节点集合）
 * 2、自动计算 NODE_LEVEL 字段（节点深度）
 *
 * @author CongJiaJia at 2019-1-7
 */
public class TreeModelTools
{
    /**
     * 添加或修改一个树型节点后重新构造该节点之下的术结构
     *
     * @param parentNode 添加时 该变量为父节点变量，如果没有父节点那么为：null，修改时亦同
     * @param childNodes 添加时 List中存放添加的一个实体，修改时是该变量本身（修改过的）及其所有子节点集合
     */
    public static void rebuildTree(TreeNode parentNode, List<TreeNode> childNodes)
    {
        //有儿子，修改所有儿子节点的 treeIds 和 nodeLevel
        if (childNodes != null && childNodes.size() > 0)
        {
            //按照TreeIds 排序
            Collections.sort(childNodes, Comparator.comparing(TreeNode::getTreeIds));
            //要删除的treeIds
            String oldTreeIds=childNodes .get(0).getTreeIds();
            //要添加的
            String partTreeIds =null;
            //无父节点
            if( parentNode ==null)
            {
                partTreeIds ="";
            }
            else if(parentNode.getParent() ==null || parentNode .getParent() .trim() .length() ==0)
            {
                partTreeIds =parentNode .getTreeNodeId();
            }
            else
            {
                partTreeIds =parentNode .getTreeIds() ;
            }

            String newTreeIds=String .format("%s,%s", partTreeIds,childNodes .get(0).getTreeNodeId()) ;

            newTreeIds = StringUtil.toTrim(newTreeIds, ',');
            for (TreeNode p : childNodes)
            {
                //设置每个节点的treeIds
                String treeIds = p.getTreeIds();
                if(treeIds ==null || treeIds .trim() .length()==0 )// treeIds为空那么该节点为添加
                {
                    treeIds =String.format("%s",newTreeIds) ;
                }
                else // 修改 treeIds
                {
                    treeIds = treeIds .replace(oldTreeIds ,newTreeIds) ;
                }
                //将treeIds 两端的 , 去掉
                treeIds =  StringUtil.toTrim(treeIds, ',');
                p.setTreeIds(treeIds);
                //设置节点深度
                p.setNodeLevel(treeIds.split(",").length);
            }
        }
    }


}
