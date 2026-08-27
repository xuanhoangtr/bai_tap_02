package vn.hcmute.dao;

import java.util.List;
import vn.hcmute.models.CategoryModel;

public interface ICategoryDao {
    void insert(CategoryModel category);
    void edit(CategoryModel category);
    void delete(int id);
    CategoryModel get(int id);
    CategoryModel get(String name);
    List<CategoryModel> getAll();
    List<CategoryModel> search(String keyword);
}
