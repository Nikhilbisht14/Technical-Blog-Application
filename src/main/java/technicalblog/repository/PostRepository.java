package technicalblog.repository;


import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPost(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
        List<Post> queryResultList = query.getResultList();

        return  queryResultList;
    }


    public Post getLatestPost(){
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class,15);
    }

    public Post createPost(Post newPost){
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            em.persist(newPost);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
        }

        return newPost;
    }

    public Post getPost(Integer postId) {
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class,postId);
    }


    public void updatedPost(Post updatePost){
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            em.merge(updatePost);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
        }
    }

    public void deletePost(Integer postId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            Post post = em.find(Post.class, postId);
            em.remove(post);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
        }
    }
}
