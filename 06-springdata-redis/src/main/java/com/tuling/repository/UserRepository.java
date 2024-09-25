package com.tuling.repository;

import com.tuling.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * CrudRepository 提供基本CRUD
 * PagingAndSortingRepository  在基本CRUD还提供分页还排序
 *
 * 实现机制：  jdk动态代理  ，  调用对应jedis命令
 */
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    
}
