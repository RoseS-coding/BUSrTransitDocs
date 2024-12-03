CREATE TABLE messages (
	send_user int, 
    receive_user int, 
    message TEXT,
    category VARCHAR(50),
    FOREIGN KEY (send_user) references users(user_id),
    foreign key (receive_user) references users(user_id),
    primary key (send_user, receive_user)
);