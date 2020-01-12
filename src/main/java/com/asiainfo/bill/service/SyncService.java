package com.asiainfo.bill.service;

/*
@Service
@Transactional
public class SyncService {
    private static Logger logger = Logger.getLogger(SyncService.class);
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemPostMapper systemPostMapper;
    @Autowired
    private SystemUserPostMapper systemUserPostMapper;
    @Autowired
    private SystemRolesMapper systemRolesMapper;
    @Autowired
    private SystemPostRoleMapper systemPostRoleMapper;
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;
    @Autowired
    private PrivilegeMapper privilegeMapper;
    @Autowired
    private PrivGrantMapper privGrantMapper;
    @Autowired
    private FuncMenuMapper funcMenuMapper;
    @Autowired
    private FuncCompMapper funcCompMapper;
    @Autowired
    private PrivFuncRelMapper privFuncRelMapper;


    public void syncData(String requestBody, JSONObject result) throws Exception {
        // 新建3个Map 用于分类各个类型的数据
        Map addMap = new HashMap();

        Map deleteMap = new HashMap();

        Map updateMap = new HashMap();

        JSONObject jsonDatas = JSON.parseObject(requestBody);

        for (String keyName : jsonDatas.keySet()) {
            Map jsonData = (Map) jsonDatas.get(keyName);
            // 新增
            if ("A".equals(jsonData.get("actType"))) {
                addMap.put(keyName, jsonData.get("data"));
                // 修改
            } else if ("M".equals(jsonData.get("actType"))) {
                updateMap.put(keyName, jsonData.get("data"));
                // 删除
            } else if ("D".equals(jsonData.get("actType"))) {
                deleteMap.put(keyName, jsonData.get("data"));
            } else {
                result.put("message", "actType格式错误请确认actType的参数值");
                throw new Exception("actType格式错误请确认actType的参数值");
            }
        }

        // 报文删除方法
        delete(deleteMap, result);

        // 报文新增方法
        add(addMap, result);

        // 报文修改方法
        update(updateMap, result);

        result.put("status", 0);
        result.put("message", "处理成功");
    }

    // 1.报文新增方法
    public void add(Map maps, JSONObject result) throws Exception {
        // 1.员工

        try {
            if (maps.containsKey("staff")) {
                Staff staff = JSON.parseObject(maps.get("staff").toString(), Staff.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                Staff staffBean = staffMapper.selectByPrimaryKey(staff.getStaffId());
                if (staffBean != null) {
                    result.put("message", "员工标识为:" + staffBean.getStaffId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                staffMapper.insert(staff);
            }

            // 2.系统用户
            if (maps.containsKey("systemUser")) {
                SystemUser systemUser = JSON.parseObject(maps.get("systemUser").toString(), SystemUser.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUser.getSysUserId());
                if (systemUserBean != null) {
                    result.put("message", "systemUser系统用户标识为:" + systemUserBean.getSysUserId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果Staff表中，不存在systemUser报文中的StaffId，则提示不存在关联的数据
                Staff staffBean = staffMapper.selectByPrimaryKey(systemUser.getStaffId());
                if (staffBean == null) {
                    result.put("message", "systemUser员工用户数据的外键值StaffId:" + systemUser.getStaffId() + "在Staff表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserMapper.insert(systemUser);
            }

            // 3.系统岗位
            if (maps.containsKey("systemPost")) {
                SystemPost systemPost = JSON.parseObject(maps.get("systemPost").toString(), SystemPost.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                SystemPost systemPostBean = systemPostMapper.selectByPrimaryKey(systemPost.getSysPostId());
                if (systemPostBean != null) {
                    result.put("message", "systemPost系统岗位标识为:" + systemPostBean.getSysPostId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemPostMapper.insert(systemPost);
            }

            // 4.系统用户任职岗位
            if (maps.containsKey("systemUserPost")) {
                SystemUserPost systemUserPost = JSON.parseObject(maps.get("systemUserPost").toString(), SystemUserPost.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                SystemUserPost systemUserPostBean = systemUserPostMapper.selectByPrimaryKey(systemUserPost.getSysUserPostId());
                if (systemUserPostBean != null) {
                    result.put("message", "systemUserPost系统用户任职岗位标识:" + systemUserPostBean.getSysUserPostId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemUser表中，不存在SystemUserPost报文中的SystemUserId，则提示不存在关联的数据
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUserPost.getSysUserId());
                if (systemUserBean == null) {
                    result.put("message", "systemUserPost系统用户任职岗位的外键值SystemUserId:" + systemUserPost.getSysUserId() + "在systemUser表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemPort表中，不存在SystemUserPost报文中的SystemPostId，则提示不存在关联的数据
                SystemPost systemPortBean = systemPostMapper.selectByPrimaryKey(systemUserPost.getSysPostId());
                if (systemPortBean == null) {
                    result.put("message", "systemUserPost系统用户任职岗位的外键值SystemPostId:" + systemUserPost.getSysPostId() + "在systemPort表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserPostMapper.insert(systemUserPost);
            }

            // 5.系统角色
            if (maps.containsKey("systemRoles")) {
                SystemRoles systemRoles = JSON.parseObject(maps.get("systemRoles").toString(), SystemRoles.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemRoles.getSysRoleId());
                if (systemRolesBean != null) {
                    result.put("message", "systemRoles系统角色标识:" + systemRolesBean.getSysRoleId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemRolesMapper.insert(systemRoles);
            }

            // 6.系统岗位对应角色
            if (maps.containsKey("systemPostRole")) {
                SystemPostRole systemPostRole = JSON.parseObject(maps.get("systemPostRole").toString(), SystemPostRole.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                SystemPostRole systemPostRoleBean = systemPostRoleMapper.selectByPrimaryKey(systemPostRole.getSysPostRoleId());
                if (systemPostRoleBean != null) {
                    result.put("message", "systemPostRole系统岗位对应角色标识:" + systemPostRoleBean.getSysPostRoleId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemPort表中，不存在systemPostRole报文中的sysPostId，则提示不存在关联的数据
                SystemPost systemPortBean = systemPostMapper.selectByPrimaryKey(systemPostRole.getSysPostId());
                if (systemPortBean == null) {
                    result.put("message", "systemPostRole系统岗位对应角色的外键值sysPostId:" + systemPostRole.getSysPostId() + "在systemPort表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemRoles表中，不存在systemPostRole报文中的sysRoleId，则提示不存在关联的数据
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemPostRole.getSysRoleId());
                if (systemRolesBean == null) {
                    result.put("message", "systemPostRole系统岗位对应角色的外键值sysRoleId:" + systemPostRole.getSysRoleId() + "在systemRole表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemPostRoleMapper.insert(systemPostRole);
            }

            // 7.系统用户角色
            if (maps.containsKey("systemUserRole")) {
                SystemUserRole systemUserRole = JSON.parseObject(maps.get("systemUserRole").toString(), SystemUserRole.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                SystemUserRole systemUserRoleBean = systemUserRoleMapper.selectByPrimaryKey(systemUserRole.getSysUserRoleId());
                if (systemUserRoleBean != null) {
                    result.put("message", "systemUserRole系统用户角色标识:" + systemUserRoleBean.getSysUserRoleId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemUser表中，不存在systemUserRole报文中的SystemUserId，则提示不存在关联的数据
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUserRole.getSysUserId());
                if (systemUserBean == null) {
                    result.put("message", "systemUserRole系统用户角色的外键值:" + systemUserRole.getSysUserId() + "在systemUser表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemRoles表中，不存在systemPostRole报文中的sysRoleId，则提示不存在关联的数据
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemUserRole.getSysRoleId());
                if (systemRolesBean == null) {
                    result.put("message", "systemUserRole系统用户角色的外键值:" + systemUserRole.getSysRoleId() + "在systemPostRole表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserRoleMapper.insert(systemUserRole);
            }

            // 8.权限
            if (maps.containsKey("privilege")) {
                Privilege privilege = JSON.parseObject(maps.get("privilege").toString(), Privilege.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                Privilege privilegeBean = privilegeMapper.selectByPrimaryKey(privilege.getPrivId());
                if (privilegeBean != null) {
                    result.put("message", "privilege权限标识:" + privilegeBean.getPrivId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                privilegeMapper.insert(privilege);
            }

            // 9.授权
            if (maps.containsKey("privGrant")) {
                PrivGrant privGrant = JSON.parseObject(maps.get("privGrant").toString(), PrivGrant.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                PrivGrant privGrantBean = privGrantMapper.selectByPrimaryKey(privGrant.getPrivGrantId());
                if (privGrantBean != null) {
                    result.put("message", "privGrant授权标识:" + privGrantBean.getPrivGrantId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 如果Privilege表中，不存在privGrant报文中的privGrantId，则提示不存在关联的数据
                Privilege privilegeBean = privilegeMapper.selectByPrimaryKey(privGrant.getPrivId());
                if (privilegeBean == null) {
                    result.put("message", "privGrant授权的外键值:" + privGrant.getPrivId() + "在Privilege表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                privGrantMapper.insert(privGrant);
            }

            // 10.功能菜单
            if (maps.containsKey("funcMenu")) {
                FuncMenu funcMenu = JSON.parseObject(maps.get("funcMenu").toString(), FuncMenu.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                FuncMenu funcMenuBean = funcMenuMapper.selectByPrimaryKey(funcMenu.getMenuId());
                if (funcMenuBean != null) {
                    result.put("message", "funcMenu功能菜单标识:" + funcMenuBean.getMenuId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 查询父节点是否存在
                if (funcMenu.getParMenuId() != null) {
                    FuncMenu funcParMenuBean = funcMenuMapper.selectByPrimaryKey(funcMenu.getParMenuId());
                    if (funcParMenuBean == null) {
                        result.put("message", "funcMenu功能菜单标识:" + funcMenu.getParMenuId() + "的数据不存在。无法执行新增操作，请确认数据是否有误");
                        logger.info(result.get("message"));
                        throw new Exception((String) result.get("message"));
                    }
                }
                funcMenuMapper.insert(funcMenu);
            }

            // 11.功能组件
            if (maps.containsKey("funcComp")) {
                FuncComp funcComp = JSON.parseObject(maps.get("funcComp").toString(), FuncComp.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                FuncComp funcCompBean = funcCompMapper.selectByPrimaryKey(funcComp.getCompId());
                if (funcCompBean != null) {
                    result.put("message", "funcComp功能菜单标识:" + funcCompBean.getCompId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果FuncMenu表中，不存在funcComp报文中的menuId，则提示不存在关联的数据
                FuncMenu funcMenuBean = funcMenuMapper.selectByPrimaryKey(funcComp.getMenuId());
                if (funcMenuBean == null) {
                    result.put("message", "menuId:" + funcComp.getMenuId() + "在FuncMenu表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                funcCompMapper.insert(funcComp);
            }

            // 12.权限包含功能
            if (maps.containsKey("privFuncRel")) {
                PrivFuncRel privFuncRel = JSON.parseObject(maps.get("privFuncRel").toString(), PrivFuncRel.class);
                // 如果数据库中，已经存在一条数据，但是传过来的类型又要我们新增，则提示已存在该数据，无法新增
                PrivFuncRel privFuncRelBean = privFuncRelMapper.selectByPrimaryKey(privFuncRel.getPrivFuncRelId());
                if (privFuncRelBean != null) {
                    result.put("message", "privFuncRel功能菜单标识:" + privFuncRelBean.getPrivFuncRelId() + "的数据已存在。无法执行新增操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 提示不存在关联的数据
                Privilege PrivilegeBean = privilegeMapper.selectByPrimaryKey(privFuncRel.getPrivId());
                if (PrivilegeBean == null) {
                    result.put("message", "privFuncRel授权的外键值:" + privFuncRel.getPrivId() + "在Privilege表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                privFuncRelMapper.insert(privFuncRel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "异常错误：" + e.getMessage()!=null&&e.getMessage().length()>256?e.getMessage().substring(0,256):e.getMessage());
            throw new Exception((String) result.get("message"));
        }

    }

    // 报文修改方法
    public void update(Map maps, JSONObject result) throws Exception {
        // 1.员工
        try {
            if (maps.containsKey("staff")) {
                Staff staff = JSON.parseObject(maps.get("staff").toString(), Staff.class);
                // 如果Staff表中，不存在StaffId，则提示无法进行数据更新
                Staff staffBean = staffMapper.selectByPrimaryKey(staff.getStaffId());
                if (staffBean == null) {
                    result.put("message", staff.getStaffId() + "在Staff表中不存在，无法进行数据更新");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                staffMapper.updateByPrimaryKey(staff);
            }

            // 2.系统用户
            if (maps.containsKey("systemUser")) {
                SystemUser systemUser = JSON.parseObject(maps.get("systemUser").toString(), SystemUser.class);
                // 如果数据库中，不存在一条数据，但是传过来的类型又要我们更新，报错
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUser.getSysUserId());
                if (systemUserBean == null) {
                    result.put("message", "systemUser系统用户标识为:" + systemUser.getSysUserId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果Staff表中，不存在systemUser报文中的StaffId，则提示不存在关联的数据
                Staff staffBean = staffMapper.selectByPrimaryKey(systemUser.getStaffId());
                if (staffBean == null) {
                    result.put("message", "systemUser员工用户数据的外键值:" + systemUser.getStaffId() + "在Staff表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserMapper.updateByPrimaryKey(systemUser);
            }

            // 3.系统岗位
            if (maps.containsKey("systemPost")) {
                SystemPost systemPost = JSON.parseObject(maps.get("systemPost").toString(), SystemPost.class);
                // 如果数据库中，不存在一条数据，但是传过来的类型又要我们更新，则提示不存在该数据，无法更新
                SystemPost systemPostBean = systemPostMapper.selectByPrimaryKey(systemPost.getSysPostId());
                if (systemPostBean == null) {
                    result.put("message", "systemPost系统岗位标识为:" + systemPost.getSysPostId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemPostMapper.updateByPrimaryKey(systemPost);
            }

            // 4.系统用户任职岗位
            if (maps.containsKey("systemUserPost")) {
                SystemUserPost systemUserPost = JSON.parseObject(maps.get("systemUserPost").toString(), SystemUserPost.class);
                SystemUserPost systemUserPostBean = systemUserPostMapper.selectByPrimaryKey(systemUserPost.getSysUserPostId());
                if (systemUserPostBean == null) {
                    result.put("message", "systemUserPost系统用户任职岗位标识:" + systemUserPost.getSysUserPostId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemUser表中，不存在SystemUserPost报文中的SystemUserId，则提示不存在关联的数据
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUserPost.getSysUserId());
                if (systemUserBean == null) {
                    result.put("message", "systemUserPost系统用户任职岗位的外键值:" + systemUserPost.getSysUserId() + "在systemUser表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemPort表中，不存在SystemUserPost报文中的SystemPostId，则提示不存在关联的数据
                SystemPost systemPortBean = systemPostMapper.selectByPrimaryKey(systemUserPost.getSysPostId());
                if (systemPortBean == null) {
                    result.put("message", "systemUserPost系统用户任职岗位的外键值:" + systemUserPost.getSysPostId() + "在systemPort表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserPostMapper.updateByPrimaryKey(systemUserPost);
            }

            // 5.系统角色
            if (maps.containsKey("systemRoles")) {
                SystemRoles systemRoles = JSON.parseObject(maps.get("systemRoles").toString(), SystemRoles.class);
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemRoles.getSysRoleId());
                if (systemRolesBean == null) {
                    result.put("message", "systemRoles系统角色标识:" + systemRoles.getSysRoleId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemRolesMapper.updateByPrimaryKey(systemRoles);
            }

            // 6.系统岗位对应角色
            if (maps.containsKey("systemPostRole")) {
                SystemPostRole systemPostRole = JSON.parseObject(maps.get("systemPostRole").toString(), SystemPostRole.class);
                SystemPostRole systemPostRoleBean = systemPostRoleMapper.selectByPrimaryKey(systemPostRole.getSysPostRoleId());
                if (systemPostRoleBean == null) {
                    result.put("message", "systemPostRole系统岗位对应角色标识:" + systemPostRole.getSysPostRoleId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemPort表中，不存在systemPostRole报文中的sysPostId，则提示不存在关联的数据
                SystemPost systemPortBean = systemPostMapper.selectByPrimaryKey(systemPostRole.getSysPostId());
                if (systemPortBean == null) {
                    result.put("message", "systemPostRole系统岗位对应角色的外键值:" + systemPostRole.getSysPostId() + "在systemPort表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemRoles表中，不存在systemPostRole报文中的sysRoleId，则提示不存在关联的数据
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemPostRole.getSysRoleId());
                if (systemRolesBean == null) {
                    result.put("message", "systemPostRole系统岗位对应角色的外键值:" + systemPostRole.getSysRoleId() + "在systemPostRole表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemPostRoleMapper.updateByPrimaryKey(systemPostRole);
            }

            // 7.系统用户角色
            if (maps.containsKey("systemUserRole")) {
                SystemUserRole systemUserRole = JSON.parseObject(maps.get("systemUserRole").toString(), SystemUserRole.class);
                SystemUserRole systemUserRoleBean = systemUserRoleMapper.selectByPrimaryKey(systemUserRole.getSysUserRoleId());
                if (systemUserRoleBean == null) {
                    result.put("message", "systemUserRole系统用户角色标识:" + systemUserRole.getSysUserRoleId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemUser表中，不存在systemUserRole报文中的SystemUserId，则提示不存在关联的数据
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUserRole.getSysUserId());
                if (systemUserBean == null) {
                    result.put("message", "systemUserRole系统用户角色的外键值:" + systemUserRole.getSysUserId() + "在systemUser表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果systemRoles表中，不存在systemPostRole报文中的sysRoleId，则提示不存在关联的数据
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemUserRole.getSysRoleId());
                if (systemRolesBean == null) {
                    result.put("message", "systemUserRole系统用户角色的外键值:" + systemUserRole.getSysRoleId() + "在systemPostRole表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserRoleMapper.updateByPrimaryKey(systemUserRole);
            }

            // 8.权限
            if (maps.containsKey("privilege")) {
                Privilege privilege = JSON.parseObject(maps.get("privilege").toString(), Privilege.class);
                Privilege privilegeBean = privilegeMapper.selectByPrimaryKey(privilege.getPrivId());
                if (privilegeBean == null) {
                    result.put("message", "权限标识:" + privilege.getPrivId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                privilegeMapper.updateByPrimaryKey(privilege);
            }

            // 9.授权
            if (maps.containsKey("privGrant")) {
                PrivGrant privGrant = JSON.parseObject(maps.get("privGrant").toString(), PrivGrant.class);
                PrivGrant privGrantBean = privGrantMapper.selectByPrimaryKey(privGrant.getPrivGrantId());
                if (privGrantBean == null) {
                    result.put("message", "privGrant授权标识:" + privGrant.getPrivGrantId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 如果Privilege表中，不存在privGrant报文中的privGrantId，则提示不存在关联的数据
                Privilege privilegeBean = privilegeMapper.selectByPrimaryKey(privGrant.getPrivId());
                if (privilegeBean == null) {
                    result.put("message", "privGrant授权的外键值:" + privGrant.getPrivId() + "在Privilege表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 保存数据
                privGrantMapper.updateByPrimaryKey(privGrant);
            }

            // 10.功能菜单
            if (maps.containsKey("funcMenu")) {
                FuncMenu funcMenu = JSON.parseObject(maps.get("funcMenu").toString(), FuncMenu.class);
                FuncMenu funcMenuBean = funcMenuMapper.selectByPrimaryKey(funcMenu.getMenuId());
                if (funcMenuBean == null) {
                    result.put("message", "funcMenu功能菜单标识:" + funcMenu.getMenuId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 效验父节点是否存在
                if (funcMenu.getParMenuId() != null) {
                    FuncMenu funcParMenuBean = funcMenuMapper.selectByPrimaryKey(funcMenu.getParMenuId());
                    if (funcParMenuBean == null) {
                        result.put("message", "funcMenu功能菜单标识:" + funcMenu.getParMenuId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                        logger.info(result.get("message"));
                        throw new Exception((String) result.get("message"));
                    }
                }
                funcMenuMapper.updateByPrimaryKey(funcMenu);
            }

            // 11.功能组件
            if (maps.containsKey("funcComp")) {
                FuncComp funcComp = JSON.parseObject(maps.get("funcComp").toString(), FuncComp.class);
                FuncComp funcCompBean = funcCompMapper.selectByPrimaryKey(funcComp.getCompId());
                if (funcCompBean == null) {
                    result.put("message", "funcComp功能菜单标识:" + funcComp.getCompId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 如果FuncMenu表中，不存在funcComp报文中的menuId，则提示不存在关联的数据
                FuncMenu funcMenuBean = funcMenuMapper.selectByPrimaryKey(funcComp.getMenuId());
                if (funcMenuBean == null) {
                    result.put("message", "funcComp授权的外键值:" + funcComp.getMenuId() + "在FuncMenu表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                funcCompMapper.updateByPrimaryKey(funcComp);
            }

            // 12.权限包含功能
            if (maps.containsKey("privFuncRel")) {
                PrivFuncRel privFuncRel = JSON.parseObject(maps.get("privFuncRel").toString(), PrivFuncRel.class);
                PrivFuncRel privFuncRelBean = privFuncRelMapper.selectByPrimaryKey(privFuncRel.getPrivFuncRelId());
                if (privFuncRelBean == null) {
                    result.put("message", "privFuncRel功能菜单标识:" + privFuncRel.getPrivFuncRelId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 提示不存在关联的数据
                Privilege PrivilegeBean = privilegeMapper.selectByPrimaryKey(privFuncRel.getPrivId());
                if (PrivilegeBean == null) {
                    result.put("message", "privFuncRel授权的外键值:" + privFuncRel.getPrivId() + "在Privilege表中不存在，无法进行主外键关联");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                privFuncRelMapper.updateByPrimaryKey(privFuncRel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "异常错误：" + e.getMessage()!=null&&e.getMessage().length()>256?e.getMessage().substring(0,256):e.getMessage());
            throw new Exception((String) result.get("message"));
        }
    }

    public void delete(Map maps, JSONObject result) throws Exception {
        // 12.权限包含功能
        try {
            if (maps.containsKey("privFuncRel")) {
                PrivFuncRel privFuncRel = JSON.parseObject(maps.get("privFuncRel").toString(), PrivFuncRel.class);
                PrivFuncRel privFuncRelBean = privFuncRelMapper.selectByPrimaryKey(privFuncRel.getPrivFuncRelId());
                if (privFuncRelBean == null) {
                    result.put("message", "privFuncRel功能菜单标识:" + privFuncRel.getPrivFuncRelId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除数据
                privFuncRelMapper.deleteByPrimaryKey(privFuncRel.getPrivFuncRelId());
            }

            // 11.功能组件
            if (maps.containsKey("funcComp")) {
                FuncComp funcComp = JSON.parseObject(maps.get("funcComp").toString(), FuncComp.class);
                FuncComp funcCompBean = funcCompMapper.selectByPrimaryKey(funcComp.getCompId());
                if (funcCompBean == null) {
                    result.put("message", "funcComp功能菜单标识:" + funcComp.getCompId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除数据
                funcCompMapper.deleteByPrimaryKey(funcComp.getCompId());
            }

            // 10.功能菜单
            if (maps.containsKey("funcMenu")) {
                FuncMenu funcMenu = JSON.parseObject(maps.get("funcMenu").toString(), FuncMenu.class);
                FuncMenu funcMenuBean = funcMenuMapper.selectByPrimaryKey(funcMenu.getMenuId());
                if (funcMenuBean == null) {
                    result.put("message", "funcMenu功能菜单标识:" + funcMenu.getMenuId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 效验功能组件中的menuId是否存在
                if (funcMenu.getParMenuId() != null) {
                    List<FuncComp> funcCompsBean = funcCompMapper.selectByMenuId(funcMenu.getMenuId());
                    if (funcCompsBean.size() != 0) {
                        result.put("message", "funcComp功能菜单标识:" + funcMenu.getMenuId() + "的还存在子节点。无法执行删除操作，请确认数据是否有误");
                        logger.info(result.get("message"));
                        throw new Exception((String) result.get("message"));
                    }
                }
                // 删除数据
                funcMenuMapper.deleteByPrimaryKey(funcMenu.getMenuId());
            }

            // 9.授权
            if (maps.containsKey("privGrant")) {
                PrivGrant privGrant = JSON.parseObject(maps.get("privGrant").toString(), PrivGrant.class);
                PrivGrant privGrantBean = privGrantMapper.selectByPrimaryKey(privGrant.getPrivGrantId());
                if (privGrantBean == null) {
                    result.put("message", "privGrant授权标识:" + privGrant.getPrivGrantId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除数据
                privGrantMapper.deleteByPrimaryKey(privGrant.getPrivGrantId());
            }

            // 8.权限
            if (maps.containsKey("privilege")) {
                Privilege privilege = JSON.parseObject(maps.get("privilege").toString(), Privilege.class);
                Privilege privilegeBean = privilegeMapper.selectByPrimaryKey(privilege.getPrivId());
                if (privilegeBean == null) {
                    result.put("message", "权限标识:" + privilege.getPrivId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                List<PrivGrant> privGrantBean = privGrantMapper.selectByPrivId(privilege.getPrivId());
                if (privGrantBean.size() != 0) {
                    result.put("message", "privGrant授权标识:" + privilege.getPrivId() + "还存在外键。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                List<PrivFuncRel> PrivFuncRelBean = privFuncRelMapper.selectByPrivId(privilege.getPrivId());
                if (PrivFuncRelBean.size() != 0) {
                    result.put("message", "PrivFuncRelBean授权标识:" + privilege.getPrivId() + "还存在外键。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除数据
                privilegeMapper.deleteByPrimaryKey(privilege.getPrivId());
            }

            // 7.系统用户角色
            if (maps.containsKey("systemUserRole")) {
                SystemUserRole systemUserRole = JSON.parseObject(maps.get("systemUserRole").toString(), SystemUserRole.class);
                SystemUserRole systemUserRoleBean = systemUserRoleMapper.selectByPrimaryKey(systemUserRole.getSysUserRoleId());
                if (systemUserRoleBean == null) {
                    result.put("message", "systemUserRole系统用户角色标识:" + systemUserRole.getSysUserRoleId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除数据
                systemUserRoleMapper.deleteByPrimaryKey(systemUserRole.getSysUserRoleId());
            }

            // 6.系统岗位对应角色
            if (maps.containsKey("systemPostRole")) {
                SystemPostRole systemPostRole = JSON.parseObject(maps.get("systemPostRole").toString(), SystemPostRole.class);
                SystemPostRole systemPostRoleBean = systemPostRoleMapper.selectByPrimaryKey(systemPostRole.getSysPostRoleId());
                if (systemPostRoleBean == null) {
                    result.put("message", "systemPostRole系统岗位对应角色标识:" + systemPostRole.getSysPostRoleId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除数据
                systemPostRoleMapper.deleteByPrimaryKey(systemPostRole.getSysPostRoleId());
            }

            // 5.系统角色
            if (maps.containsKey("systemRoles")) {
                SystemRoles systemRoles = JSON.parseObject(maps.get("systemRoles").toString(), SystemRoles.class);
                SystemRoles systemRolesBean = systemRolesMapper.selectByPrimaryKey(systemRoles.getSysRoleId());
                if (systemRolesBean == null) {
                    result.put("message", "systemRoles系统角色标识:" + systemRoles.getSysRoleId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }


                // 查询系统用户角色表里面的数据是否已经删除
                List<SystemPostRole> systemPostRoles = systemPostRoleMapper.selectByRoleId(systemRoles.getSysRoleId());
                if (systemPostRoles.size() != 0) {
                    result.put("message", "systemRoles系统角色标识:" + systemRoles.getSysRoleId() + "还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception();
                }

                // 查询系统岗位对应角色表中的数据是否删除
                List<SystemUserRole> systemUserRoles = systemUserRoleMapper.selectBySysRoleId(systemRoles.getSysRoleId());
                if (systemUserRoles.size() != 0) {
                    result.put("message", "systemRoles系统角色标识:" + systemRoles.getSysRoleId() + "还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 删除数据
                systemRolesMapper.deleteByPrimaryKey(systemRoles.getSysRoleId());
            }

            // 4.系统用户任职岗位
            if (maps.containsKey("systemUserPost")) {
                SystemUserPost systemUserPost = JSON.parseObject(maps.get("systemUserPost").toString(), SystemUserPost.class);
                SystemUserPost systemUserPostBean = systemUserPostMapper.selectByPrimaryKey(systemUserPost.getSysUserPostId());
                if (systemUserPostBean == null) {
                    result.put("message", "systemUserPost系统用户任职岗位标识:" + systemUserPost.getSysUserPostId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 删除操作
                systemUserPostMapper.deleteByPrimaryKey(systemUserPost.getSysUserPostId());
            }

            // 3.系统岗位
            if (maps.containsKey("systemPost")) {
                SystemPost systemPost = JSON.parseObject(maps.get("systemPost").toString(), SystemPost.class);
                // 如果数据库中，不存在一条数据，但是传过来的类型又要我们更新，则提示不存在该数据，无法更新
                SystemPost systemPostBean = systemPostMapper.selectByPrimaryKey(systemPost.getSysPostId());
                if (systemPostBean == null) {
                    result.put("message", "systemPost系统岗位标识为:" + systemPost.getSysPostId() + "的数据不存在。无法执行删除操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 查询系统岗位对应角色表中的数据是否删除
                List<SystemUserPost> systemUserPosts = systemUserPostMapper.selectBySysPostId(systemPost.getSysPostId());
                if (systemUserPosts.size() != 0) {
                    result.put("message", "systemPost系统岗位标识为:" + systemPost.getSysPostId() + "还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 查询系统岗位对应角色表中的数据是否删除
                List<SystemPostRole> systemPostRoles = systemPostRoleMapper.selectBySysPortId(systemPost.getSysPostId());
                if (systemPostRoles.size() != 0) {
                    result.put("message", "systemPost系统岗位标识为:" + systemPost.getSysPostId() + "SYSTEM_POST_ROLE还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                // 保存数据
                systemPostMapper.deleteByPrimaryKey(systemPost.getSysPostId());
            }

            // 2.系统用户
            if (maps.containsKey("systemUser")) {
                SystemUser systemUser = JSON.parseObject(maps.get("systemUser").toString(), SystemUser.class);
                // 数据为新增数据
                // 如果数据库中，不存在一条数据，但是传过来的类型又要我们删除，提示报错
                SystemUser systemUserBean = systemUserMapper.selectByPrimaryKey(systemUser.getSysUserId());
                if (systemUserBean == null) {
                    result.put("message", "systemUser系统用户标识为:" + systemUser.getSysUserId() + "的数据不存在。无法执行更新操作，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 判断SystemUserPost是否还有未删除的外键
                List<SystemUserPost> systemUserPostBeans = systemUserPostMapper.selectBySysUserId(systemUser.getSysUserId());
                if (systemUserPostBeans.size() != 0) {
                    result.put("message", "systemUser系统用户标识为:" + systemUser.getSysUserId() + "system_user_post还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 判断SystemUserPost是否还有未删除的外键
                List<SystemUserRole> systemUserRoleBeans = systemUserRoleMapper.selectBySysUserId(systemUser.getSysUserId());
                if (systemUserRoleBeans.size() != 0) {
                    result.put("message", "systemUser系统用户标识为:" + systemUser.getSysUserId() + "system_user_role还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                systemUserMapper.deleteByPrimaryKey(systemUser.getSysUserId());
            }

            // 1.员工
            if (maps.containsKey("staff")) {
                Staff staff = JSON.parseObject(maps.get("staff").toString(), Staff.class);
                // 如果Staff表中，不存在StaffId，则提示无法进行数据删除
                Staff staffBean = staffMapper.selectByPrimaryKey(staff.getStaffId());
                if (staffBean == null) {
                    result.put("message", staff.getStaffId() + "在Staff表中不存在，无法进行数据删除");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }

                // 判断SystemUserPost是否还有未删除的外键
                List<SystemUser> systemUserBeans = systemUserMapper.selectByStaffId(staff.getStaffId());
                if (systemUserBeans.size() != 0) {
                    result.put("message", "systemUser系统用户标识为:" + staff.getStaffId() + "system_user还存在外键未删除，请确认数据是否有误");
                    logger.info(result.get("message"));
                    throw new Exception((String) result.get("message"));
                }
                staffMapper.deleteByPrimaryKey(staff.getStaffId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "异常错误：" + e.getMessage()!=null&&e.getMessage().length()>256?e.getMessage().substring(0,256):e.getMessage());
            throw new Exception((String) result.get("message"));
        }
    }
}*/
