# DOC

```
 You can verify if connect wased open
 
 EntityManager entityManager = null; 
 EntityManagerFactory entityManagerFactory = null;
 
 entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
 entityManager = entityManagerFactory.createEntityManager();
 
 LOGGER.log(Level.INFO, "connection was:" + entityManager.isOpen());
 
 entityManager.close(); entityManagerFactory.close();
```

