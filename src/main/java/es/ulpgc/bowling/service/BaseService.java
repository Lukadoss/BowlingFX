//package es.ulpgc.bowling.service;
//
//import es.ulpgc.bowling.entity.BaseEntity;
//import org.hibernate.ObjectNotFoundException;
//import org.springframework.validation.Errors;
//
//import java.util.List;
//
//public interface BaseService<T extends BaseEntity> {
//
//    /**
//     * Read all entity objects.
//     *
//     * @return list of all object of type T
//     */
//    List<T> getAll();
//
//    /**
//     * Read object with given ID.
//     *
//     * @param id ID
//     * @return found object, never null
//     * @throws ObjectNotFoundException if object was not found
//     */
//    T get(Long id) throws ObjectNotFoundException;
//
//    /**
//     * Persist entity.
//     *
//     * @param object representation of the new object of type T
//     * @return representation of the object complemented with UUID
//     */
//    T add(T object, Errors errors);
//
//    /**
//     * Soft-delete method which sets "archived" parameter to id. See {@link BaseEntity}.
//     *
//     * @param id UUID
//     * @throws ObjectNotFoundException if no object with current UUID is found
//     */
//    void delete(Long id) throws ObjectNotFoundException;
//
//    /**
//     * Soft-delete method which sets "archived" parameter to id. See {@link BaseEntity}.
//     *
//     * @param object entity to soft delete
//     */
//    void delete(T object) throws ObjectNotFoundException;
//
//    /**
//     * Update object.
//     *
//     * @param object representation of the object object to be updated
//     * @return representation of the updated object
//     * @throws ObjectNotFoundException if station was not found or if id is not set
//     */
//    T update(T object, Errors errors) throws ObjectNotFoundException;
//
//    /**
//     * Test if entity exists.
//     *
//     * @param id UUID
//     * @return true if object exists
//     */
//    boolean exists(Long id);
//
//    /**
//     * Find entity object by ID.
//     *
//     * @param id UUID
//     * @return found object, or null
//     */
//    T find(Long id);
//
//    /**
//     * Count entities (not archived).
//     */
//    long count();
//}
//
