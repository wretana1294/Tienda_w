/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;
//import com.tienda.
// java.util;
import com.tienda.entity.Persona;
import com.tienda.service.iPersonaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Carlitos
 */
@Controller
public class PersonasController {

    @Autowired
    private iPersonaService personaService;

    @GetMapping("/personas")
    public String index(Model model) {
        List<Persona> ListaPersonas = personaService.getAllPerson();
        model.addAttribute("titulo", "Personas");
        model.addAttribute("personas", ListaPersonas);
        return "personas";
    }
    
    @GetMapping("/personasN")
    public String crearPersona(Model model){
        model.addAttribute("persona", new Persona());
        //personaService.savePerson(persona);
        return "crear";
    }
    
    @PostMapping("/save")
    public String guardarPersona(@ModelAttribute Persona persona){
     personaService.savePerson(persona);
     return "redirect:/persona";
    }
    
    @GetMapping("/delete/{id}")
    public String eliminarPersona(@PathVariable("id") Long idPersona) {
        personaService.delete(idPersona);
        return "redirect:/persona";
    }
}

/*Protocolos
Post: Crear en base de datos un registro nuevo.
Get: Lee de la BD 
Put: Actualiza o reemplaza información en la BD.
Delete: Unicamente para eliminar toda la colección de datos.
Patch: modifica una columna únicamente.
*/
