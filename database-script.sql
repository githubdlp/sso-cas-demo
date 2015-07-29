CREATE TABLE `users` (
`username` varchar(50) NOT NULL,
`password` varchar(50) NOT NULL,
`active` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--password is bydefault MD5 encoded
 
CREATE TABLE `user_data` (
`uid` varchar(50) NOT NULL,
`first_name` varchar(50) NOT NULL,
`last_name` varchar(50) NOT NULL,
`email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;