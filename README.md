# sso-cas-demo

This project demo the sso feature using CAS.

For CAS documentation see http://jasig.github.io/cas/4.0.x/index.html

Default in-memory credential for CAS server is [casuser/Mellon]

For facebook authentication, a facebook application (key, secret) is required on behalf of which our cas-demo-server will request authentication of the user.
for creating facebook app, use this link https://developers.facebook.com/apps

After creating the app, go to advance setting and add cas-demo-server URL in Valid OAuth Redirect URI list.


For database authentication, all tables from database-script.sql file has to be created