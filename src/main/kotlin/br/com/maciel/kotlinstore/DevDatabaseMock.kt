package br.com.maciel.kotlinstore

import br.com.maciel.kotlinstore.controller.model.*
import br.com.maciel.kotlinstore.controller.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class DevDatabaseMock @Autowired constructor(
        val catRepo: CategoriaRepository,
        val prodRepo: ProdutoRepository,
        val itensRepo: ItemPedidoRepository,
        val pedidoRepo: PedidoRepository
) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        catRepo.saveAll(popularMockCategorias())
        prodRepo.saveAll(popularMockProdutos())

        var pedido = criarPedido()
        itensRepo.saveAll(pedido.items)
        pedidoRepo.save(pedido)
    }

    val listaCategorias = LinkedList<Categoria>()
    val listaProdutos = LinkedList<Produto>()
    val listaItensPedido = LinkedList<ItemPedido>()

    fun popularMockCategorias(): List<Categoria> {
        listaCategorias.clear()
        //
        listaCategorias.add(Categoria(null, "Bebidas", "Agua, suco, refigerantes, cervejas e drinks"))
        listaCategorias.add(Categoria(null, "Espetos", "Carne bovina, frango, linguiça"))
        //
        return listaCategorias
    }

    fun popularMockProdutos(): List<Produto> {
        listaProdutos.clear()
        //
        listaProdutos.add(Produto(null, listaCategorias.get(0),"Agua sem gás", "Agua mineral da marca XXX sem gás", BigDecimal(2.50),50))
        listaProdutos.add(Produto(null, listaCategorias.get(0),"Agua com gás", "Agua mineral da marca XXX com gás", BigDecimal(3.00),20))
        //
        listaProdutos.add(Produto(null, listaCategorias.get(1),"Espeto de contra filé", "Espeto de carne boniva, contra filé", BigDecimal(5.50)))
        listaProdutos.add(Produto(null, listaCategorias.get(1),"Espeto de alcatra", "Espeto de carne bovina, alcatra", BigDecimal(5.00)))
        listaProdutos.add(Produto(null, listaCategorias.get(1),"Medalhão de picanha", "Espeto de carne boniva, picanha com bacon em formato medalhão", BigDecimal(7.00)))
        listaProdutos.add(Produto(null, listaCategorias.get(1),"Espeto de Frango", "Espeto de frango temperado", BigDecimal(3.50)))
        listaProdutos.add(Produto(null, listaCategorias.get(1),"Espeto de Liguiça", "Espeto de linguiça toscana temperada", BigDecimal(3.00)))
        //
        return listaProdutos
    }

    fun popularMockItemsPedido(): List<ItemPedido> {
        listaItensPedido.clear()
        //
        listaItensPedido.add(ItemPedido(null, listaProdutos.get(0), listaProdutos.get(1).preco, 2, null, STATUS_ITEM.CANCELADO))
        listaItensPedido.add(ItemPedido(null, listaProdutos.get(0), listaProdutos.get(0).preco, 2, null, STATUS_ITEM.VENDA_NORMAL))
        listaItensPedido.add(ItemPedido(null, listaProdutos.get(0), listaProdutos.get(3).preco, 4, null, STATUS_ITEM.VENDA_NORMAL))
        listaItensPedido.add(ItemPedido(null, listaProdutos.get(0), listaProdutos.get(2).preco, 4, null, STATUS_ITEM.VENDA_NORMAL))
        //
        return listaItensPedido
    }

    fun criarPedido() : Pedido {
        return Pedido(null, popularMockItemsPedido())
    }
}