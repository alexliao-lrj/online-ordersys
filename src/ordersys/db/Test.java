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
		info.setDescription("¿¾Ñ¼ºÃ³Ô");
		info.setFlag(2);
		info.setName("Î¶ÔöÌÀ");
		info.setPrice(64);
		
		mapping.insertFood(info);
		

	}

}
