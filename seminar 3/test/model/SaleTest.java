package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private Sale sale;
    private Item apple;
    @BeforeEach
    public void setUp() {
        sale = new Sale();
        apple = new Item("1001", "Milk", 1.00, 0.12);
    }
    @Test
    void addItem() {
        sale.addItem(apple, 2);
        assertEquals(2, sale.getItems().get(0).getQuantity());
        assertEquals(1, sale.getItems().size());
    }
    @Test
    public void AddItem_DuplicateItemIncrementsQuantity() {
        sale.addItem(apple, 2);
        sale.addItem(apple, 3);
        assertEquals(5, sale.getItems().get(0).getQuantity());
    }

    @Test
    public void GetTotalWithoutDiscount() {
        sale.addItem(apple, 2);
        assertEquals(2.00, sale.getTotal(), 0.001);
    }

    @Test
    public void ApplyDiscount() {
        sale.addItem(apple, 2);
        sale.applyDiscount(0.50);
        assertEquals(1.50, sale.getTotal(), 0.001);
    }

    @Test
    public void GetVATTotal() {
        sale.addItem(apple, 2);
        assertEquals(0.24, sale.getVATTotal(), 0.001);
    }
}