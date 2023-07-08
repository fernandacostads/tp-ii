package br.ufc.tpii.vmsys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

public class HashMapInventoryTest {

    @Test
    public void testAddItem() throws ItemAlreadyAdded, ItemNotFound {
        HashMapInventory inventory = new HashMapInventory();
        Item item = new Item("Matte leão", 3.5, 1);
        inventory.addItem(item);

        Item retrievedItem = inventory.getItem("Matte leão");
        assertEquals(item, retrievedItem);
    }

    @Test
    public void testAddExistingItem() throws ItemAlreadyAdded {
        Inventory inventory = new HashMapInventory();
        Item item1 = new Item("Matte leão", 3.5, 1);
        Item item2 = new Item("Matte leão", 3.5, 1);

        inventory.addItem(item1);

        Exception exception = null;
        try {
            inventory.addItem(item2);
        } catch (ItemAlreadyAdded e) {
            exception = e;
            new ItemAlreadyAdded();
        }

        assertNotNull(exception);
        assertEquals(ItemAlreadyAdded.class, exception.getClass());
    }

    @Test
    public void testRemoveItem() throws ItemAlreadyAdded, ItemNotFound {
        HashMapInventory inventory = new HashMapInventory();
        Item item = new Item("Matte leão", 3.5, 1);
        inventory.addItem(item);

        inventory.removeItem("Matte leão");
        assertEquals(0, inventory.numberOfItems());
    }

    @Test
    public void testRemoveNonExistindItem() {
        Inventory inventory = new HashMapInventory();
        Exception exception = null;
        try {
            inventory.removeItem("Matte leão");
        } catch (ItemNotFound e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(ItemNotFound.class, exception.getClass());
    }

    @Test
    public void testGetItem() throws ItemAlreadyAdded, ItemNotFound {
        HashMapInventory inventory = new HashMapInventory();
        Item item = new Item("Matte leão", 3.5, 1);
        inventory.addItem(item);

        Item retrievedItem = inventory.getItem("Matte leão");
        assertEquals(item, retrievedItem);
    }

    @Test
    public void testGetNonExistingItem() {
        Inventory inventory = new HashMapInventory();

        Exception exception = null;
        try {
            inventory.getItem("Matte leão");
        } catch (ItemNotFound e) {
            exception = e;
            new ItemNotFound();
        }

        assertNotNull(exception);
        assertEquals(ItemNotFound.class, exception.getClass());
    }

    @Test
    public void testNumberOfItems() throws ItemAlreadyAdded {
        HashMapInventory inventory = new HashMapInventory();
        Item item1 = new Item("Matte leão", 3.5, 1);
        Item item2 = new Item("Watter", 2.0, 1);
        inventory.addItem(item1);
        inventory.addItem(item2);

        assertEquals(2, inventory.numberOfItems());
    }
}