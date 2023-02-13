INSERT INTO acl_sid (id, principal, sid)
VALUES (1, 0, 'ADMIN'),
       (2, 0, 'USER'),
       (3, 0, 'CHILD');



INSERT INTO acl_class (id, class)
VALUES (1, 'ru.otus.spring082022.homework13.domain.Book'),
       (2, 'ru.otus.spring082022.homework13.domain.Comment');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
VALUES (1, 1, 1, NULL, 1, 0),
       (2, 1, 2, NULL, 1, 0),
       (3, 2, 1, NULL, 1, 0);


INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure)
VALUES (1, 1, 1, 1, 1, 1, 1, 1),
       (2, 2, 2, 1, 1, 1, 1, 1),
       (3, 3, 3, 1, 1, 1, 1, 1),
       (4, 1, 4, 1, 2, 1, 1, 1),
       (5, 2, 5, 1, 2, 1, 1, 1),
       (6, 3, 6, 1, 2, 1, 1, 1),
       (7, 1, 7, 2, 1, 1, 1, 1),
       (8, 2, 8, 2, 1, 1, 1, 1),
       (9, 3, 9, 2, 1, 1, 1, 1),
       (10, 1, 10, 3, 1, 1, 1, 1),
       (11, 2, 11, 3, 0, 1, 1, 1),
       (12, 3, 12, 3, 0, 1, 1, 1);

