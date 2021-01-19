/*
use restocks;
drop table topic;
create table topic (
	topic_id int not null,
    topic_code varchar(10) not null,
    topic_name varchar(20) not null,
    PRIMARY KEY (topic_code)
)
*/

/*
use restocks;
drop table stock;
create table stock (
	topic_code varchar(10) not null,
    stock_code varchar(6) not null,
    PRIMARY KEY (topic_code,stock_code)
)
*/

/*
use restocks;
drop table stock_master;
create table stock_master (
    stock_code varchar(6) not null,
    stock_name varchar(100) not null,
    PRIMARY KEY (stock_code)
)
*/

/*
use restocks;
drop table stock_price;
create table stock_price (
    stock_code varchar(6) not null,
    stock_date varchar(8) not null,
    start_price int not null,
    high_price int not null,
    low_price int not null,
    end_price int not null,
    stock_qty int not null,
    PRIMARY KEY (stock_code,stock_date)
)
*/
