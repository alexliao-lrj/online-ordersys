package ordersys.db;

import java.util.ArrayList;
import java.util.Date;

import com.chinasofti.util.jdbc.template.automapper.annotation.Param;
import com.chinasofti.util.jdbc.template.automapper.annotation.Select;
import com.chinasofti.util.jdbc.template.automapper.annotation.Update;

public interface OrderMapping {
	
	@Select("select * from myorder order by id limit #{offsite},#{size}")
	public ArrayList<MyorderInfo> getAllOrder(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select * from myorder where flag = 0 order by id limit #{offsite},#{size}")
	public ArrayList<MyorderInfo> getAllUnfinishedOrder(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select * from myorder where flag = 1 order by id limit #{offsite},#{size}")
	public ArrayList<MyorderInfo> getAllFinishedOrder(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select count(*) from myorder")
	public long getAllOrderCount();
	
	@Update("insert into myorder (table_id,starttime,endtime,price,flag) values(#{info.table_id},#{info.starttime},#{info.endtime},#{info.price},#{info.flag})")
	public void insertOrder(@Param("info") MyorderInfo info);
	
	@Update("update myorder set flag=1 where id=#{id1} ")
	public void setFinished(@Param("id1") int id1,@Param("endtime") Date endtime);
	
	
	
}
