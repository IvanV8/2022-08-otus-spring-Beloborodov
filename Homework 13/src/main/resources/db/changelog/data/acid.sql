INSERT INTO acl_sid (id, principal, sid)
VALUES (1, 0, 'ROLE_ADMIN'),
       (2, 0, 'ROLE_USER'),
       (3, 0, 'ROLE_CHILD');



INSERT INTO acl_class (id, class)
VALUES (1, 'ru.otus.spring082022.homework13.domain.Book'),
       (2, 'ru.otus.spring082022.homework13.domain.Comment');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
VALUES (1, 1, 1, NULL, 1, 0),
       (2, 1, 2, NULL, 1, 0),
       (3, 2, 1, NULL, 1, 0);

