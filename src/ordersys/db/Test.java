package ordersys.db;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FoodMapping mapping = MapperFactory.getDBMapper(FoodMapping.class);
		FoodInfo info = new FoodInfo();
		info.setBpic("/userhead/u1");
		info.setDescription("��Ѽ�ó�");
		info.setFlag(2);
		info.setName("ζ����");
		info.setPrice(64);
		
		mapping.insertFood(info);
		

	}

}
