package model;
import play.data.Form;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import play.db.ebean.*;
import play.libs.Time;
import play.mvc.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;

import play.data.validation.*;

import com.avaje.ebean.Model;
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
 * The Class Booking.
 */
@Entity
public class Booking extends Model{
	
	/** The booking id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int bookingId;
	
	/** The date. */
	@Formats.DateTime(pattern = "dd/MM/yyyy")
	public Date date = new Date();
	/** The event id. */
	public int eventID;
	public int id;
	
	/** The find. */
	public static Finder<Long, Booking> find = new Finder<>(Booking.class);
	
	/**
	 * Instantiates a new booking.
	 */
	public Booking(){}
	


	/**
	 * Instantiates a new booking.
	 *
	 * @param date the date
	 * @param eventId the event id
	 * @param userID the user id
	 */
	public Booking( Date date, int eventId, int userID) {
		this.date = date;
		this.eventID = eventId;
		this.id = userID;
	}



	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", date=" + date + ", eventId=" + eventID + ", userID=" + id
				+ "]";
	}
	public static List<Booking> findAll(){
		return Booking.find.all();
	}
	public static Booking findDeleteBooking(int id){
		return find.where().eq("id", id).findUnique();
	}
	public static Booking deleteBooking(int id){
		return find.where().eq("bookingId", id).findUnique();
	}
	public static List<Booking> findById(int id){
		List<Booking> cols = Ebean.find(Booking.class).findList();
		List<Booking> bookings =  Ebean.filter(Booking.class).eq("id", id).filter(cols);
		return bookings;
	}
	//

//	public static Booking findByDate(String date) { 
//		for (Booking candidate : bookings) { 
//			if (candidate.date.equals(date)) {
//				return candidate;
//				}
//			}
//		return null;
//		}
		 
	}