-- insert admin
insert into profile(name,surname,password,email,role,created_date)
SELECT 'TOshmat','Toshatov','123','toshmat@mail.ru','ADMIN',now()
    WHERE
    NOT EXISTS (
        SELECT id FROM profile WHERE surname = 'Toshatov'
    );