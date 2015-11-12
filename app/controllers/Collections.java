package controllers;
import play.mvc.Security;
import model.*;
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
public class Collections extends Application{
	public Result collection() {
		List<Collection> collections = Collection.findSingleId();
		String em = session().get("email");
		User u = User.findByEmail(em);
    	return ok(collectionsHome.render(u,collections));
    }
	public Result collectionView(int id){
		String em = session().get("email");
		User u = User.findByEmail(em);
		List<Collection> collections= Collection.findById(id);
		Artist artist = Artist.findById(id);
		return ok(collectionView.render(u,artist,collections));
    }

}
