//package es.ulpgc.bowling.controllers;
//
//import es.ulpgc.bowling.entity.BaseEntity;
//import es.ulpgc.bowling.service.BaseService;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//@CrossOrigin(origins = "*")
//public abstract class BaseController<T extends BaseEntity, S extends BaseService<T>> {
//
////    @Autowired
////    protected ModelMapper mapper;
////
////    /** Endpoint for get all entities of certain type. Use with caution. */
////    @GetMapping(value = "")
////    public List<D> getAll() {
////        List<T> entityList = getService().getAll();
////        List<D> result = new ArrayList<>();
////        entityList.forEach((entity) -> {
////            result.add(mapper.map(entity, getDtoClass()));
////        });
////        return result;
////    }
////
////    /**
////     * Method used for returning of one Object of T type.
////     *
////     * @param id UUID of returned T
////     * @return an object representing concretely implemented T entity
////     */
////    @GetMapping(value = "/{id}")
////    public D get(@PathVariable String id) {
////        T entity = (T) getService().get(id);
////        D entityDto = mapper.map(entity, getDtoClass());
////        return entityDto;
////    }
////
////    /** Endpoint for create single entity. */
////    @PostMapping(value = "")
////    @ResponseStatus(HttpStatus.CREATED)
////    public ResponseEntity<D> add(@RequestBody D entityDto, Errors errors) {
////        T entity = mapper.map(entityDto, getEntityClass());
////        T result = (T) getService().add(entity, errors);
////        return createResponse(mapper.map(result, getDtoClass()), errors);
////    }
////
////    /** Endpoint for delete single entity. */
////    @DeleteMapping(value = "/{id}")
////    @ResponseStatus(HttpStatus.OK)
////    public void delete(@PathVariable String id) {
////        getService().delete(id);
////    }
////
////    /** Endpoint for update single entity. */
////    @PutMapping(value = "/{id}")
////    @ResponseStatus(HttpStatus.OK)
////    public D update(@RequestBody D entityDto, @PathVariable("id") String id, Errors errors) {
////        if (getService().exists(id)) {
////            T persistedEntity = (T) getService().get(id);
////            mapper.map(entityDto, persistedEntity);
////            persistedEntity.setId(id);
////            persistedEntity = (T) getService().update(persistedEntity, errors);
////            D result = mapper.map(persistedEntity, getDtoClass());
////            return result;
////        } else {
////            throw new ObjectNotFoundException(getEntityClass().getSimpleName() + " with id " + id + " does not exist.");
////        }
////    }
////
////    protected ResponseEntity<D> createResponse(D resultObject, Errors errors) {
////        final HttpStatus responseStatus;
////
////        if ((errors != null) && errors.hasErrors()) {
////            responseStatus = HttpStatus.UNPROCESSABLE_ENTITY;
////        } else {
////            responseStatus = HttpStatus.OK;
////        }
////
////        ResponseEntity<D> result = new ResponseEntity<D>(resultObject, responseStatus);
////        return result;
////    }
//
//    public abstract S getService();
//
////    public abstract Class<D> getDtoClass();
//
////    public abstract Class<T> getEntityClass();
//
//}
