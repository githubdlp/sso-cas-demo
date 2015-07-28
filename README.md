# sso-cas-demo

This project demo the sso feature using CAS.

For CAS documentation see http://jasig.github.io/cas/4.0.x/index.html

Default in-memory credential for CAS server is [casuser/Mellon]

For facebook authentication, a facebook application (key, secret) is required on behalf of which our cas-demo-server will request authentication of the user.
for creating facebook app, use this link https://developers.facebook.com/apps

After creating the app, go to advance setting and add cas-demo-server URL in Valid OAuth Redirect URI list.


For database authentication, Following tables should be present

CREATE TABLE `users` (
`username` varchar(50) NOT NULL,
`password` varchar(50) NOT NULL,
`active` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

password is bydefault MD5 encoded
 
CREATE TABLE `user_data` (
`uid` varchar(50) NOT NULL,
`first_name` varchar(50) NOT NULL,
`last_name` varchar(50) NOT NULL,
`email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;