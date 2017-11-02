package com.baixin.ees.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baixin.ees.Constants;
import com.baixin.ees.util.DesUtil;
import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.util.UserUtils;
import com.baixin.ees.web.dao.mapper.SysMenuJoinMapper;
import com.baixin.ees.web.dao.mapper.SysUserJoinMapper;
import com.baixin.ees.web.dao.mapper.SysUserMapper;
import com.baixin.ees.web.dao.model.MenuTree;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserExample;
import com.baixin.ees.web.dao.model.SysUserExample.Criteria;
import com.baixin.ees.web.dao.model.SysUserResult;
import com.baixin.ees.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession eesSqlSession;

	public static final String INIT_PASSWORD = "123456";

	public static final String UP_STATUS = "1";
	public static final String DOWN_STATUS = "0";

	@Override
	public ResultMessage add(SysUser user) {
		try {
			user.setPassword(DesUtil.encryptStr(INIT_PASSWORD));
			user.setStatus(UP_STATUS);
			user.setCreatedate(new Date());
			user.setLastupdatetime(new Date());
			SysUserMapper mapper = eesSqlSession.getMapper(SysUserMapper.class);
			mapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage update(SysUser user) {
		try {
			user.setPassword(null);
			user.setLastupdatetime(new Date());
			eesSqlSession.getMapper(SysUserMapper.class).updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage delete(SysUser user) {
		try {
			eesSqlSession.getMapper(SysUserMapper.class).deleteByPrimaryKey(user.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage checkLoginNameExsit(String loginName) {
		try {
			if (StringUtils.isNotBlank(loginName)) {
				SysUserMapper mapper = eesSqlSession.getMapper(SysUserMapper.class);
				SysUserExample example = new SysUserExample();
				Criteria criteria = example.createCriteria();
				criteria.andLoginNameEqualTo(loginName);
				if (mapper.countByExample(example) > 0) {
					return ResultMessage.isOk("该用户存在", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isOk("校验发生错误", null);
		}

		return ResultMessage.isError("该用户可用");
	}

	@Override
	public ResultMessage deleteById(Integer userId) {
		try {
			SysUserMapper mapper = eesSqlSession.getMapper(SysUserMapper.class);
			mapper.deleteByPrimaryKey(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage deleteListById(String userIds) {
		try {
			if (StringUtils.isNotBlank(userIds)) {
				SysUserJoinMapper mapper = eesSqlSession.getMapper(SysUserJoinMapper.class);
				mapper.deleteByIds(userIds);
				return ResultMessage.isOk();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isError("参数不能为空");
	}

	@Override
	public ResultMessage upUserListStatus(String userIds) {
		try {
			if (StringUtils.isNotBlank(userIds)) {
				SysUserJoinMapper mapper = eesSqlSession.getMapper(SysUserJoinMapper.class);
				mapper.upUserListStatus(new Date(), userIds);
				return ResultMessage.isOk();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isError("参数不能为空");
	}

	@Override
	public ResultMessage downUserListStatus(String userIds) {
		try {
			if (StringUtils.isNotBlank(userIds)) {
				SysUserJoinMapper mapper = eesSqlSession.getMapper(SysUserJoinMapper.class);
				mapper.downUserListStatus(new Date(), userIds);
				return ResultMessage.isOk();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isError("参数不能为空");
	}

	@Override
	public ResultMessage initPwd(String userIds) {
		try {
			if (StringUtils.isNotBlank(userIds)) {
				SysUserJoinMapper mapper = eesSqlSession.getMapper(SysUserJoinMapper.class);
				mapper.initPwd(DesUtil.encryptStr(INIT_PASSWORD), new Date(), userIds);
				return ResultMessage.isOk();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();

		}
		return ResultMessage.isError("参数不能为空");
	}

	@Override
	public ResultMessage updatePwd(Integer userId, String newPwd) {
		try {
			SysUserMapper mapper = eesSqlSession.getMapper(SysUserMapper.class);
			SysUser user = new SysUser();
			user.setUserId(userId);
			user.setPassword(DesUtil.encryptStr(newPwd));
			user.setLastupdatetime(new Date());
			mapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public List<MenuTree> getUserMenuTree(Integer rootNodeId, List<SysMenu> sysMenuList) {
		List<MenuTree> treeList = new ArrayList<MenuTree>();
		for (SysMenu menu : sysMenuList) {
			if (rootNodeId.equals(menu.getParentMenuId())) {
				MenuTree tree = new MenuTree();
				tree.setText(menu.getMenuName());
				tree.setHref(menu.getMenuUrl());
				tree.setTags(menu.getMenuId().toString());
				tree.setNodes(getUserMenuTree(menu.getMenuId(), sysMenuList));
				treeList.add(tree);
			}
		}
		return treeList;
	}

	@Override
	public Page<SysUserResult> selectListResult(SysUserResult user) throws MyException {
		Page<SysUserResult> page;
		try {
			page = new Page<SysUserResult>();
			SysUserJoinMapper mapper = eesSqlSession.getMapper(SysUserJoinMapper.class);
			List<SysUserResult> selectListResult = mapper.selectListResult(user);
			page.setData(selectListResult);
			page.setDraw(user.getDraw());
			int countListResult = mapper.countListResult(user);
			page.setRecordsFiltered(countListResult);
			page.setRecordsTotal(countListResult);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询用户列表时发生未知异常");
		}
		return page;
	}

	@Override
	public List<MenuTree> getUserMenu() throws MyException {
		List<SysMenu> sysMenuList = null;
		try {
			SysMenuJoinMapper mapper = eesSqlSession.getMapper(SysMenuJoinMapper.class);
			sysMenuList = mapper.selectMenuByIds(UserUtils.getSessionFromCache().getSysAuth().getMenuIds());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"获取用户菜单时发生未知异常");
		}
		return getUserMenuTree(Constants.ROOT_MENU_ID, sysMenuList);
	}
}
