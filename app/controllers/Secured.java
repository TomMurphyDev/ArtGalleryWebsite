package controllers;
import model.User;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import play.mvc.Security.Authenticator;
/***************************************************************************************

*    Usage: Following actions demonstrated
*    Title: LABS WDD2 weeks 1-12
*    Author:ENDA LEE
*    Date: 30-06-2015
*    Code version: 1.0
*    Availability: Itt tallaght moodle course page.Password protected 
*
***************************************************************************************/
/**
 * The Class Secured.
 */
public class Secured extends Security.Authenticator {
	
	/* 
	 * @see play.mvc.Security.Authenticator#getUsername(play.mvc.Http.Context)
	 */
	@Override
	public String getUsername(Context ctx) {
		// TODO Auto-generated method stub
		
		return ctx.session().get("email");
	}
	
	

	/* 
	 * @see play.mvc.Security.Authenticator#onUnauthorized(play.mvc.Http.Context)
	 */
	public Result onUnauthorized(Context ctx){
		return redirect(routes.Application.login());
	}
	
}
