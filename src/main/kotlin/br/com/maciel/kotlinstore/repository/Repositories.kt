package br.com.maciel.kotlinstore.controller.repository

import br.com.maciel.kotlinstore.controller.model.Categoria
import br.com.maciel.kotlinstore.controller.model.ItemPedido
import br.com.maciel.kotlinstore.controller.model.Pedido
import br.com.maciel.kotlinstore.controller.model.Produto
import org.springframework.data.repository.CrudRepository

/*
 *
 */


interface CategoriaRepository : CrudRepository<Categoria, Int> {

}


interface  ProdutoRepository : CrudRepository<Produto, Long> {

}


interface  ItemPedidoRepository : CrudRepository<ItemPedido, Long> {

}


interface  PedidoRepository : CrudRepository<Pedido, Long> {

}

