package ca.jrvs.apps.twitter.dao;

public interface CrdDao<T, ID> {
  /**
   * Create an entity to the underlying storage
   *
   * @param entity entity that to be created
   *               Discuss what does <T, ID> mean.
   *               Write public class TwitterRestDao implements
   *               CrdDao<Tweet, String>
   *               1. Implement
   *               ca.jrvs.apps.twitter.dao.TwitterRestDao#findById
   *               High-level steps
   * @return created entity
   */
  T create(T entity);

  /**
   * Find an entity by its id
   *
   * @param id entity id
   * @return created entity
   */
  T findById(ID id);
  /**
   * Delete an entity by its ID
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  T deleteById(ID id);
}