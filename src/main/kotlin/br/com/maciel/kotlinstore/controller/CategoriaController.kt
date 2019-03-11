package br.com.maciel.kotlinstore.controller

import br.com.maciel.kotlinstore.controller.model.Categoria
import br.com.maciel.kotlinstore.controller.repository.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class CategoriaController constructor(@Autowired val repository: CategoriaRepository) {

    @RequestMapping( "categoria/", method = [RequestMethod.GET])
    fun listarWEB(model: Model): String {
        model["title"] = "Categorias"
        model["categorias"] = repository.findAll()
        return "categorias"
    }

    @RequestMapping( "categoria/api", method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listarAPI(): ResponseEntity<Iterable<Categoria>> {
        return  ResponseEntity.ok(repository.findAll())
    }
}
