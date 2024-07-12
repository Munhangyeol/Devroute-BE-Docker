INSERT INTO company (name, logo_url, click_count, date_created, date_updated)
VALUES ('Company A', 'http://logoA.com', 100, '2024/07/11 12:12:00', '2024/07/11 12:12:00'),
       ('Company B', 'http://logoB.com', 200, '2024/07/11 12:13:00', '2024/07/11 12:13:00'),
       ('Company C', 'http://logoC.com', 300, '2024/07/11 12:14:00', '2024/07/11 12:14:00');

INSERT INTO user (development_field, email,password,name,login_type, user_role)
    VALUE ('AI', 'abc@email.com', '1234', 'user', 'NORMAL', 'USER');
INSERT INTO user (development_field, email,password,name,login_type, user_role)
    VALUE ('BACKEND', 'abc@email.com', '1234', 'user', 'NORMAL', 'ADMIN');