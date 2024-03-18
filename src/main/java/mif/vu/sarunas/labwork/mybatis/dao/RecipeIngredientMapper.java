package mif.vu.sarunas.labwork.mybatis.dao;

import mif.vu.sarunas.labwork.mybatis.model.RecipeIngredient;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface RecipeIngredientMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE_INGREDIENT
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	int insert(RecipeIngredient record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table PUBLIC.RECIPE_INGREDIENT
	 *
	 * @mbg.generated Sun Mar 17 23:42:02 EET 2024
	 */
	List<RecipeIngredient> selectAll();
}