use kiwidb;

create table kiwidb.Reviews (
	id int not null auto_increment,
	user_id varchar(20) not null,
    movie_id int not null,
	content varchar(200) not null,
    
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (movie_id) references movies (id)
);

create table kiwidb.Bookmarks (
	user_id varchar(20) not null,
    movie_id int not null,
    
    primary key (user_id, movie_id),
    foreign key (user_id) references Users (id),
    foreign key (movie_id) references Movies (id)
);