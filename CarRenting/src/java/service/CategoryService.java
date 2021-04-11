/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDao;
import java.util.Vector;
import model.Category;

/**
 *
 * @author ADMIN
 */
public class CategoryService {

    private CategoryDao cateDao = null;

    public CategoryService() {
        cateDao = new CategoryDao();
    }

    public Vector<Category> getAllCate() {
        Vector<Category> cateList = cateDao.getAllCategory();
        return cateList;
    }

    public String getCategoryName(int id) {
        Category cate = cateDao.getCategoryName(id);
        return cate.getCategoryName();
    }

}
