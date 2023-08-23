package com.ruoyi.project.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.common.service.VerifyRecordService;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.post.domain.Post;
import com.ruoyi.project.system.post.mapper.PostMapper;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.mapper.RoleMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserPost;
import com.ruoyi.project.system.user.domain.UserRole;
import com.ruoyi.project.system.user.mapper.UserMapper;
import com.ruoyi.project.system.user.mapper.UserPostMapper;
import com.ruoyi.project.system.user.mapper.UserRoleMapper;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class UserServiceImpl implements IUserService
{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserPostMapper userPostMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
   
    @Autowired
    private IConfigService configService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
   	VerifyRecordService VerifyRecordService;

    
    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        // 生成数据权限过滤条件
        return userMapper.selectUserList(user);
    }
    

    /**
     * 根据条件分页查询已分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> selectAllocatedList(User user)
    {
        return userMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> selectUnallocatedList(User user)
    {
        return userMapper.selectUnallocatedList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }

    
    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }
    

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws BusinessException
    {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds)
        {
            if (User.isAdmin(userId))
            {
                throw new BusinessException("不允许删除超级管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }
    

    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(User user)
    {
        user.randomSalt();//随机盐
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));//密码加盐加密
        user.setCreateBy(ShiroUtils.getLoginName());//创建人
        // （1）新增用户信息
        int rows = userMapper.insertUser(user);
        // （2）新增用户岗位关联
        insertUserPost(user);
        // （3）新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    
    /**
     * 修改保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(User user)
    {
        Long userId = user.getUserId();//用户id
        user.setUpdateBy(ShiroUtils.getLoginName());//更信人
        // 删除用户与角色关联：通过用户ID删除用户和角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理方法
        insertUserRole(user);
        // 删除用户与岗位关联：通过用户ID删除用户和岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);
        //更新用户信息
        return userMapper.updateUser(user);
    }

    
    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(User user)
    {
        return userMapper.updateUser(user);
    }

    
    /**
     * 修改用户密码信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(User user)
    {
    	//生成随机盐
        user.randomSalt();
        //密码加盐加密
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return updateUserInfo(user);
    }

    
    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
    	//角色组
        Long[] roles = user.getRoleIds();
        //角色组非空
        if (StringUtil.isNotNull(roles))
        {
            // 新增用户与角色管理
            List<UserRole> list = new ArrayList<UserRole>();
            //循环编辑角色组关系
            for (Long roleId : user.getRoleIds())
            {
            	//用户和角色关联
                UserRole ur = new UserRole();
                ur.setUserId(user.getUserId());//用户id
                ur.setRoleId(roleId);//角色id
                list.add(ur);//加入该对象
            }
            //集合大于0，说明有对应的用户角色关联信息
            if (list.size() > 0)
            {
            	//批量新增用户角色信息
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    
    /**
     * 新增用户岗位信息
     * 
     * @param user 用户对象
     */
    public void insertUserPost(User user)
    {
    	//岗位组
        Long[] posts = user.getPostIds();
        //岗位组非空
        if (StringUtil.isNotNull(posts))
        {
            // 新增用户与岗位管理
            List<UserPost> list = new ArrayList<UserPost>();
            //循环遍历岗位组关系
            for (Long postId : user.getPostIds())
            {
            	//用户和岗位关联 
                UserPost up = new UserPost();
                up.setUserId(user.getUserId());//用户id
                up.setPostId(postId);//岗位id
                list.add(up);//加入该对象
            }
            //集合大于0，说明有对应的用户岗位关联信息
            if (list.size() > 0)
            {
            	//批量新增用户岗位信息
                userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(User user)
    {
        Long userId = StringUtil.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtil.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(User user)
    {
        Long userId = StringUtil.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtil.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    
    /**
     * 根据用户ID查询用户所属角色组
     * 返回角色名称字符串处理结果
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId)
    {
    	//根据用户id查询角色列表
        List<Role> list = roleMapper.selectRolesByUserId(userId);
        
        //返回结果字符串
        StringBuffer idsStr = new StringBuffer();
        //循环遍历拼接角色名称
        for (Role role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        //非空的情况下，去除最后一个拼接的逗号
        if (StringUtil.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }
    

    /**
     * 根据用户ID查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId)
    {
    	//根据用户ID查询岗位列表
        List<Post> list = postMapper.selectPostsByUserId(userId);
       
        //返回结果字符串
        StringBuffer idsStr = new StringBuffer();
        //循环遍历拼接岗位名称
        for (Post post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        //非空的情况下，去除最后一个拼接的逗号
        if (StringUtil.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    
    
    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importUser(List<User> userList, Boolean isUpdateSupport)
    {
        if (StringUtil.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        int successNum = 0;//成功数量
        int failureNum = 0;//失败数量
        StringBuilder successMsg = new StringBuilder();//成功消息
        StringBuilder failureMsg = new StringBuilder();//失败消息
        String operName = ShiroUtils.getLoginName();//操作人名称
        //查询系统设置的初始化密码
        String password = configService.selectConfigByKey("sys.user.initPassword");
       
        //循环导入该用户
        for (User user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                User u = userMapper.selectUserByLoginName(user.getLoginName());
                //如果用户名不存在，则直接导入
                if (StringUtil.isNull(u))
                {
                    user.setPassword(password);//密码
                    user.setCreateBy(operName);//创建人
                    //新增用户（包含用户，用户与岗位、用户与角色）
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
                }
                //否则用户名存在，如果支持更新，则更新该用户的信息
                else if (isUpdateSupport)
                {
                    user.setUpdateBy(operName);//更新人
                    //
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
                }
                //否则不操作，返回失败
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        
        //失败信息
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        //全部成功
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    
    /**
     * 用户状态修改
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int changeStatus(User user)
    {
        if (User.isAdmin(user.getUserId()))
        {
            throw new BusinessException("不允许修改超级管理员用户");
        }
        return userMapper.updateUser(user);
    }


    /**
     * 个人资料里面修改自己的密码
     */
	@Override
	@Transactional
	public R resetPwd(String oldPassword, String newPassword, String smsCode) {
		//当前用户身份对象
        User user = ShiroUtils.getSysUser();
        //（1）校验短信验证码
		R checkResult = VerifyRecordService.compare(user.getUserId().toString(), VerifyConstant.BackModifyPass, VerifyConstant.MobileAccType, user.getPhonenumber(), smsCode, VerifyConstant.SystemBack);
		if(!Type.SUCCESS.value.equals(checkResult.get("code").toString())) {
			return checkResult;
		}
		try {
			//（2）旧密码错误或者新密码未设置
			if(StringUtil.isEmpty(newPassword) || !passwordService.matches(user, oldPassword)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改密码失败，旧密码错误且新密码必须不为空");
			}
			//（3）设置更新新密码
			user.setPassword(newPassword);
			int i = this.resetUserPwd(user);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "用户密码更新失败");
			}
			//（4）更新shiro对象信息
			ShiroUtils.setSysUser(this.selectUserById(user.getUserId()));
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "用户密码修改异常");
		}
	}


	/**
	 * 重置用户密码
	 */
	@Override
	public R resetPwd(User user) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			int i = this.resetUserPwd(user);
			if(i != 1) {
				return R.error(Type.WARN, "用户密码更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "用户密码重置异常");
		}
	}
}
