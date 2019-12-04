package wh.service;

import org.apache.ibatis.annotations.Param;
import wh.entity.Node;
import wh.entity.Student;

import java.util.List;


public interface INodeService {

    /**
     * 获取节点树
     */
    List<Node> getNodeTree(String id);
}
