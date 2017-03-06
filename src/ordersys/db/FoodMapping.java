package ordersys.db;

import java.util.ArrayList;

import com.chinasofti.util.jdbc.template.automapper.annotation.Param;
import com.chinasofti.util.jdbc.template.automapper.annotation.Select;
import com.chinasofti.util.jdbc.template.automapper.annotation.Update;

public interface FoodMapping {
	
	@Select("select * from food order by id limit #{offsite},#{size}")
	public ArrayList<FoodInfo> getAllFood(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select * from food where id=#{id}")
	public FoodInfo getFoodById(@Param("id") int id);
	
	@Select("select count(*) from food")
	public long getAllFoodCount();
	
	@Select("select * from food where flag=1 order by id limit #{offsite},#{size}")
	public ArrayList<FoodInfo> getSnack(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select * from food where flag=2 order by id limit #{offsite},#{size}")
	public ArrayList<FoodInfo> getSoup(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select * from food where flag=3 order by id limit #{offsite},#{size}")
	public ArrayList<FoodInfo> getMainFood(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select * from food where flag=4 order by id limit #{offsite},#{size}")
	public ArrayList<FoodInfo> getDessert(@Param("offsite") int offsite, @Param("size") int size);
	
	@Update("insert into food(name,description,flag,price,bpic) values(#{info.name},#{info.description},#{info.flag},#{info.price},#{info.bpic})")
	public void insertFood(@Param("info") FoodInfo info);
	
	@Update("update food set name=#{fname},description = #{fdescription},flag =#{fflag} ,price=#{fprice},bpic=#{fbpic} where id = #{id}")
	public void updateFood(@Param("fname") String fname,@Param("fdescription") String fdescription,@Param("fflag") int fflag,@Param("fprice") double fprice,@Param("fbpic") String fbpic,@Param("id") int id);
	

	
	@Update("delete from food where id=#{id}")
	public void deleteFood(@Param("id") int id);

}
