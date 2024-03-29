package br.com.maciel.kotlinstore.controller.model

import java.math.BigDecimal
import java.math.BigInteger
import javax.persistence.*

enum class STATUS_ITEM () {
        VENDA_NORMAL,
        PROMOCAO,
        CANCELADO
}

@Entity
data class Categoria (
        @Id @GeneratedValue var categoriaId: Int?,
        var nome: String,
        var descricao: String?)
@Entity
data class Produto (
        @Id @GeneratedValue var produtoId: Long?,
        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "categoriaId", foreignKey = ForeignKey(name = "categoria_produto_fk")) var categoria: Categoria,
        var nome: String,
        var descricao: String?,
        var preco: BigDecimal,
        var quantidadeEstoque: Int? = null,
        var foto: String? = null)

@Entity
data class ItemPedido (
        @Id @GeneratedValue var itemId: Long?,
        @OneToOne
        @JoinColumn(name = "produtoId", foreignKey = ForeignKey(name = "produto_items_pedido_fk")) var produto:Produto,
        var precoVenda: BigDecimal,
        var quantidade: Int,
        var observacao: String? = null,
        /**
         * 1 = Venda Normal
         * 2 = Promoção/Combo
         * ...
         * 9 = Cancelado
         */
        @Enumerated(EnumType.STRING)
        var status: STATUS_ITEM)

@Entity
data class Pedido (
        @Id @GeneratedValue var pedidoId: Long?,
        @ManyToMany @JoinTable(name = "PedidoItens") var items: List<ItemPedido>) {
        // Valor total dos itens de um pedido
        var valorBrutoPedido: BigDecimal = BigDecimal.ZERO
                get() =  items.stream().map { it -> it.precoVenda }.reduce(BigDecimal::add).get()
        // Valor total dos itens cancelados do pedido
        var valorDescontoConcedido: BigDecimal = BigDecimal.ZERO
                get()  {
                        if(items.filter { it -> it.status == STATUS_ITEM.CANCELADO }.size > 0)
                                return items.filter { it -> it.status == STATUS_ITEM.CANCELADO }.map { it -> it.precoVenda }.reduce(BigDecimal::add)
                        else
                                return BigDecimal.ZERO
                }
        // Valor total dos itens de um pedido subraindo os descontos
        var valorFinalPedido: BigDecimal = BigDecimal.ZERO
                get() = valorBrutoPedido.subtract(valorDescontoConcedido)
}