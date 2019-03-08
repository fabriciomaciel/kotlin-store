package br.com.maciel.kotlinstore.controller.model

import java.math.BigDecimal
import java.math.BigInteger
import javax.persistence.*

@Entity
data class Categoria (
        @Id @GeneratedValue var categoriaId: Int?,
        var nome: String,
        var descricao: String?)

@Entity
data class Produto (
        @Id @GeneratedValue var produtoId: Long?,
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "categoriaId") var categoria: Categoria,
        var nome: String,
        var descricao: String?,
        var preco: BigDecimal,
        var quantidadeEstoque: Int? = null,
        var foto: String? = null)

@Entity
data class ItemPedido (
        @Id @GeneratedValue var itemId: Long?,
        @OneToOne @JoinColumn(name = "produtoId") var produto:Produto,
        var precoVenda: BigDecimal,
        var quantidade: Int,
        var observacao: String? = null,
        var status: Int)

@Entity
data class Pedido (
        @Id @GeneratedValue var pedidoId: Long?,
        @ManyToMany @JoinTable(name = "PedidoItens") var items: List<ItemPedido>,
        var valorDescontoConcedido: BigDecimal? = null,
        var valorBrutoPedido: BigDecimal = BigDecimal.ZERO
// TODO
//        val infix valorBrutoPedido: BigDecimal? = BigDecimal.ZERO
//                get() { return null;}
        ,
        var valorFinalPedido: BigDecimal? = null)