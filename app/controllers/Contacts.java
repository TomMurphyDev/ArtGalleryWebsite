package controllers;
import play.mvc.Security;
import model.Booking;
import model.Contact;
import model.User;
import play.*;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.*;
import static play.data.Form.form;

import java.util.List;

import com.avaje.ebean.*;

import java.util.List;

import com.avaje.ebean.*;
/***************************************************************************************

*    Usage: Following actions demonstrated
*    Title: LABS WDD2 weeks 1-12
*    Author:ENDA LEE
*    Date: 30-06-2015
*    Code version: <code version>
*    Availability: Itt tallaght moodle course page.Password protected 
*
***************************************************************************************/

/**
 * The Class Contacts.
 */
public class Contacts extends Application {
	
	private static final Form<Contact> contactForm = form(Contact.class);
    /**
     * Contact.
     *
     * @return the result
     */
    public Result contact() {
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	return ok(contact.render(u,contactForm));
    }
    @Security.Authenticated(Secured.class)
    public Result contactList(){
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	if(u.type == 99){
    		List<Contact> contacts = Contact.findAll();
        	return ok(contactView.render(u,contacts));
			}
			else{
				return redirect(routes.Users.accessDenied());
			}
    }
    /**
     * Contact submit.
     *
     * @return the result
     */
    @Security.Authenticated(Secured.class)
    public Result contactSubmit(){
    	Form<Contact> contactFormData = contactForm.bindFromRequest();
    	if(contactFormData.hasErrors()) {
    	      return badRequest("Form Field has Errors" +contactFormData);
    	}
    	String em = session().get("email");
		User u = User.findByEmail(em);
    	Contact newContact =contactFormData.get();
    	newContact.save();
    	return ok(contactSubmit.render(u));
    	
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return the result
     */
    public Result delete(int id){
    	return TODO;
    }
}