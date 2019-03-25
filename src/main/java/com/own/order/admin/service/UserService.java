package com.own.order.admin.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.own.order.admin.entity.User;
import com.own.order.admin.entity.UserExample;
import com.own.order.admin.repository.UserMapper;
import com.own.order.infrastructure.util.CryptoTools;
import com.own.order.infrastructure.vo.PaggingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author:liyuyao
 * @DAte：2019-03-21 09:24
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public PaggingResult<User> getUserList(Integer pageNum, Integer pageSize, String orderByName,
                                           String orderByLoginName, String query) {

        UserExample example = new UserExample();

        // 排序
        String orderByClause = String.format("%s desc, %s", example.getFieldNameByPropName("userId"),
                example.getFieldNameByPropName("userId"));
        if (orderByName != null && orderByName.length() > 0)
        {
            orderByClause = String.format("%s %s, %s", example.getFieldNameByPropName("name"), orderByName,
                    example.getFieldNameByPropName("userId"));
        }
        if (orderByLoginName != null && orderByLoginName.length() > 0)
        {
            orderByClause = String.format("%s %s, %s", example.getFieldNameByPropName("loginName"), orderByLoginName,
                    example.getFieldNameByPropName("userId"));
        }
        example.setOrderByClause(orderByClause);

        UserExample.Criteria criteria = example.createCriteria();

        // 模糊查询
        if (query != null && query.length() > 0)
        {
            criteria.andQuery("%" + query + "%");
        }
        // 分页
        if (pageNum != null && pageSize != null)
        {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<User> list = userMapper.selectByExample(example);

        long total = 0;
        if (list instanceof Page)
        {
            total = ((Page<User>) list).getTotal();
        }

        return new PaggingResult<User>(list, total);
    }

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    public User getUserById(String id) {

        User user = new User();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if (id != null && id.length() > 0)
        {
            criteria.andUserIdEqualTo(id);
        }

        List<User> result = userMapper.selectByExample(example);
        if (result.size() > 0)
        {
            user = result.get(0);
        }

        return user;
    }

    /**
     * 新增用户
     *
     * @param entity
     */
    public void saveUser(User entity) {
        entity.setUserId(UUID.randomUUID().toString());
        try
        {
            entity.setPwd(new CryptoTools().encode("0"));
        } catch (Exception e)
        {
            throw new ValidationException(e.getMessage());
        }
        userMapper.insert(entity);

    }

    /**
     * 更新用户
     *
     * @param entity
     */
    public void updateUser(User entity) {
        if (entity.getUserId() == null || entity.getUserId().trim().length() == 0)
            throw new ValidationException("修改用户信息必须通过userId，而userId为空！");

        //如果密码不为空，重置密码
        if (entity.getPwd() != null && entity.getPwd().length() > 0)
            try
            {
                entity.setPwd(new CryptoTools().encode("0"));
            } catch (Exception e)
            {
                throw new ValidationException(e.getMessage());
            }

        userMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    public void deleteUser(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 判断用户编码是否存在
     *
     * @param id
     * @param code
     * @return
     */
    public Boolean isUserCodeExists(String id, String code) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if (id != null && id.length() > 0)
        {
            criteria.andUserIdNotEqualTo(id);
        }

        criteria.andCodeEqualTo(code);

        Long count = userMapper.countByExample(example);

        return (count > 0);
    }

    /**
     * 判断用户登录名是否存在
     *
     * @param id
     * @param loginName
     * @return
     */
    public Boolean isUserLoginNameExists(String id, String loginName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if (id != null && id.length() > 0)
        {
            criteria.andUserIdNotEqualTo(id);
        }

        criteria.andLoginNameEqualTo(loginName);

        Long count = userMapper.countByExample(example);

        return (count > 0);
    }


    /**
     * 用户登录
     */
    public User login(String loginName, String password)
    {
        CryptoTools tool;
        String encrypPwd = "";
        try
        {
            tool = new CryptoTools();
            encrypPwd = tool.encode(password);
        } catch (Exception e)
        {

        }

        User user = findUser(loginName, encrypPwd);

        return user;
    }

    private User findUser(String loginName, String password)
    {
        UserExample example = new UserExample() ;

        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        criteria.andPwdEqualTo(password);

        List<User> result = userMapper.selectByExample(example);

        if (result.isEmpty())
        {
            return null;
        } else
        {
            return result.get(0);
        }
    }

}
