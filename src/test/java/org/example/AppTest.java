package org.example;

import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    // The following is the test case
    @Test
    public void testStudent(){
        UserEntity user = new UserEntity();
        //If the id is a self-increasing column, no assignment is required, otherwise an error will be reported
        //user.setId(3);
        user.setLastName("Jun");
        user.setFirstName("Lei");
//        user.setUserId(2);
        user.setNumberOfPets((byte)2);
        session.save(user);
    }


    // The test starts
    @Before
    public void init(){
        //Create a session factory
        sessionFactory = new Configuration().configure().buildSessionFactory();
        // Start a conversation
        session =sessionFactory.openSession();
        //Turn things on
        transaction = session.beginTransaction();
    }

    // End of test
    @After
    public void destroy(){
        //Close things
        transaction.commit();
        //Close the conversation
        session.close();
        // Close the session factory
        sessionFactory.close();
    }


}
