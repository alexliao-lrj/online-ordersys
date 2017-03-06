package ordersys.db;

import java.util.ArrayList;

import com.chinasofti.util.jdbc.template.automapper.annotation.Param;
import com.chinasofti.util.jdbc.template.automapper.annotation.Select;
import com.chinasofti.util.jdbc.template.automapper.annotation.Update;

public interface RecommendMapping {
	
	@Select("select * from recommend")
	public ArrayList<RecommendInfo> getAllRecommend();
	
	@Update("insert into recommend values(#{info.food_id})")
	public void insertRecommend(@Param("info") RecommendInfo info);
	
	@Update("delete from recommend where food_id=#{id}")
	public void removeRecommend(@Param("id") int id);
	
}
