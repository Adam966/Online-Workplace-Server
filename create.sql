alter table checklist_task drop foreign key FKfn3eh9xnv9f4xl2mjesd66qjb
alter table checklist_task drop foreign key FKc5uwq6tajr00on7kyxsfhxxre
alter table element_label drop foreign key FKn7fybwujxv4jjutw0qxwprg2j
alter table element_label drop foreign key FK8es27ub5hf98odfgn5f3rusoy
alter table element_user drop foreign key FKdnocsxly6gidy5vd4j4u5w4lr
alter table element_user drop foreign key FKq5xv7tvaeonsre1wqysal6ii9
alter table notification drop foreign key FKgtksickis1kjl98281hxsqsc0
alter table notification drop foreign key FKpgwrwgy6jhyjmhba4scyk9ug9
alter table notification drop foreign key FKi5oqlq9byodr5ki8mrdgqdma3
alter table notification_rights_entity drop foreign key FKh6v28g16nkhu32ybx4l72gglo
alter table notification_rights_entity drop foreign key FKh2hq9sqmxnt8gwlxnmp3108c6
alter table task_user drop foreign key FKpso32p8jmihunxmbxv4gpbodx
alter table task_user drop foreign key FKtp41ta1l93bm4u5gknn29xq46
alter table user_rights drop foreign key FKdk6ke3rj91dvj8ulac7eh0bsj
alter table user_rights drop foreign key FK3efts2se656yuhg43w3fmvclo
alter table workplace_label drop foreign key FK6898e4d2034vjucqsh5lrjpx4
alter table workplace_label drop foreign key FK3f9edq9uqvoypsjlntf2extij
alter table workplace_user drop foreign key FKm9h888v8tm2juhlirbrd7eqhx
alter table workplace_user drop foreign key FKgi44hhixynqkd9xxebwnh82rk
alter table workplace_element_entity drop foreign key FK90id9xffa1bh3w0unxvjc1x0t
drop table if exists checklist_task
drop table if exists element_label
drop table if exists element_user
drop table if exists label
drop table if exists notification
drop table if exists notification_rights_entity
drop table if exists task
drop table if exists task_user
drop table if exists user
drop table if exists user_rights
drop table if exists workplace
drop table if exists workplace_label
drop table if exists workplace_photo
drop table if exists workplace_user
drop table if exists workplace_element_entity
create table checklist_task (checklist_id bigint not null, task_id bigint not null) engine=InnoDB
create table element_label (element_id bigint not null, label_id bigint not null) engine=InnoDB
create table element_user (element_id bigint not null, user_id bigint not null) engine=InnoDB
create table label (id bigint not null auto_increment, color varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table notification (id bigint not null auto_increment, creation_time datetime(6), description varchar(255), fresh bit not null, type integer, recipient_user_id bigint, sender_user_id bigint, workplace_entity_id bigint, primary key (id)) engine=InnoDB
create table notification_rights_entity (id bigint not null auto_increment, added_to_element bit not null, due_date bit not null, removed_from_element bit not null, sent_message bit not null, user_entity_id bigint, workplace_entity_id bigint, primary key (id)) engine=InnoDB
create table task (id bigint not null auto_increment, description varchar(255), is_completed bit not null, primary key (id)) engine=InnoDB
create table task_user (user_id bigint not null, task_id bigint not null) engine=InnoDB
create table user (id bigint not null auto_increment, email varchar(255), password varchar(255), photo bigint, user_name varchar(255), user_surname varchar(255), primary key (id)) engine=InnoDB
create table user_rights (id bigint not null auto_increment, add_to_workplace bit not null, archive_element bit not null, change_rights bit not null, remove_from_workplace bit not null, user_entity_id bigint, workplace_entity_id bigint, primary key (id)) engine=InnoDB
create table workplace (id bigint not null auto_increment, admin_id bigint, background_color varchar(255), color_of_element varchar(255), description varchar(255), name varchar(255), photo bigint, primary key (id)) engine=InnoDB
create table workplace_label (workplace_id bigint, label_id bigint not null, primary key (label_id)) engine=InnoDB
create table workplace_photo (id bigint not null auto_increment, picture longblob, primary key (id)) engine=InnoDB
create table workplace_user (user_id bigint not null, workplace_id bigint not null) engine=InnoDB
create table workplace_element_entity (type varchar(31) not null, id bigint not null auto_increment, creation_time datetime(6), is_archived bit not null, name varchar(255), description varchar(255), due_date datetime(6), workplace_entity_id bigint, primary key (id)) engine=InnoDB
alter table checklist_task add constraint UK_q3exo5sijd615hgchmqxl4q1a unique (task_id)
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table checklist_task add constraint FKfn3eh9xnv9f4xl2mjesd66qjb foreign key (task_id) references task (id)
alter table checklist_task add constraint FKc5uwq6tajr00on7kyxsfhxxre foreign key (checklist_id) references workplace_element_entity (id)
alter table element_label add constraint FKn7fybwujxv4jjutw0qxwprg2j foreign key (label_id) references label (id)
alter table element_label add constraint FK8es27ub5hf98odfgn5f3rusoy foreign key (element_id) references workplace_element_entity (id)
alter table element_user add constraint FKdnocsxly6gidy5vd4j4u5w4lr foreign key (user_id) references user (id)
alter table element_user add constraint FKq5xv7tvaeonsre1wqysal6ii9 foreign key (element_id) references workplace_element_entity (id)
alter table notification add constraint FKgtksickis1kjl98281hxsqsc0 foreign key (recipient_user_id) references user (id)
alter table notification add constraint FKpgwrwgy6jhyjmhba4scyk9ug9 foreign key (sender_user_id) references user (id)
alter table notification add constraint FKi5oqlq9byodr5ki8mrdgqdma3 foreign key (workplace_entity_id) references workplace (id)
alter table notification_rights_entity add constraint FKh6v28g16nkhu32ybx4l72gglo foreign key (user_entity_id) references user (id)
alter table notification_rights_entity add constraint FKh2hq9sqmxnt8gwlxnmp3108c6 foreign key (workplace_entity_id) references workplace (id)
alter table task_user add constraint FKpso32p8jmihunxmbxv4gpbodx foreign key (task_id) references user (id)
alter table task_user add constraint FKtp41ta1l93bm4u5gknn29xq46 foreign key (user_id) references task (id)
alter table user_rights add constraint FKdk6ke3rj91dvj8ulac7eh0bsj foreign key (user_entity_id) references user (id)
alter table user_rights add constraint FK3efts2se656yuhg43w3fmvclo foreign key (workplace_entity_id) references workplace (id)
alter table workplace_label add constraint FK6898e4d2034vjucqsh5lrjpx4 foreign key (workplace_id) references workplace (id)
alter table workplace_label add constraint FK3f9edq9uqvoypsjlntf2extij foreign key (label_id) references label (id)
alter table workplace_user add constraint FKm9h888v8tm2juhlirbrd7eqhx foreign key (workplace_id) references workplace (id)
alter table workplace_user add constraint FKgi44hhixynqkd9xxebwnh82rk foreign key (user_id) references user (id)
alter table workplace_element_entity add constraint FK90id9xffa1bh3w0unxvjc1x0t foreign key (workplace_entity_id) references workplace (id)
