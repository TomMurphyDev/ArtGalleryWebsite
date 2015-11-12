package model;
import java.util.*;

import javax.persistence.*;
import javax.validation.Constraint;

import org.mindrot.jbcrypt.BCrypt;

import com.avaje.ebean.Model;

import play.db.ebean.*;
import play.mvc.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;

import play.data.validation.*;

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
@Table(name = "my_user")
public class User extends Model {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;//unique value generated on account creation
	@Constraints.Required
    public String fName;
	@Constraints.Required
	public String lName;
	@Constraints.Required
	@Constraints.Email
	public String email;
	@Constraints.Required
	@Constraints.MinLength(8) 
	public String password;
	@Constraints.Required
	@Constraints.MinLength(8)
	public String confirmPassword;
	@Constraints.Required
	public String tel;
	@Constraints.Required
	public String add1;
	@Constraints.Required
	public String add2;
	@Constraints.Required
	public String country;//user basic information details
	public int type;
	
	 public List<ValidationError> validate() {
		    List<ValidationError> errors = new ArrayList<ValidationError>();
		    if (!(password.equals(confirmPassword)))  {
		        errors.add(new ValidationError("password", "Passwords do not match."));
		    }
		    return errors.isEmpty() ? null : errors;
		}
	public static Finder<Long, User> find = new Finder<>(User.class);
	public User(){
		type =1;
	}
	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param fName the f name
	 * @param lName the l name
	 * @param email the email
	 * @param password the password
	 * @param tel the tel
	 * @param add1 the add1
	 * @param add2 the add2
	 * @param add3 the add3
	 * @param country the country
	 * @param type the type
	 */
	
	@Override
	public String toString() {
		return "User: " +fName + " says";
	};
	
	public User(String fName, String lName, String email, String password,String confirmPassword,
			String tel, String add1, String add2, String country) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.confirmPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		this.tel = tel;
		this.add1 = add1;
		this.add2 = add2;
		this.country = country;
		this.type =1;
	}
public boolean isEmpty(){
	
	if(email == null ){
		return true;
	}
	else{
		return false;
	}
}
	
	public static List<User> findAll(){
		return User.find.all();
	}
	public static User findByEmail(String email) {
		User user = User.find.where().eq("email", email).findUnique();
		 if (user != null) {
		      return user;
		    } else {
		      User p = new User();
		      return p;
		    }
		  
		  }
	public static User findById(int id) {
	   return find.where().eq("id", id).findUnique();
	  }
	public static User authenticate(String email,String password){
		User user = User.find.where().eq("email", email).findUnique();
	    if (user != null && BCrypt.checkpw(password, user.password)) {
	      return user;
	    } else {
	      return null;
	    }
	}

}