package br.ufc.tpii.vmsys;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;
import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    /**
     * autor: fernanda
     * 
     * @throws ItemAlreadyAdded
     */
    // @BeforeEach
    // public void setUp() throws ItemAlreadyAdded {
    // HashMapInventory inventory = new HashMapInventory();
    // Item item1 = new Item("Chá", 2.0, 10);

    // inventory.addItem(item1);

    // vendingMachine = new VendingMachine(inventory);
    // }

    @Test
    public void testAddCoins() throws ItemAlreadyAdded {
        HashMapInventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá", 2.0, 10);

        inventory.addItem(item1);

        vendingMachine = new VendingMachine(inventory);

        vendingMachine.addCoins(1.0);
        vendingMachine.addCoins(0.5);
        vendingMachine.addCoins(-0.5);
        assertEquals(vendingMachine.howManyCoinsLeft(), 1.5);
    }

    @Test
    public void testWithdrawRemainingCoins() throws ItemAlreadyAdded {

        HashMapInventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá", 2.0, 10);

        inventory.addItem(item1);

        vendingMachine = new VendingMachine(inventory);
        vendingMachine.addCoins(1.0);
        vendingMachine.addCoins(0.5);
        assertEquals(vendingMachine.withdrawRemainingCoins(), 1.5);
        assertEquals(vendingMachine.howManyCoinsLeft(), 0.0);
    }

    @Test
    public void testHowManyCoinsLeft() throws ItemAlreadyAdded {
        HashMapInventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá", 2.0, 10);

        inventory.addItem(item1);

        vendingMachine = new VendingMachine(inventory);

        assertEquals(vendingMachine.howManyCoinsLeft(), 0.0);
        vendingMachine.addCoins(1.0);
        vendingMachine.addCoins(0.5);
        assertEquals(vendingMachine.howManyCoinsLeft(), 1.5);
    }

    @Test
    public void testVendWithInvalidSelection() throws ItemAlreadyAdded, OutOfStock, InsufficientFunds {
        Inventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá", 2.0, 10);

        inventory.addItem(item1);

        VendingMachine vendingMachine = new VendingMachine(inventory);
        Exception exception = null;
        try {
            vendingMachine.vend("InvalidItem");
        } catch (InvalidSelection e) {
            exception = e;
            new InvalidSelection();
        }

        assertNotNull(exception);
        assertEquals(InvalidSelection.class, exception.getClass());
    }

    @Test
    public void testVendWithOutOfStock() throws ItemAlreadyAdded, InvalidSelection, InsufficientFunds {

        Inventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá Matte", 2.0, 5);
        inventory.addItem(item1);

        VendingMachine vendingMachine = new VendingMachine(inventory);
        vendingMachine.addCoins(10.0);
        Exception exception = null;

        try {
            vendingMachine.vend("Chá Matte");
            vendingMachine.vend("Chá Matte");
            vendingMachine.vend("Chá Matte");
            vendingMachine.vend("Chá Matte");
            vendingMachine.vend("Chá Matte");
            vendingMachine.vend("Chá Matte");
        } catch (OutOfStock e) {
            exception = e;
            new OutOfStock();
        }

        assertNotNull(exception);
        assertEquals(OutOfStock.class, exception.getClass());
    }

    @Test
    public void testVendWithInsufficientFunds() throws ItemAlreadyAdded, InvalidSelection, OutOfStock {
        HashMapInventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá", 2.0, 10);

        inventory.addItem(item1);

        vendingMachine = new VendingMachine(inventory);
        double coinValue = 1.0;
        vendingMachine.addCoins(coinValue);
        try {
            vendingMachine.vend("Chá");
            fail("Expected InsufficientFunds exception not thrown");
        } catch (InsufficientFunds e) {
            // Exceção esperada capturada
            new InsufficientFunds();
        }
    }

    @Test
    public void testVend() throws InvalidSelection, OutOfStock, InsufficientFunds, ItemNotFound, ItemAlreadyAdded {
        HashMapInventory inventory = new HashMapInventory();
        Item item1 = new Item("Chá", 2.0, 10);

        inventory.addItem(item1);
        vendingMachine = new VendingMachine(inventory);

        vendingMachine.addCoins(2.0);
        vendingMachine.vend("Chá");

        assertEquals(vendingMachine.howManyCoinsLeft(), 0.0);

        Item item = vendingMachine.getInventory().getItem("Chá");
        assertEquals(item.getCount(), 9);
    }
}
