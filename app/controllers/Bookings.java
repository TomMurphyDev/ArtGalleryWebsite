package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controllers.*;
import model.*;
import play.*;
import play.data.Form;
import play.libs.Time;
import play.mvc.*;
import views.html.*;
import play.mvc.Security;
import play.*;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.*;
import static play.data.Form.form;

import java.util.List;

import com.avaje.ebean.*;
import com.avaje.ebean.Model.Finder;

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
public class Bookings extends Application {

	public static List<Booking> findAll(){
		return Booking.find.all();
	}
	
	@Security.Authenticated(Secured.class)
	public Result bookingConf(int id){
		Event event = Event.findById(id);
		
    		Booking newBooking = new Booking(event.eventDate, event.eventID,id);
    		newBooking.save();
		return redirect(routes.Bookings.saveBooking());
	}
	
	/**
	 * Save booking.
	 *
	 * @return the result
	 */
	@Security.Authenticated(Secured.class)
	public Result saveBooking(){
		String em = session().get("email");
		User u = User.findByEmail(em);
	    return ok(bookingComplete.render(u));
	}
	public static int checkAvail(int id){
		Event order = Ebean.find(Event.class, id);
		if(order.numbookings < order.capacity){
			return (order.numbookings-order.capacity);
		}
		else{ 
			return 0;
		}
	}
/**
 * Booking list.
 *
 * @return the result
 */
//	Returns list of current bookings
	@Security.Authenticated(Secured.class)
   public Result bookingList() {
	   String em = session().get("email");
		User u = User.findByEmail(em);
		if(u.type == 99){
			List<Booking> bookings = Booking.findAll();
			return ok(bookingList.render(u,bookings));
			}
			else{
				return redirect(routes.Users.accessDenied());
			}
    }
    
    /** The book form. */
    //show new booking details form
    Form<Booking> bookForm = Form.form(Booking.class);
    
    /**
     * New booking.
     *
     * @return the result
     */
    public  Result newBooking() {
    	return TODO;
    }
    @Security.Authenticated(Secured.class)
    public Result delete(int id){
    	final Booking ub = Booking.deleteBooking(id);
    	if(ub != null){
    		ub.delete();
    	}
    	return redirect(routes.Users.list());
    }
    @Security.Authenticated(Secured.class)
    public Result deleteByBId(int id){
    	final Booking ub = Booking.deleteBooking(id);
    	if(ub != null){
    		ub.delete();
    	}
    	return redirect(routes.Users.list());
    }
    /**
     * Booking details.
     *
     * @param date the date
     * @return the result
     */
    public Result bookingDetails(String date) {
    	return TODO;
    }
}