package wh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
     void moveIn(@Param("id") int id, @Param("money") float money); // 转入

     void moveOut(@Param("id") int id, @Param("money") float money); // 转出
}
