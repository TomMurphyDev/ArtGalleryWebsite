package model;

import java.sql.Time;
import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
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
public class Collection extends Model{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int id;
	@ManyToOne
	@JoinColumn(table = "artist", referencedColumnName = "artistId")
	public int artistId;
	public String name;
	public String imgUrl;
	public String description;
	
	public static Finder<Long, Collection> find = new Finder<>(Collection.class);
	
	public Collection(){}

	/**
	 * @param id
	 * @param artistId
	 * @param name
	 * @param imgUrl
	 */
	public Collection(int artistId, String name, String imgUrl, String description) {
		this.artistId = artistId;
		this.name = name;
		this.imgUrl = imgUrl;
		this.description = description;
	}
	
	public static List<Collection> findAll(){
		return Collection.find.all();
	}
	public static List<Collection> findSingleId(){
		List<Collection> cols = Ebean.find(Collection.class).findList();
		List<Collection> result = new ArrayList<Collection>();
		int loop = cols.size();
		for(int i = 0;i <loop;i++){
			List<Collection> filteredCols =  Ebean.filter(Collection.class).eq("artistId", i).filter(cols);
			if (filteredCols.size() > 0){
				result.add(filteredCols.get(0));
			}else{
				result.addAll(filteredCols);
			}
		}
		return result;
		
	}
	
	public static List<Collection> findById(int artistId){
	List<Collection> cols = Ebean.find(Collection.class).findList();
	List<Collection> filteredOrders =  Ebean.filter(Collection.class).eq("artistId", artistId).filter(cols);
	return filteredOrders;
	}
}
