package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Model.Finder;

import java.sql.Time;
import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import play.db.ebean.*;
import play.mvc.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

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
public class Review extends Model{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int reviewId;
	public int userId;
	@ManyToOne
	@JoinColumn(table = "user", referencedColumnName = "fName")
	public String username;
	@Required
	@MaxLength(254)
	public String comment;
	public Date dateOf;
	/**
	 * @param user
	 * @param stars
	 * @param comment
	 */
	public Review(){}
	
	public Review(int userId,String user, String comment) {
		this.userId = userId;
		this.username = user;
		this.comment = comment;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		@SuppressWarnings("deprecation")
		Date date = new Date(dateFormat.format(cal.getTime()));
		this.dateOf = date;
	}
	public static List<Review> findAll(){
		return Review.find.all();
	}
	public static Finder<Long, Review> find = new Finder<>(Review.class);
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the contact
	 */
	public static Review findById(int id) {
		   return find.where().eq("userId", id).findUnique();
		  }
	public static Review findByEmail(String email) {
		   return find.where().eq("email", email).findUnique();
		  }
	

}