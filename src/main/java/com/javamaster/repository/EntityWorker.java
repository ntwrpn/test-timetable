/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javamaster.repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class EntityWorker {
    //public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

     	private static final EntityManagerFactory emFactory;
	static {
            emFactory = Persistence.createEntityManagerFactory("COLIBRI");
	}
	public static EntityManager GetEntityWorker(){
		return emFactory.createEntityManager();
	}
	public static void close(){
		emFactory.close();
	}
}
