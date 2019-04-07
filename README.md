# MongoREST
Spring boot project with REST API and MongoDB

User entity consists of following fields:
 name - String ;
 address - String ;
 email – String ;
 deleted - boolean ;

The REST API has following methods:
1. Post: /user/add – adds new user object
2. Get: /user – retrieves lists of all users
3. Get: /user/{email} – gets the user object by the email – only users which are not marked as deleted
ones can be returned. In case the user does not exist or is marked as deleted response code 404 is returned
4. Delete: /user/{email} – marks the user as a deleted one .
