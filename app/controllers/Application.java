package controllers;
import play.*;

import play.mvc.*;
import static play.data.Form.form;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import play.Configuration;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.*;

import org.h2.engine.Session;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import play.api.mvc.Cookie;
import play.api.mvc.DiscardingCookie;
import play.db.*;
/***************************************************************************************

*    Usage: Following actions demonstrated
*    Title: LABS WDD2 weeks 1-12
*    Author:ENDA LEE
*    Date: 30-06-2015
*    Code version: <code version>
*    Availability: Itt tallaght moodle course page.Password protected 
*
***************************************************************************************/

/*
 *Main application controller methods defined here are used by the get and post methods 
 *in the conf/routes section *
 */
public class Application extends Controller {
	/**
	 * Navbar actions
	 * @return the index or whats decribed as the Home Page
	 * 
	 */
	public Result index() {
		String em = session().get("email");
		User u = User.findByEmail(em);
        return ok(index.render(u));
    }
    
    /**
     * Events.
     *
     * @return the available events by calling findAvailable in the event class.
     * pass this list to the events page and render it.
     */
    public Result events() {
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	List<Event> eventlist = Event.findAvailable();
    	return ok(events.render(u,eventlist));
    }
    public Result eventView(int id) {
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	Event event = Event.findById(id);
    	return ok(eventView.render(u,event));
    }
    /**
     * The Class Login.
     */
    //local class for authentication
    public static class Login{
    	
	    /** The email. */
	    public String email;
    	
	    /** The password. */
	    public String password;
    }
    /**
     * Login.
     *
     * @return the login form in the login page in views package
     */
    public Result login(){
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	return ok(login.render(u,form(Login.class)));
    }
    public Result logout(){
    	session().clear();
		User u = new User();
    	return ok(index.render(u));
    }
    public Result accessDenied(){
    	User u = new User();
    	return ok(accessDenied.render(u));
    }
    /**
     * Authenticate. the user.
     *Taking the the email and password from the login form generated in 
     *error handling when applied return to index page
     * @return the result
     */
    public Result authenticate(){
    	Form<Login> loginForm = form(Login.class).bindFromRequest();
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	if(loginForm.hasErrors()) {
  	      return badRequest(views.html.login.render(u,loginForm));
    	}else{
    	String email = loginForm.get().email;
    	String password = loginForm.get().password;
    	if(User.authenticate(email, password)==null){
    		return badRequest(login.render(u,loginForm));
    	}
    	session().clear();
    	session("email",email);
    	u = User.findByEmail(email);
    	return ok(index.render(u));
    	}
    }
}