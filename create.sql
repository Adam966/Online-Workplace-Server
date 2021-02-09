alter table checklist drop foreign key FKjc1jpts9w7r9l0lbhqp3i60x7
alter table checklist_label drop foreign key FKpphf1cptc3i34749c7c1pog1i
alter table checklist_label drop foreign key FKq2n6ctd9cc7ovjsyac5wijhod
alter table checklist_task drop foreign key FKfn3eh9xnv9f4xl2mjesd66qjb
alter table checklist_task drop foreign key FKjxt6a9nka2e03fjj6fq7cabrs
alter table note drop foreign key FK76gwlqx5mcdb556o1hiq7py7c
alter table note_label drop foreign key FKkyh9y1lmtwb4o5lo7hak0cb3c
alter table note_label drop foreign key FK88cg0ldmwcnfrj5vflj0fcxb8
alter table note_user drop foreign key FKf6g6i302yjqianvtmgylt7ut2
alter table note_user drop foreign key FK9g6sg7dfjsrua1tum0sam9wu8
alter table task_user drop foreign key FKpso32p8jmihunxmbxv4gpbodx
alter table task_user drop foreign key FKtp41ta1l93bm4u5gknn29xq46
alter table thread drop foreign key FK9b14quxy6pc5wpforc03bcyry
alter table thread_label drop foreign key FK8vgdjxwm5b5wdg2x0rv0l8e2l
alter table thread_label drop foreign key FKo6681ixxvm30w6i131iglffo3
alter table thread_user drop foreign key FK32g756jo17q1hrbrvled2c3rl
alter table thread_user drop foreign key FK9rrj8i7fkug2k5bhgwvpy2g1a
alter table workplace_label drop foreign key FK6898e4d2034vjucqsh5lrjpx4
alter table workplace_label drop foreign key FK3f9edq9uqvoypsjlntf2extij
alter table workplace_user drop foreign key FKm9h888v8tm2juhlirbrd7eqhx
alter table workplace_user drop foreign key FKgi44hhixynqkd9xxebwnh82rk
drop table if exists checklist
drop table if exists checklist_label
drop table if exists checklist_task
drop table if exists label
drop table if exists note
drop table if exists note_label
drop table if exists note_user
drop table if exists task
drop table if exists task_user
drop table if exists thread
drop table if exists thread_label
drop table if exists thread_user
drop table if exists user
drop table if exists workplace
drop table if exists workplace_label
drop table if exists workplace_photo
drop table if exists workplace_user
create table checklist (id bigint not null auto_increment, creation_time datetime(6), is_archived bit not null, name varchar(255), workplace_entity_id bigint, primary key (id)) engine=InnoDB
create table checklist_label (checklist_id bigint not null, thread_id bigint not null) engine=InnoDB
create table checklist_task (checklist_id bigint not null, task_id bigint not null) engine=InnoDB
create table label (id bigint not null auto_increment, color varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table note (id bigint not null auto_increment, creation_time datetime(6), is_archived bit not null, name varchar(255), description varchar(255), due_date datetime(6), workplace_entity_id bigint, primary key (id)) engine=InnoDB
create table note_label (note_id bigint not null, label_id bigint not null) engine=InnoDB
create table note_user (note_id bigint not null, user_id bigint not null) engine=InnoDB
create table task (id bigint not null auto_increment, description varchar(255), due_date datetime(6), primary key (id)) engine=InnoDB
create table task_user (user_id bigint not null, task_id bigint not null) engine=InnoDB
create table thread (id bigint not null auto_increment, creation_time datetime(6), is_archived bit not null, name varchar(255), description varchar(255), workplace_entity_id bigint, primary key (id)) engine=InnoDB
create table thread_label (label_id bigint not null, thread_id bigint not null) engine=InnoDB
create table thread_user (user_id bigint not null, thread_id bigint not null) engine=InnoDB
create table user (id bigint not null auto_increment, email varchar(255), name varchar(255), password varchar(255), photo bigint, surname varchar(255), primary key (id)) engine=InnoDB
create table workplace (id bigint not null auto_increment, admin_id bigint, background_color varchar(255), description varchar(255), name varchar(255), photo bigint, primary key (id)) engine=InnoDB
create table workplace_label (workplace_id bigint, label_id bigint not null, primary key (label_id)) engine=InnoDB
create table workplace_photo (id bigint not null auto_increment, picture longblob, primary key (id)) engine=InnoDB
create table workplace_user (user_id bigint not null, workplace_id bigint not null) engine=InnoDB
alter table checklist_task add constraint UK_q3exo5sijd615hgchmqxl4q1a unique (task_id)
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table checklist add constraint FKjc1jpts9w7r9l0lbhqp3i60x7 foreign key (workplace_entity_id) references workplace (id)
alter table checklist_label add constraint FKpphf1cptc3i34749c7c1pog1i foreign key (thread_id) references label (id)
alter table checklist_label add constraint FKq2n6ctd9cc7ovjsyac5wijhod foreign key (checklist_id) references checklist (id)
alter table checklist_task add constraint FKfn3eh9xnv9f4xl2mjesd66qjb foreign key (task_id) references task (id)
alter table checklist_task add constraint FKjxt6a9nka2e03fjj6fq7cabrs foreign key (checklist_id) references checklist (id)
alter table note add constraint FK76gwlqx5mcdb556o1hiq7py7c foreign key (workplace_entity_id) references workplace (id)
alter table note_label add constraint FKkyh9y1lmtwb4o5lo7hak0cb3c foreign key (label_id) references label (id)
alter table note_label add constraint FK88cg0ldmwcnfrj5vflj0fcxb8 foreign key (note_id) references note (id)
alter table note_user add constraint FKf6g6i302yjqianvtmgylt7ut2 foreign key (user_id) references user (id)
alter table note_user add constraint FK9g6sg7dfjsrua1tum0sam9wu8 foreign key (note_id) references note (id)
alter table task_user add constraint FKpso32p8jmihunxmbxv4gpbodx foreign key (task_id) references user (id)
alter table task_user add constraint FKtp41ta1l93bm4u5gknn29xq46 foreign key (user_id) references task (id)
alter table thread add constraint FK9b14quxy6pc5wpforc03bcyry foreign key (workplace_entity_id) references workplace (id)
alter table thread_label add constraint FK8vgdjxwm5b5wdg2x0rv0l8e2l foreign key (thread_id) references label (id)
alter table thread_label add constraint FKo6681ixxvm30w6i131iglffo3 foreign key (label_id) references thread (id)
alter table thread_user add constraint FK32g756jo17q1hrbrvled2c3rl foreign key (thread_id) references user (id)
alter table thread_user add constraint FK9rrj8i7fkug2k5bhgwvpy2g1a foreign key (user_id) references thread (id)
alter table workplace_label add constraint FK6898e4d2034vjucqsh5lrjpx4 foreign key (workplace_id) references workplace (id)
alter table workplace_label add constraint FK3f9edq9uqvoypsjlntf2extij foreign key (label_id) references label (id)
alter table workplace_user add constraint FKm9h888v8tm2juhlirbrd7eqhx foreign key (workplace_id) references workplace (id)
alter table workplace_user add constraint FKgi44hhixynqkd9xxebwnh82rk foreign key (user_id) references user (id)
