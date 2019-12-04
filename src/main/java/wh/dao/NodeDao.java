package wh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wh.entity.Node;
import wh.entity.Student;

import java.util.List;

@Mapper
public interface NodeDao {
    /**
     * 获取节点树
     */
    List<Node> getNodeTree(String id);

}
