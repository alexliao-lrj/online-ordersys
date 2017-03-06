package ordersys.db;

import java.util.ArrayList;

import com.chinasofti.util.jdbc.template.automapper.annotation.Param;
import com.chinasofti.util.jdbc.template.automapper.annotation.Select;
import com.chinasofti.util.jdbc.template.automapper.annotation.Update;

public interface UserinfoMapping {
	
	@Select("select * from userinfo order by id limit #{offsite},#{size}")
	public ArrayList<UserInfo> getAllUser(@Param("offsite") int offsite, @Param("size") int size);
	
	@Select("select count(*) from userinfo")
	public long getAllUserCount();
	
	@Select("select * from userinfo where id=#{id}")
	public UserInfo getUserById(@Param("id") int id);
	
	@Select("select * from userinfo where account=#{account}")
	public UserInfo getUserByAccount(@Param("account") String account);
	

	@Update("insert into userinfo(account,password,salary,phone,flag,bpic) values(#{info.account},#{info.password},#{info.salary},#{info.phone},#{info.flag},#{info.bpic})")
	public void insertUser(@Param("info") UserInfo info);
	
	
	
	@Update("delete from userinfo where id=#{id}")
	public void deleteUserByAccount(@Param("id") int id);
	
	@Update("update userinfo set flag=#{flag} where account=#{name}")
	public void modifyUserFlag(@Param("flag") int flag,@Param("name") String name);
	
	@Update("update userinfo set salary=#{salary} where account=#{name}")
	public void modifyUserSalary(@Param("salary") float salary,@Param("name") String name);
	
	@Update("update userinfo set phone=#{phone} where account=#{name}")
	public void modifyUserPhone(@Param("phone") String phone,@Param("name") String name);
	
	@Update("update userinfo set bpic=#{bpic} where account=#{name}")
	public void modifyUserBpic(@Param("bpic") String bpic,@Param("name") String name);
	
	@Update("delete from userinfo where id=#{id}")
	public void deleteUser(@Param("id") int id);
	
	@Update("update userinfo set account=#{account},password = #{password},flag =#{flag} ,salary=#{salary},bpic=#{bpic},phone=#{phone} where id = #{id}")
	public void updateUser(@Param("account") String account,@Param("password") String password,@Param("salary") double salary,@Param("phone") String phone,@Param("flag") int flag,@Param("bpic") String bpic,@Param("id") int id);
	
}
