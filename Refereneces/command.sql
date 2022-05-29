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

insert into kiwidb.reviews values (null, 'korean123', 1, '이 영화는 명작이다.', 100, now());
insert into kiwidb.reviews values (null, 'macIsDdong', 2, '이 영화는 쓰레기다', 14, now());
insert into kiwidb.reviews values (null, 'kimmijun123', 2, '로망스의 끝', 87, now());
insert into kiwidb.reviews values (null, 'kimmijun123', 3, '누군가는 해야 한다.', 56, now());

-- movie id에 대한 리뷰 정보를 가져온다.
select count(freshRate) as 'count', sum(freshRate) as 'sum'
from kiwidb.reviews
group by movie_id
having movie_id = 5;

-- movie rate update
update kiwidb.movies set freshrate = ? where movie_id = ?;

insert into kiwidb.bookmarks values ('korean123', 3);
insert into kiwidb.bookmarks values ('korean123', 4);
insert into kiwidb.bookmarks values ('korean123', 6);

-- 점수 동기화 함수
DELIMITER $$
create procedure synchronizeFreshRate()
BEGIN 
	declare movieCount int default 1;
    declare movieId int;
    declare reviewCount double;
    declare reviewSum double;
    declare reviewFresh double;
    declare finishFresh int;
    select count(id) into movieCount from kiwidb.movies;
    while (movieCount > 0) DO
		-- 무비 아이디 가져오기
        select id into movieId from kiwidb.movies where id = movieCount;
        
        -- 무비 아이디를 기준으로 총점, 개수 구하기
		select count(freshRate) as 'count' into reviewCount
		from kiwidb.reviews
		group by movie_id
		having movie_id = movieId;
        
        select sum(freshRate) as 'sum' into reviewSum
		from kiwidb.reviews
		group by movie_id
		having movie_id = movieId;
        -- (총점 / 개수 * 100) * 100 계산해서 다시 무비 아이디에 값 넣어주기
		set reviewFresh = (reviewSum / (reviewCount * 100)) * 100;
        
		update kiwidb.movies set freshRate = reviewFresh where id = movieId;
        
		set movieCount = movieCount - 1;
    END WHILE;
END$$
DELIMITER ;

call synchronizeFreshRate();

select * from movies;



