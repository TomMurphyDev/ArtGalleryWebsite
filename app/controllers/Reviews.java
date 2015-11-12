package controllers;
import play.mvc.Security;
import model.*;
import play.*;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.*;
import static play.data.Form.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.*;
/***************************************************************************************

*    Usage: Following actions demonstrated in 
*    Title: LABS WDD2 weeks 1-12
*    Author:ENDA LEE
*    Date: 30-06-2015
*    Code version: <1.0>
*    Availability: Itt tallaght moodle course page.Password protected 
*
***************************************************************************************/

public class Reviews extends Application{
	private static final Form<Review> reviewForm = form(Review.class);

	public Result review() {
		List<Review> reviews = Review.findAll();
		String em = session().get("email");
		User u = User.findByEmail(em);
		return ok(review.render(u,reviews,reviewForm));
    }
	@SuppressWarnings("deprecation")
	@Security.Authenticated(Secured.class)
	public Result reviewSubmit(){
    	List<Review> reviews = Review.findAll();
    	Form<Review> reviewFormData = reviewForm.bindFromRequest();
    		Review newReview=reviewFormData.get();
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Calendar cal = Calendar.getInstance();
    		Date date = new Date(dateFormat.format(cal.getTime()));
        	newReview.dateOf = date;
        	newReview.save();
        	//return ok("U have already left a review " + checkReview.userId +"Else" +newReview.userId  );
        	return redirect(routes.Reviews.review());
    }
}

