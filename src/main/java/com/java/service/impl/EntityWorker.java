/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.java.service.impl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityWorker {

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
