INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(1, 'gary101@google.com', 'gary', 'gary gary101', 'gary101');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(2, 'gary102@google.com', 'gary', 'gary gary102', 'gary102');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(3, 'gary103@google.com', 'gary', 'gary gary103', 'gary103');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(4, 'gary104@google.com', 'gary', 'gary gary104', 'gary104');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(5, 'gary105@google.com', 'gary', 'gary gary105', 'gary105');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(6, 'gary106@google.com', 'gary', 'gary gary106', 'gary106');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(7, 'gary107@google.com', 'gary', 'gary gary107', 'gary107');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(8, 'gary108@google.com', 'gary', 'gary gary108', 'gary108');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(9, 'gary109@google.com', 'gary', 'gary gary109', 'gary109');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(10, 'gary110@google.com', 'gary', 'gary gary110', 'gary110');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(11, NULL, 'gary', 'gary gary111', 'gary111');
INSERT INTO public.author
(id, email_address, first_name, full_name, second_name)
VALUES(12, '', 'gary', 'gary gary112', 'gary112');


INSERT INTO public.post
(id, "header", "name", publish_date, rating, "text", author_id)
VALUES(1, 'header1', 'name1', '2023-05-20 16:01:35.464', 0, 'text post1', 1);
INSERT INTO public.post
(id, "header", "name", publish_date, rating, "text", author_id)
VALUES(2, 'header2', 'name2', '2023-05-20 16:01:35.483', 845, 'text post2', 1);
INSERT INTO public.post
(id, "header", "name", publish_date, rating, "text", author_id)
VALUES(3, 'header3', 'name3', '2023-05-20 16:01:35.485', -43, 'text post3', 8);
INSERT INTO public.post
(id, "header", "name", publish_date, rating, "text", author_id)
VALUES(4, 'header4', 'name4', '2023-05-20 16:01:35.488', 6, 'text post4', 12);


ALTER SEQUENCE author_id_seq RESTART with 13;
ALTER SEQUENCE post_id_seq RESTART with 5;