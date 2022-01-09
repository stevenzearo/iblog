-- function assert_varchar_exist
delimiter $$
drop procedure if exists assert_varchar_exist;
create procedure assert_varchar_exist(varchar_name varchar(50), varchar_value varchar(50))
    deterministic
begin
    declare msg varchar(100);
    set msg = concat(varchar_name, ' can not be null');
    if varchar_value is null then
        signal sqlstate '40100' set message_text = msg;
    end if;
end $$
delimiter ;
-- function assert_int_exist
delimiter $$
drop procedure if exists assert_int_exist;
create procedure assert_int_exist(int_name varchar(50), int_value int)
    deterministic
begin
    declare msg varchar(100);
    set msg = concat(int_name, ' can not be null');
    if int_value is null then
        signal sqlstate '40101' set message_text = msg;
    end if;
end $$
delimiter ;
-- procedure mock_groups
delimiter $$
drop procedure if exists mock_groups;
create procedure mock_groups(out mocked_count int,
                             in id_sufficient varchar(25),
                             in name_sufficient varchar(25),
                             in created_by varchar(50),
                             in mock_count int)
begin
    declare i int default 1;

    call assert_varchar_exist('id_sufficient', id_sufficient);
    call assert_varchar_exist('name_sufficient', name_sufficient);
    call assert_varchar_exist('created_by', created_by);
    call assert_int_exist('mock_count', mock_count);

    while i <= mock_count do
            insert into `groups` values (concat(id_sufficient, i), concat(name_sufficient, i), created_by, current_timestamp(6));
            set mocked_count = i;
            set i = i + 1;
        end while;
end $$
delimiter ;
