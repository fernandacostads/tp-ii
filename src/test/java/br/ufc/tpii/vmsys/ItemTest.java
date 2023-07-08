package br.ufc.tpii.vmsys;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ufc.tpii.vmsys.inventory.Item;

public class ItemTest {

    private Item item;

    // @BeforeEach
    // public void setUp() {
    // item = new Item("Item 1", 10.0, 10);
    // }

    // O método testGetName() chama o método getName() da variável item e compara o
    // resultado com uma
    // String esperada usando o método assertEquals(). Neste caso, o teste verifica
    // se o método getName()
    // retorna o valor esperado "Item 1". Se o valor retornado for diferente do
    // valor esperado, o teste falha.
    // Em resumo, esta classe de teste cria uma instância da classe Item e testa se
    // o método getName()
    // retorna o valor esperado para essa instância.
    @Test
    public void testGetName() {
        item = new Item("Item 1", 10.0, 10);
        assertEquals(item.getName(), "Item 1");
    }

    // Dentro do método de teste, verifico se o resultado do método getPrice()
    // da instância item é igual ao valor esperado 10.0 usando o método
    // assertEquals().
    // Portanto, este método de teste verifica se o método getPrice() da classe Item
    // está
    // funcionando corretamente para a instância item criada no método setUp() da
    // classe ItemTest.
    @Test
    public void testGetPrice() {
        item = new Item("Item 1", 10.0, 10);
        assertEquals(item.getPrice(), 10.0);
    }

    // Dentro do método de teste, chamo o método setPrice() da instância item com o
    // valor 15.0,
    // e depois verifico se o resultado do método getPrice() da mesma instância item
    // é igual ao
    // valor esperado 15.0, novamente usando o método assertEquals().
    // Eeste método de teste verifica se o método setPrice() da classe Item está
    // atualizando o preço corretamente para a instância item.
    @Test
    public void testSetPrice() {
        item = new Item("Item 1", 10.0, 10);
        item.setPrice(15.0);
        assertEquals(item.getPrice(), 15.0);
    }

    // Dentro do método de teste, chamo o método getCount() da instância item.
    // Em seguida, verifico se o resultado do método getCount() é igual ao valor
    // esperado 5,
    // sando o método assertEquals(). Eeste método de teste verifica se o método
    // getCount() da classe Item está retornando o valor correto para a instância
    // item.
    @Test
    public void testGetCount() {
        item = new Item("Item 1", 10.0, 10);
        assertEquals(item.getCount(), 10);
    }

    // Dentro do método de teste, chamo o método decCount() da instância item, que
    // deve decrementar
    // a contagem em 1. Em seguida, verifico se o resultado do método getCount() é
    // igual ao valor
    // esperado 4, usando o método assertEquals(). Eeste método de teste verifica se
    // o método decCount() da classe Item está decrementando a contagem corretamente
    // para a instância item.
    @Test
    public void testDecCount() {
        item = new Item("Item 1", 10.0, 10);
        item.decCount();
        assertEquals(item.getCount(), 9);
    }

    // Dentro do método de teste, chamo o método incCount() da instância item, que
    // deve incrementara contagem em 1. Em seguida, verifico se o resultado do
    // método getCount() é igual ao valor esperado 6, usando o método
    // assertEquals().
    @Test
    public void testIncCount() {
        item = new Item("Item 1", 10.0, 10);
        item.incCount();
        assertEquals(item.getCount(), 11);
    }

}
