/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.entity.Pais;
import com.tienda.repository.PaisRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlitos
 */
@Service
public class PaisService implements iPaisService{
    
    //Inyección de dependias
    @Autowired
    private PaisRepository paisRepository;
    
    @Override
    public List<Pais> listCountry(){
        return (List<Pais>) paisRepository.findAll();
    }
}
