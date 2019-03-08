package br.com.maciel.kotlinstore.controller.model

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

