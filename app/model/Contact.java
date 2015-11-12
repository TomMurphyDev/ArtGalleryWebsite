package model;

import java.sql.Time;
import java.util.*;

import javax.persistence.*;

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

/**
 * The Class Contact.
 */
@Entity
public class Contact extends Model  {
	
	/** The msg id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int msgId;
	
	/** The name. */
	public String name;
	
	/** The email. */
	public String email;
	
	/** The subject. */
	public String subject;
	
	/** The message. */
	public String message;
	
	/** The status. */
	public String status = "open";;
	
	/** The find. */
	public static Finder<Long, Contact> find = new Finder<>(Contact.class);
	
	/**
	 * Instantiates a new contact.
	 */
	public Contact(){}
	
	/**
	 * Instantiates a new contact.
	 *
	 * @param name the name
	 * @param email the email
	 * @param subject the subject
	 * @param message the message
	 */
	public Contact(String name, String email, String subject, String message) {
		super();
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public static List<Contact> findAll(){
		return Contact.find.all();
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the contact
	 */
	public static Contact findById(int id) {
		   return find.where().eq("msgId", id).findUnique();
		  }

}
