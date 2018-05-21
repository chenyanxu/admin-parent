DELETE FROM "public"."middleware_oauth_client";
DELETE FROM "public"."middleware_oauth_user";
INSERT INTO "public"."middleware_oauth_client" ("id", "createby", "createbyid", "creationdate", "updateby", "updatebyid", "updatedate", "clientid", "clientname", "clientsecret", "version_") VALUES ('1', NULL, NULL, NULL, NULL, NULL, NULL, 'c1ebe466-1cdc-4bd3-ab69-77c3561b9dee', 'chapter17-client', 'd8346ea2-6017-43ed-ad68-19c0f971738b', '1');
INSERT INTO "public"."middleware_oauth_user" ("id", "createby", "createbyid", "creationdate", "updateby", "updatebyid", "updatedate", "password", "salt", "username", "version_") VALUES ('5304', NULL, NULL, '2017-04-19 16:33:52.996', NULL, NULL, '2017-04-19 16:33:52.995', '19780abeb4be11c36a6497fd6106b9a1', '608931499fdfaf771aff161c272b24e7', 'admin', '1');
