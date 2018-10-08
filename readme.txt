For users:
	EasyLink
This application allows you to shorten your links and name them by specified Id. Firstly
To run the program successfully it is necessary to have MySQL database server installed on your machine.
Database Username must be set to: "root" and password must be set to "abcd1234".
Start the database server on your machine.
User interface can be found entering "http://localhost:8080/" in your Web Browser address bar.
	Home page
Now you can see two input fields *Long URL* and *Short URL*.
In *Long URL* paste link that must be 'shortened'. In *Short URL*(maximum 1000 symbols) enter name to which *Long URL* will be redirected.
After filling both fields press button Shorten. And voila! Now you can access to your inappropriately humongous link through following
address : "http://localhost:8080/links/*Short URL*".
Few words about our frugal navigation bar.
Home button always returns you to homepage, whenever you are.
About button as you already see is our web page documentation.
Logically, Register button opens registration form. There you must enter your account name/nickname(maximum 100 symbols),
Email(maximum 100 symbols), Password(maximum 100 symbols & password must contain at least one uppercase symbol, lowercase symbol and number) + re-enter it.
Login button opens Login page, where if you are already registered, you can enter password and login to open homepage as concrete user.
****************************************************************************************************************************************************************
For developers/administrators:
1.Application (EasyLinkApplication.java)
Running this class we bootstrap and launch a Spring application from a Java main method.

2.URL (URL.java)
fields:
-private String id
-private String url
This class contains getters and setters for 2 fields (*id* - String,*full_url_address* - String)
and 2 constructors empty-URL() and URL(id,url).
We set *id* as this Entities priamry key.


3.Registration (Registration.java)
fields:
-private String username.
-private String password.
-private String email.
-private Set<URL> URLList.
This class contains getters and setters for 3 fields (*username* - String ,*password* - String ,*email* - String) 
and 2 constructors empty-Registration() and Registration(username,password,email).
We set *username* as this Entities priamry key. For *username* field we generate primary key values automatically.
For field URLList(Set<URL>) we create relation one to many with id from 'Registration' table and fetch immediately everything that we have in it.
And method addToList(URL<*id* - String, *full_url_address* - String>) it adds to URLList given in parameter URL object.


4.Login (Login.java)
fields:
-private String username;
-private String password;
This class contains getters and setters for 2 fields (*username* - String,*password* - String)
and empty constructor-Login().


5.Controller (EasyLinkController.java)
Controller handles HTTP requests. If you enter "http://localhost:8080/links/" in Web Browser address bar,
you will see what currently database stores.
Example:    (JSON)
{
..
{
"id": name for link given by user,
"full_url": links full address,
},
..
}
For our API, you can try tool, such as the Firefox RESTClient, or Chrome's POSTMAN to test the REST integration.
Using them you can check mapping for HTTP GET requests, add new urls in database with HTTP POST, or DELETE some records from database.
To GET, POST or DELETE something you must choose in corresponded request method in REST integration testing tool
and enter following address "http://localhost:8080/links/{id}" where {id} is needed links name.

5.1.EasylinkController (EasyLinkController.java)
(link(*id* - String,*full_url_address* - String), *httpServletResponse* - HttpServletResponse)
This REST controller create following mapping:
 - http://localhost:8080/links/
 --getAllLinks() method returns all available links(like mentioned above). 
 - http://localhost:8080/links/{id} (RequestMethod.GET)
 --getLink(@PathVariable *id*, *httpServletResponse*) this method redirects(*httpServletResponse*.setStatus(302))from entered address to the full url.  
 - http://localhost:8080/links/ (RequestMethod.POST)
 --addLink(@RequestBody *link*) inserts new record in 'EasyLink' table if there are no double entry or error.
 - http://localhost:8080/links/{id} (RequestMethod.DELETE)
 --deleteLink(@PathVariable *id*) delete from 'Easylink' table record with *id*.


