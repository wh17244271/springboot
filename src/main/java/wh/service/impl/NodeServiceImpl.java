package wh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.dao.NodeDao;
import wh.dao.StudentDao;
import wh.entity.Node;
import wh.entity.Student;
import wh.service.INodeService;
import wh.service.IStudentService;

import java.util.List;

@Service("nodeService")
public class NodeServiceImpl implements INodeService {

    @Autowired
    private NodeDao nodeDao;


    @Override
    public List<Node> getNodeTree(String id) {
        return nodeDao.getNodeTree(id);
    }
}
