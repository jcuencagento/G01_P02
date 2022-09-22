package com.grupo01.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo01.spring.model.Game;


/**
 * 
 * @author Grupo 01
 *
 */

@Repository
public interface GameDAO extends JpaRepository<Game, Integer>{

}
