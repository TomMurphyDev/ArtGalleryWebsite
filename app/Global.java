import play.*;
import play.libs.*;

import java.util.*;
import java.text.ParseException;

import play.GlobalSettings;

import com.avaje.ebean.*;

import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;
import controllers.routes;
import model.*;
/**
 * The Class Global.
 * Responsible for applying filters in form fields
 */
public class Global extends GlobalSettings {
	
	/* 
	 * @see play.GlobalSettings#filters()
	 * Override the globalSetting filter class
	 */
	@Override
	public <T extends EssentialFilter> Class<T>[] filters() {
		return new Class[]{CSRFFilter.class};
	}
	
}