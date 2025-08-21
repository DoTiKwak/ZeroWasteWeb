delete from member where member_id = 'testID';

drop table member;
SET foreign_key_checks = 1;

select member_id
from member
where member_name = 'lee' and member_phone_number = '01012345678';

UPDATE member SET member_password= '1111' where member_id = 'lee';

create table persistent_logins(
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null);

INSERT INTO member (member_id, member_name, member_phone_number, member_email, member_password, member_address, member_is_activate, member_is_social_activate)
    VALUE ('testID', 'testID' , '000', 'testID@com', '1111', 'aa', true, false);

SELECT TABLE_NAME, CONSTRAINT_NAME, COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE REFERENCED_TABLE_NAME = 'board'
  AND REFERENCED_TABLE_SCHEMA = 'czo3';
