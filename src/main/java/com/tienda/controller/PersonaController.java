/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.entity.Persona;
import com.tienda.service.iPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Carlitos
 */
@Controller
public class PersonaController {
    @Autowired
    private iPersonaService personaService;
    
    @GetMapping("/personas")
    public String index(Model model){
        List<Persona> listaPersona=personaService.getAllPerson();
        model.addAttribute("titulo", "Personas");
        model.addAttribute("personas", listaPersona);
        return "personas";
    }
    
    //Metodo que se ejecutará la presionar el botón de nueva persona
    @GetMapping("/nuevaPersona")
    public String nuevaPersona(Persona persona){
        return "modificarPersona"; //se usa la misma pagina tanto para modificar persona como para ingresar una nueva
    }
    
    @PostMapping("/guardarPersona")
    public String guardarPersona(Persona persona){
        personaService.savePerson(persona);
        return "redirect:/"; //redirige a la pagina principal
    }
    
    @GetMapping("/modificarPersona/{getId}")
    public String modificarPersona(Persona persona, Model model){
        persona = personaService.getPersonById(persona);
        model.addAttribute("persona", persona);
        return "modificarPersona";
    }
}
