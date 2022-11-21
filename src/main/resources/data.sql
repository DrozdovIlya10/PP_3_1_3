insert into t_role(id, name)
values (1, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
insert into t_role(id, name)
values (2, 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_ADMIN';
insert into t_user(id, username, password)
values (1, 'user', '$2y$10$1hejPrtewmgD7b5wK40sVeZTPiaG9IjuUfYE8PU2F45IffQKfzesK')
ON DUPLICATE KEY UPDATE username = 'user';
insert into t_user(id, username, password)
values (2, 'admin', '$2y$10$JpsugMd57vRcNSgc6CVzyuelsID9gZXtA7ESWg1w5ifp2i40ihVb.')
ON DUPLICATE KEY UPDATE username = 'admin';
insert into t_user_t_role (User_id, roles_id)
VALUES (1, 1)
ON DUPLICATE KEY UPDATE User_id = 1;
insert into t_user_t_role (User_id, roles_id)
VALUES (2, 2)
ON DUPLICATE KEY UPDATE User_id = 2;
insert into t_user_t_role (User_id, roles_id)
VALUES (2, 1)
ON DUPLICATE KEY UPDATE User_id  = 2,
                        roles_id = 1;
update hibernate_sequence
set next_val = 3
where next_val = 1;


