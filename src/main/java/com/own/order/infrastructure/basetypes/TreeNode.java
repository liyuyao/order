package com.own.order.infrastructure.basetypes;

public interface TreeNode
{
    String getTreeNodeId();

    String getTreeIds();
    void setTreeIds(String v);

    String getParent();
    void setParent(String v);

    Boolean getIsLeaf();
    void setIsLeaf(Boolean v);

    Integer getNodeLevel();
    void setNodeLevel(Integer v);

}
