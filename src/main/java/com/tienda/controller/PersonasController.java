/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;
//import com.tienda.
// java.util;
import com.tienda.entity.Persona;
import com.tienda.service.iPersonaService;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "redirect:/personas";
    }
    
@RequestMapping("/pdf")

    public void getReportsinPDF(HttpServletResponse response) throws JRException, IOException {

        //Compiled report
        InputStream jasperStream = (InputStream) this.getClass().getResourceAsStream("/reports/Reporte_Empleados.jasper");

        //Adding attribute names
        Map params = new HashMap<>();
        params.put("id", "id");
        params.put("nombre", "nombre");
        params.put("apellido1", "apellido1");
        params.put("apellido2", "apellido2");
        params.put("roles", "roles");
        

        // Fetching the student from the data database.
        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(personaService.getAllPerson());

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=ListaPersonas.pdf");

        final ServletOutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }    
}

/*Protocolos
Post: Crear en base de datos un registro nuevo.
Get: Lee de la BD 
Put: Actualiza o reemplaza información en la BD.
Delete: Unicamente para eliminar toda la colección de datos.
Patch: modifica una columna únicamente.
*/
