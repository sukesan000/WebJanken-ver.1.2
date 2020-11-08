/* ユーザーマスタのデータ（ADMIN権限） */
INSERT INTO m_user (user_id, password, user_name, gender, role)
VALUES('yamada@xxx.co.jp', 'password', '山田太郎', true, 'ROLE_ADMIN');

/* ユーザーマスタのデータ（一般権限） */
INSERT INTO m_user (user_id, password, user_name, gender, role)
VALUES('tamura@xxx.co.jp', 'password', '田村', false, 'ROLE_GENERAL');