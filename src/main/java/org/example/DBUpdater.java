package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;

public class DBUpdater {
    /**
     * Create and initialize necessary variables to update mapped objects
     * SessionFactory *creates* Session *creates* Transaction
     */
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public DBUpdater() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    /**
     * Save the mapped Entity into the database
     * -UserEntity should be properly initialized before being inputted into this method
     * @param e  any entity object
     * @return true when properly saved into database, false during exception
     */
    public boolean addEntity(Entity e){
        try{
            session.save(e);
            System.out.println("saved e");
            return true;
        } catch(Exception error){
            System.out.println("error saving e: " + error.getMessage());
            return false;
        }
    }

    /**
     * Delete a selected entity from the database
     * @param e any entity object
     * @return true when properly deleted from database, false during exception
     */
    public boolean deleteEntity(Entity e){
//        Query query = session.createQuery("delete PetsEntity where petId = :num");
//        query.setParameter("num", petId);
//
//        int result = query.executeUpdate();
//
//        if (result > 0) {
//            System.out.println("pet was removed");
//            return true;
//        }
//        return false;
        if (e != null) {
            session.delete(e);
            return true;
        }else{
            System.out.println("Cannot find identification key");
            return false;
        }
    }

    /**
     * Retrieve the designated entity from the database
     * @param pet UserEntity object(using default constructor will work here)
     * @param petId primary key identification for the database
     * @return an UserEntity object; null when the requested data does not exist
     */
    public PetsEntity getEntity(PetsEntity pet, int petId){
        pet.setPetId(petId);
        PetsEntity obtainedPet = session.get(pet.getClass(),petId);
//        System.out.println(obtainedPet.getPetId() + " " + obtainedPet.getPetName());
        return obtainedPet;
    }

    /**
     * Retrieve the designated entity from the database
     * @param user UserEntity object(using default constructor will work here)
     * @param userId primary key identification for the database
     * @return an UserEntity object; null when the requested data does not exist
     */
    public UserEntity getEntity(UserEntity user, int userId){
        user.setUserId(userId);
        UserEntity obtainedUser = session.get(user.getClass(),userId);
//        System.out.println(obtainedUser.getUserId() + " " + obtainedUser.getFirstName());
        return obtainedUser;
    }

    /**
     * Updates the database with matching entityId.
     * This method should be used along side with getEntity()
     * @param entity any entity object
     * @return true when the database is successfully updated; false other wise
     */
    public boolean updateEntity(Entity entity){
        if(entity != null){
            session.update(entity);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Ends Transaction, closes Session, and close SessionFactory
     * @return true when successfully closed; false during exception
     */
    public boolean closeUpdater(){
        try{
            transaction.commit();
            session.close();
            sessionFactory.close();
            System.out.println("all closed");
            return true;
        }catch(Exception e){
            System.out.println("Error closing updater");
            return false;
        }
    }
}
