-- groups
insert ignore into `groups` values('group-0001', 'super-admin', 'init-script', current_timestamp(6));
insert ignore into `groups` values('group-0002', 'bo-admin', 'init-script', current_timestamp(6));
-- super-admin roles
insert ignore into roles values('role-0001', 'group-0001', 'super-admin-role', 'ALL', 'init-script', current_timestamp(6));
insert ignore into roles values('role-0002', 'group-0001', 'bo-admin-role', 'BO_SITE', 'init-script', current_timestamp(6));
insert ignore into roles values('role-0003', 'group-0001', 'front-admin-role', 'FRONT_SITE', 'init-script', current_timestamp(6));
-- bo-admin roles
insert ignore into roles values('role-0004', 'group-0002', 'bo-admin-role', 'BO_SITE', 'init-script', current_timestamp(6));
-- check group existence trigger before insert admins
drop trigger if exists check_group_existence;
delimiter $$
create trigger check_group_existence before insert on admins
    for each row
    begin
        declare existence boolean default false;
        select count(1) > 0 into existence from `groups` where `groups`.id = NEW.group_id limit 1;
        if not existence then
            signal sqlstate '40400' set message_text = 'group not found';
        end if;
    end $$
delimiter ;
-- check role existence trigger before insert roles
drop trigger if exists check_role_existence;
delimiter $$
create trigger check_role_existence before insert on roles
    for each row
begin
    declare existence boolean default false;
    select count(1) > 0 into existence from `groups` where `groups`.id = NEW.group_id limit 1;
    if not existence then
        signal sqlstate '40400' set message_text = 'group not found';
    end if;
end $$
delimiter ;
