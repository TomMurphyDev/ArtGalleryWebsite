package controllers;
import play.mvc.Security;
import model.Booking;
import model.Event;
import model.User;
import play.*;
import play.data.validation.Constraints;
import play.api.data.validation.ValidationError;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.*;
import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.*;

import java.util.List;

import com.avaje.ebean.*;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;









import play.data.Form;
import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.i18n.Messages;

import com.feth.play.module.mail.Mailer;
import com.feth.play.module.mail.Mailer.Mail;
import com.feth.play.module.mail.Mailer.Mail.Body;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
/***************************************************************************************

*    Usage: Following actions demonstrated
*    Title: LABS WDD2 weeks 1-12
*    Author:ENDA LEE
*    Date: 30-06-2015
*    Code version: <code version>
*    Availability: Itt tallaght moodle course page.Password protected 
*
***************************************************************************************/
public class Users extends Application {
	/** The Constant userReg. */
	private static final Form<User> userReg = Form.form(User.class);
	
	/**
	 * User reg.
	 *
	 * @return the result
	 */
	public Result userReg() {
		String em = session().get("email");
		User u = User.findByEmail(em);
        return ok(userRegForm.render(u,userReg));
    }
	@Security.Authenticated(Secured.class)
	public Result viewUser(int id){
		
		User guest = User.findById(id);
		String em = session().get("email");
		User u = User.findByEmail(em);
		List<Booking> bookings = Booking.findById(id);
		return ok(viewUser.render(u,guest,bookings));
	}
	/**
	 * List.
	 *
	 * @return the result
	 */
	
	@Security.Authenticated(Secured.class)
	public Result list(){
		String em = session().get("email");
		User u = User.findByEmail(em);
		if(u.type == 99){
		List<User> users = User.findAll();
		return ok(userList.render(u,users));
		}
		else{
			return redirect(routes.Users.accessDenied());
		}
	}
	/**
	 * User details.
	 *
	 * @param id the id
	 * @return the result
	 */
	@Security.Authenticated(Secured.class)
	public  Result userDetails(int id) {
	    final User user = User.findById(id);
	    if (user == null) {
	      return notFound(String.format("User %s does not exist.", id));
	    }

	    Form<User> filledForm = userReg.fill(user);
	    return ok("Success");
	  }
	
	public Result save() {
		String em = session().get("email");
		User u = User.findByEmail(em);
    	Form<User> userFormData = userReg.bindFromRequest();
	    	if(userFormData.hasErrors()) {
	    	      return badRequest(views.html.userRegForm.render(u,userFormData));
	    	 }else{
	    	    	User newUser = new User(userFormData.get().fName,userFormData.get().lName,userFormData.get().email,userFormData.get().password,userFormData.get().confirmPassword,userFormData.get().tel,userFormData.get().add1,userFormData.get().add2,userFormData.get().country);
	    	    	final String email = newUser.email;
	    	        final Body body = new Body(
	    	                views.txt.email.body.render().toString(),
	    	                views.html.email.body.render().toString()
	    	        );
	    	        final Mailer defaultMailer = Mailer.getDefaultMailer();
	
	    	        {
	    	            // simple usage
	    	            defaultMailer.sendMail("Registration Complete | Welcome to the artGallery", body, email);
	    	        }
	    	        newUser.save();
	    	        String em2 = session().get("email");
	    			User ub = User.findByEmail(em2);
	    	        return ok(views.html.SignUpRegistrationComplete.render(ub));
	    	    }
    }
	
    
    /**
     * Delete.
     *
     * @param id the id
     * @return the result
     */
	@Security.Authenticated(Secured.class)
    public Result delete(int id){
    	final User user = User.findById(id);
    	if(user != null){
    		user.delete();
    	}
    	return redirect(routes.Users.list());
    }
    
}