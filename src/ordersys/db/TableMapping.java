package ordersys.db;

import java.util.ArrayList;

import com.chinasofti.util.jdbc.template.automapper.annotation.Param;
import com.chinasofti.util.jdbc.template.automapper.annotation.Select;
import com.chinasofti.util.jdbc.template.automapper.annotation.Update;

public interface TableMapping {
	
	@Select("select * from mytable where mytable.id=#{id}")
	public ArrayList<MytableInfo> getAllFood(@Param("id") int id);
	
	@Select("select sum(food.price*mytable.count) from mytable,food where mytable.id=#{id} and mytable.food_name = food.name")
	public double getPriceSum(@Param("id") int id);
	
	@Update("insert into mytable (id,food_name,count) values (#{info.id},#{info.food_name},#{info.count})")
	public void insertFood(@Param("info") MytableInfo info);
	
	@Update("delete from mytable where id=#{id}")
	public void emptyTable(@Param("id") int id);
	
}
