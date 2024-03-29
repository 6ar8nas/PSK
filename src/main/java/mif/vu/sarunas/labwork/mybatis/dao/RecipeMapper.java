package mif.vu.sarunas.labwork.mybatis.dao;

import mif.vu.sarunas.labwork.mybatis.model.Recipe;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface RecipeMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	int insert(Recipe record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	Recipe selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	List<Recipe> selectAll();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	int updateByPrimaryKey(Recipe record);
}