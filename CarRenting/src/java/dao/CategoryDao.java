/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Vector;
import mapper.CategoryMapper;
import model.Category;

/**
 *
 * @author ADMIN
 */
public class CategoryDao extends AbstractDao<Category>{

    public CategoryDao() {
    }
    public Vector<Category> getAllCategory(){
        Vector<Category> cateList = null;
        String sql = "SELECT type_id,type_name FROM types";
        cateList = query(sql,new CategoryMapper());
        return cateList;
    }
    public Category getCategoryName(int id){
        Vector<Category> cateList = null;
        String sql = "SELECT type_id,type_name FROM types WHERE type_id = ?";
        cateList = query(sql,new CategoryMapper(),id);
        Category cate = null;
        if(!cateList.isEmpty()){
            cate = cateList.get(0);
        }
        return cate;
    }
    
    
}
