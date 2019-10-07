-- 查询：系统-模块
select * from func_module ;
-- 查询：系统-菜单
select * from func_menu ;
-- 查询：系统-功能
select * from func_action ;

-- 查询：系统-权限
select * from info_role ;
-- 查询：权限-模块-关系
select * from info_role_module ;
-- 查询：权限-菜单-关系
select * from info_role_menu ;
-- 查询：权限-功能-关系
select * from info_role_action ;

-- 查询：用户-模块-关系
select * from info_user_module ;
-- 查询：用户-菜单-关系
select * from info_user_menu ;
-- 查询：用户-功能-关系
select * from info_user_action ;

-- 查询：公司信息
select * from info_enterprise ;
-- 查询：用户信息
select * from info_user ;