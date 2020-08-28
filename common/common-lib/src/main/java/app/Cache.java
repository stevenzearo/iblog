package app;

import java.util.List;

/**
 * @author steve
 */

/*
 * design summary:
 * 1. all cache data will be saved in redis with a valid duration which will be set in properties file
 * 2. cache can be set, update, and remove
 * 3. cathe will be removed while valid duration expired (this can be implemented with redis in built-in feature)
 * */
public interface Cache {
    <T> void save(T t);

    <T> void bulkSave(List<T> t);

    <T> void update(String id, T t);

    void remove(String id);

    void removeAll();
}
