package br.com.maciel.kotlinstore.controller

import br.com.maciel.kotlinstore.controller.model.Pedido
import br.com.maciel.kotlinstore.controller.model.Produto
import br.com.maciel.kotlinstore.controller.repository.ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ProdutoController constructor(@Autowired val repository: ProdutoRepository) {

    @RequestMapping( "produto/", method = [RequestMethod.GET])
    fun listarWEB(model: Model): String {
        model["title"] = "Produtos"
        model["produtos"] = repository.findAll()
        return "produtos"
    }

    @RequestMapping("produto/api", method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listarAPI(): ResponseEntity<Iterable<Produto>> {
        return  ResponseEntity.ok(repository.findAll())
    }

}