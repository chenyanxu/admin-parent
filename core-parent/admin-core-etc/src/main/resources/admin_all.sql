DELETE FROM public.sys_user;
INSERT INTO "public"."sys_user" ("id", "createby", "createbyid", "creationdate", "updateby", "updatebyid", "updatedate", "available", "email", "icon", "logindate", "loginip", "loginname", "mobile", "name", "password", "phone", "sex", "code", "position", "usertype", "version_") VALUES ('-2', '系统', NULL, '2016-08-04 15:13:54.808', '系统', NULL, '2016-08-04 15:13:54.808', '1', NULL, NULL, NULL, '0:0:0:0:0:0:0:1', 'qwer', NULL, '操作员', 'ICy5YqxZB1uWSwcVLSNLcA==', NULL, NULL, '130', NULL, '-1', NULL);
INSERT INTO "public"."sys_user" ("id", "createby", "createbyid", "creationdate", "updateby", "updatebyid", "updatedate", "available", "email", "icon", "logindate", "loginip", "loginname", "mobile", "name", "password", "phone", "sex", "code", "position", "usertype", "version_") VALUES ('-1', '系统', NULL, NULL, '系统', NULL, NULL, '1', NULL, NULL, '2017-11-17 11:21:21.082', '0:0:0:0:0:0:0:1', 'admin', NULL, '管理员', 'ICy5YqxZB1uWSwcVLSNLcA==', NULL, NULL, NULL, NULL, '-1', NULL);

DELETE FROM public.sys_role;
INSERT INTO public.sys_role (id, createby, creationdate, updateby, updatedate, app, name, remark, version_) VALUES (1, '管理员', '2015-11-17 02:32:42.692000', '管理员', '2015-11-17 02:32:42.707000', '系统应用', '超级管理员', '系统应用', 1);

DELETE FROM public.sys_role_user;
INSERT INTO public.sys_role_user (id, createby, creationdate, updateby, updatedate, roleid, userid, version_) VALUES (1, '管理员', '2015-11-17 01:52:55.053000', '管理员', '2015-11-17 01:52:55.053000', 1, -1, 1);

DELETE FROM public.sys_organization_user;
INSERT INTO public.sys_organization_user (id, createby, creationdate, updateby, updatedate, orgid, userid, version_) VALUES (1, '管理员', '2016-08-09 13:09:00.000000', '管理员', '2016-08-09 13:09:00.000000', 1, -1, 1);

--工作组初始化
DELETE FROM public.sys_workgroup;
INSERT INTO public.sys_workgroup (id, createby, creationdate, updateby, updatedate, app, name, remark, version_) VALUES (1, '管理员', '2016-08-09 15:13:13.033000', '管理员', '2016-08-09 15:13:13.033000', '系统应用', '系统管理工作组', '最高权限工作组', 1);

DELETE FROM public.sys_workgroup_user;
INSERT INTO public.sys_workgroup_user (id, createby, creationdate, updateby, updatedate, groupid, userid, version_) VALUES (1, '管理员', '2016-08-09 15:13:38.028000', '管理员', '2016-08-09 15:13:38.028000', 1, -1, 1);

DELETE FROM public.sys_workgroup_role;
INSERT INTO public.sys_workgroup_role (id, createby, creationdate, updateby, updatedate, groupid, roleid, version_) VALUES (1, '管理员', '2016-08-09 15:13:38.028000', '管理员', '2016-08-09 15:13:38.028000', 1, 1, 1);

--DELETE FROM public.sys_role_application;
--DELETE FROM public.sys_role_function;

--INSERT INTO public.sys_role_application VALUES ('1', '管理员', '2015-11-17 00:11:36.699', '管理员', '2015-11-17 00:11:36.699', '0', '1', '1');
--INSERT INTO public.sys_role_application VALUES ('2', '管理员', '2016-06-03 16:28:40.754', '管理员', '2016-06-03 16:28:40.754', '0', '2', '1');
--INSERT INTO public.sys_role_application VALUES ('3', '管理员', '2016-06-03 16:28:40.754', '管理员', '2016-06-03 16:28:40.754', '0', '3', '1');
--INSERT INTO public.sys_role_function VALUES ('1', '管理员', '2015-11-17 00:11:36.703', '管理员', '2015-11-17 00:11:36.703', '0', '1', '1');