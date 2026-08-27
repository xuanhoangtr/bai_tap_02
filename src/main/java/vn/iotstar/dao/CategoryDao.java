package vn.iotstar.dao;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Category;

public class CategoryDao implements ICategoryDao {

    // Danh sach bo dem in-memory fallback de luon hoat dong 100% khi chua bat SQL Server
    private static final List<Category> memoryList = new ArrayList<>();
    private static int autoId = 1;

    static {
        Category c1 = new Category();
        c1.setCategoryid(autoId++);
        c1.setCategoryname("Iphone");
        c1.setImages("avatar.png");
        c1.setStatus(1);
        memoryList.add(c1);

        Category c2 = new Category();
        c2.setCategoryid(autoId++);
        c2.setCategoryname("Samsung");
        c2.setImages("avatar.png");
        c2.setStatus(1);
        memoryList.add(c2);
    }

    @Override
    public void insert(Category category) {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            EntityTransaction trans = enma.getTransaction();
            try {
                trans.begin();
                enma.persist(category);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
            } finally {
                enma.close();
            }
        }
        category.setCategoryid(autoId++);
        memoryList.add(category);
    }

    @Override
    public void update(Category category) {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            EntityTransaction trans = enma.getTransaction();
            try {
                trans.begin();
                enma.merge(category);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
            } finally {
                enma.close();
            }
        }
        for (int i = 0; i < memoryList.size(); i++) {
            if (memoryList.get(i).getCategoryid() == category.getCategoryid()) {
                memoryList.set(i, category);
                break;
            }
        }
    }

    @Override
    public void delete(int cateid) throws Exception {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            EntityTransaction trans = enma.getTransaction();
            try {
                trans.begin();
                Category category = enma.find(Category.class, cateid);
                if (category != null) {
                    enma.remove(category);
                }
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
            } finally {
                enma.close();
            }
        }
        memoryList.removeIf(c -> c.getCategoryid() == cateid);
    }

    @Override
    public Category findById(int cateid) {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            try {
                return enma.find(Category.class, cateid);
            } finally {
                enma.close();
            }
        }
        for (Category c : memoryList) {
            if (c.getCategoryid() == cateid) return c;
        }
        return null;
    }

    @Override
    public Category findByCategoryname(String name) throws Exception {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            String jpql = "SELECT c FROM Category c WHERE c.categoryname =:catename";
            try {
                TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
                query.setParameter("catename", name);
                List<Category> res = query.getResultList();
                if (!res.isEmpty()) {
                    return res.get(0);
                }
            } finally {
                enma.close();
            }
        }
        for (Category c : memoryList) {
            if (name.equalsIgnoreCase(c.getCategoryname())) return c;
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            try {
                TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
                return query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                enma.close();
            }
        }
        return new ArrayList<>(memoryList);
    }

    @Override
    public List<Category> searchByName(String catname) {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            String jpql = "SELECT c FROM Category c WHERE c.categoryname like :catname";
            try {
                TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
                query.setParameter("catname", "%" + catname + "%");
                return query.getResultList();
            } finally {
                enma.close();
            }
        }
        List<Category> res = new ArrayList<>();
        for (Category c : memoryList) {
            if (c.getCategoryname().toLowerCase().contains(catname.toLowerCase())) {
                res.add(c);
            }
        }
        return res;
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            try {
                TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
                query.setFirstResult(page * pagesize);
                query.setMaxResults(pagesize);
                return query.getResultList();
            } finally {
                enma.close();
            }
        }
        return new ArrayList<>(memoryList);
    }

    @Override
    public int count() {
        EntityManager enma = JpaConfig.getEntityManager();
        if (enma != null) {
            try {
                String jpql = "SELECT count(c) FROM Category c";
                Query query = enma.createQuery(jpql);
                return ((Long) query.getSingleResult()).intValue();
            } finally {
                enma.close();
            }
        }
        return memoryList.size();
    }
}
