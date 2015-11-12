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
@Entity
public class Artist extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int artistId;
	@Constraints.Required
	public String name;
	@Constraints.Required
	public String genre;
	@Constraints.Required
	public String description;
	public static Finder<Long, Artist> find = new Finder<>(Artist.class);
	public Artist(){}
	public Artist(int artistId, String name, String genre, String description) {
		super();
		this.artistId = artistId;
		this.name = name;
		this.genre = genre;
		this.description = description;
	}
	public static Artist findById(int id) {
		   return find.where().eq("artistId", id).findUnique();
		  }
	public static Artist findByEmail(String email) {
		   return find.where().eq("email", email).findUnique();
		  }	
}
