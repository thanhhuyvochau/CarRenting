/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.sql.ResultSet;
import model.Category;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class CategoryMapper implements MapperInterface<Category> {
    final static Logger log = Logger.getLogger(CategoryMapper.class.getName());

    public CategoryMapper() {
    }

    public Category mapper(ResultSet result) {
        Category category = null;
        try {
            int id = result.getInt("type_id");
            String cateName = result.getString("type_name");
            category = new Category(id, cateName);
        } catch (Exception e) {
            //log
            log.info(e.toString());
        }
        return category;

    }

}
