package br.com.maciel.kotlinstore.controller

import br.com.maciel.kotlinstore.controller.model.Pedido
import br.com.maciel.kotlinstore.controller.repository.PedidoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PedidoController constructor(@Autowired val repository: PedidoRepository) {

    @RequestMapping( "pedido/", method = [RequestMethod.GET])
    fun listarWEB(model: Model): String {
        model["title"] = "Pedidos"
        model["pedidos"] = repository.findAll()
        return "pedidos"
    }

    @RequestMapping( "pedido/api", method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listarAPI(): ResponseEntity<Iterable<Pedido>> {
        return  ResponseEntity.ok(repository.findAll())
    }

}