5.2.RegistrationController (RegistrationController.java)
(link(*id* - String,*full_url_address* - String), registration(*username* - String, *password* - String, *email* - String, Set<URL>), *httpServletResponse* - HttpServletResponse, Login(*username* - String,*password* - String))
This REST controller create following mapping:
 - http://localhost:8080/register/
 --getAllRegistrations() method returns all registered users in a list. 
 - http://localhost:8080/user/{username} (RequestMethod.GET)
 --getLink(@PathVariable *username*, *httpServletResponse*) this method returns *registration*(fill it with this user shortened links) object for user with nickname - *username*.
 - http://localhost:8080/pass/{username} (RequestMethod.POST)
 --getCheckPassword(@RequestBody *login*, *httpServletResponse*, @PathVariable(value="username") *username*) check if entered password is correct for current *username*.
 - http://localhost:8080/{username}/links/ (RequestMethod.GET)
 --getRegistrationLinks(@PathVariable(value="username") *username*, *httpServletResponse*) Returns(set<URL>) all URL(*id*,*full_url_address*) objects from 'Easylink' table where their "owner" is *username*.
 - http://localhost:8080/{username}/links/{id} (RequestMethod.GET)
 --getRegistrationLink(@PathVariable(value="username") *username*, @PathVariable *id*, *httpServletResponse*) redirects user with *username* to page where shortened name is *id*.
 - http://localhost:8080/{username}/links/ (RequestMethod.POST)
 --addLink(@RequestBody *link*, @PathVariable(value = "username") *username*) inserts new shortened link object to *username*.
 - http://localhost:8080/{username}/links/{id} (RequestMethod.PATCH)
 --editLink(@RequestBody *link*, @PathVariable(value = "username") *username*, @PathVariable(value = "id") *id*) delete *link* with *id* for *username* and creates new *link* object for *username* with same *id*.
 - http://localhost:8080/{username}/register/ (RequestMethod.POST)
 --addRegistration(@RequestBody *registration*) adds new user(new *registration* object for 'Registration' table).
 - http://localhost:8080/user/{username} (RequestMethod.DELETE)
 --deleteRegistration(@PathVariable *username*) delete user(*registration* object from 'Registration' table and all his shortened links).

6.Easylink db manager (EasyLinkDatabaseManager.java) 
This class create connection with 'easylink' database and provide methods to work with it.
In fields USERNAME, PASSWORD and CONN_STRING put corresponding information about database maintenance tool you are working with.
And if connection with database established,
( URL(*id* - String, *full_url_address* - String), *username*- String )
- method getAllLinks() Returns(list<URL>) all records from 'Easylink' table.
- method getLink(*id*) Returns(String) full url address / if have not found it returns: "You`re not lucky. No link found for such Id :(".
- method getPassword(*username*) Returns(String) *username*s password  / if have not found it returns: "You`re not lucky. No password found for such user :(".
- method insertLink(*id*,*full_url_address*) Returns(boolean:true) and inserts in 'easylink' table new record (everyone can use this shortened link) / if something wrong returns:(boolean:false).
- method insertLink(*id*,*full_url_address*,*username*) This method overloads previous one, it inserts new record for concrete user. Returns(boolean:true) / if something wrong returns:(boolean:false).
- method checkDuplicateID(*id*) Returns(boolean:true) if in table 'Easylink' is only one record with *id* / else returns:(boolean:false).
- method deleteLink(*id*) Returns(boolean:true) if delete link from table 'Easylink' / else returns:(boolean:false).


7.Registration db manager (RegistrationDatabaseManager.java) 
This class create connection with 'easylink' database and provide methods to work with it.
In fields USERNAME, PASSWORD and CONN_STRING put corresponding information about database maintenance tool you are working with.
And if connection with database established,
( URL<*id* - String, *full_url_address* - String>, Registration(*username* - String, *password* - String, *email* - String, Set<URL>) )
- method getAllRegistrations() Returns(list<Registration>) all records from 'Registration' table.
- method getRegistration(*username*) Returns(object:Registration) that have *username* as in parameter / if something wrong return:(null).
- method getRegistrationLinks(*username*) Returns(set<URL>) all URL(*id*,*full_url_address*) objects from 'Easylink' table where their "owner" is *username*.
- method getRegistrationLink(*id*) Returns(*full_url_address*) for given *id*/ else returns:"Link not found".
- method insertRegistration(*username*,*password*,*email*)  Returns(boolean:true) and inserts in 'Registarion' table new record(new user) / if something wrong returns:(boolean:false).
- method checkDuplicateID(*id*) Return(boolean:true) Returns(boolean:true) if in table 'Registration' is only one record with *id* / else returns:(boolean:false). 	
- method deleteRegistration(*username*) Returns(boolean:true) if delete user with *username* from table 'Registarion' / else returns:(boolean:false).


@author Kristaps, Raivis, Martins, Arturs
	









































