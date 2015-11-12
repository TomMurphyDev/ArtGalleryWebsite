package model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import play.db.ebean.*;
import play.mvc.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;
/***************************************************************************************

*    Usage: Following actions demonstrated
*    Title: LABS WDD2 weeks 1-12
*    Author:ENDA LEE
*    Date: 30-06-2015
*    Code version: <code version>
*    Availability: Itt tallaght moodle course page.Password protected 
*
***************************************************************************************/
@Entity
public class Event extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int eventID;//uniquely set on addition to the database
	@Constraints.Required
	public String eventName,eventCat,eventCost,eventDescription;
	@Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
	public Date eventDate;
	public int capacity;
	public int numbookings;
	@Constraints.Required
	public String imageURL;
	public static Finder<Long, Event> find = new Finder<>(Event.class);
	public Event(){}
	
	
	public Event(String eventName, String eventCat, String eventCost, String eventDescription, Date eventDate,
			int capacity, int numbookings, String imageURL) {
		super();
		this.eventName = eventName;
		this.eventCat = eventCat;
		this.eventCost = eventCost;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.capacity = capacity;
		this.numbookings = numbookings;
		this.imageURL = imageURL;
	}




	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventName=" + eventName + ", eventCat=" + eventCat + ", eventCost="
				+ eventCost + ", eventDescription=" + eventDescription + ", eventDate=" + eventDate + ", capacity="
				+ capacity + ", numbookings=" + numbookings + "]";
	}

	public static List<Event> findAll(){
		return Event.find.all();
	}
	@SuppressWarnings("deprecation")
	public static List<Event> findAvailable(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = new Date(dateFormat.format(cal.getTime()));
		List<Event> avail = find.where().gt("eventDate",date).orderBy("eventDate").findList();
		List<Event> result = new ArrayList<Event>();
		for(int i = 0;i < avail.size();i++){
			if(avail.get(i).numbookings < avail.get(i).capacity){
				result.add(avail.get(i));
			}
		}
		//findList();
		return result;
	}
	
	
	public static Event findById(int id) {
		   return find.where().eq("eventID", id).findUnique();
		  }
	public Event findById(){
		return find.where().eq("eventID",
				eventID).findUnique();
	}
	
}