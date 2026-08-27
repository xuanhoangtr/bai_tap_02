package vn.hcmute.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.hcmute.dao.DBConnection;
import vn.hcmute.dao.ICategoryDao;
import vn.hcmute.models.CategoryModel;

public class CategoryDaoImpl extends DBConnection implements ICategoryDao {

    private static final List<CategoryModel> memoryList = new ArrayList<>();
    private static int autoId = 1;

    static {
        memoryList.add(new CategoryModel(autoId++, "Lap Trinh Java", "category/default.png"));
        memoryList.add(new CategoryModel(autoId++, "Lap Trinh Web", "category/default.png"));
        memoryList.add(new CategoryModel(autoId++, "Co So Du Lieu", "category/default.png"));
    }

    @Override
    public void insert(CategoryModel category) {
        if (category == null) return;

        Connection con = super.getConnection();
        if (con != null) {
            String sql = "INSERT INTO Category(cate_name, icons) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, category.getName());
                ps.setString(2, category.getIcon());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        category.setId(autoId++);
        memoryList.add(category);
    }

    @Override
    public void edit(CategoryModel category) {
        if (category == null) return;

        Connection con = super.getConnection();
        if (con != null) {
            String sql = "UPDATE Category SET cate_name = ?, icons = ? WHERE cate_id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, category.getName());
                ps.setString(2, category.getIcon());
                ps.setInt(3, category.getId());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        for (int i = 0; i < memoryList.size(); i++) {
            if (memoryList.get(i).getId() == category.getId()) {
                memoryList.set(i, category);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection con = super.getConnection();
        if (con != null) {
            String sql = "DELETE FROM Category WHERE cate_id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        memoryList.removeIf(c -> c.getId() == id);
    }

    @Override
    public CategoryModel get(int id) {
        Connection con = super.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM Category WHERE cate_id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        CategoryModel category = new CategoryModel();
                        category.setId(rs.getInt("cate_id"));
                        category.setName(rs.getString("cate_name"));
                        category.setIcon(rs.getString("icons"));
                        return category;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        for (CategoryModel c : memoryList) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    @Override
    public CategoryModel get(String name) {
        if (name == null) return null;

        Connection con = super.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM Category WHERE cate_name = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        CategoryModel category = new CategoryModel();
                        category.setId(rs.getInt("cate_id"));
                        category.setName(rs.getString("cate_name"));
                        category.setIcon(rs.getString("icons"));
                        return category;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        for (CategoryModel c : memoryList) {
            if (name.equalsIgnoreCase(c.getName())) return c;
        }
        return null;
    }

    @Override
    public List<CategoryModel> getAll() {
        Connection con = super.getConnection();
        if (con != null) {
            List<CategoryModel> categories = new ArrayList<>();
            String sql = "SELECT * FROM Category";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoryModel category = new CategoryModel();
                    category.setId(rs.getInt("cate_id"));
                    category.setName(rs.getString("cate_name"));
                    category.setIcon(rs.getString("icons"));
                    categories.add(category);
                }
                return categories;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        return new ArrayList<>(memoryList);
    }

    @Override
    public List<CategoryModel> search(String keyword) {
        List<CategoryModel> result = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAll();
        }

        Connection con = super.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM Category WHERE cate_name LIKE ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, "%" + keyword.trim() + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CategoryModel category = new CategoryModel();
                        category.setId(rs.getInt("cate_id"));
                        category.setName(rs.getString("cate_name"));
                        category.setIcon(rs.getString("icons"));
                        result.add(category);
                    }
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        for (CategoryModel c : memoryList) {
            if (c.getName().toLowerCase().contains(keyword.trim().toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
}
