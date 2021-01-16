create table label (id bigint not null auto_increment, color varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table user (id bigint not null auto_increment, email varchar(255), name varchar(255), password varchar(255), surname varchar(255), primary key (id)) engine=InnoDB;
create table workplace (id bigint not null auto_increment, admin_id bigint, background_color varchar(255), description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table workplace_label (label_id bigint, workplace_id bigint not null, primary key (workplace_id)) engine=InnoDB;
create table workplace_user (user_id bigint not null, workplace_id bigint not null) engine=InnoDB;
alter table workplace_label add constraint FKcftikubqwb8tg91b3ek791ecl foreign key (label_id) references workplace (id);
alter table workplace_label add constraint FKlwpmxk1on70q9a3atkjpop056 foreign key (workplace_id) references label (id);
alter table workplace_user add constraint FKm9h888v8tm2juhlirbrd7eqhx foreign key (workplace_id) references workplace (id);
alter table workplace_user add constraint FKgi44hhixynqkd9xxebwnh82rk foreign key (user_id) references user (id);
