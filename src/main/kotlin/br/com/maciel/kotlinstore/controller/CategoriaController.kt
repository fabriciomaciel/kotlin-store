package br.com.maciel.kotlinstore.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class CategoriaController {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun listarWEB(model: Model): String {
        model["title"] = "Categorias"
        return "categorias"
    }

    @RequestMapping(value = "/api", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun listarAPI(): ResponseEntity<String> {
        return  ResponseEntity.ok("{ \"categorias\" : { } }");
    }
}
