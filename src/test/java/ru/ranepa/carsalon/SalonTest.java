package ru.ranepa.carsalon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalonTest {

    private Salon createSalonWithTwoVehicles() {
        Salon salon = new Salon();
        salon.addVehicle(new PassengerCar("Toyota", "Camry", 2020, 2000000, 5));
        salon.addVehicle(new Truck("Volvo", "FH", 2019, 6000000, 18));
        salon.addClient(new Client("Ivan", "89991234567"));
        return salon;
    }

    @Test
    void averagePriceOfEmptyCatalogIsZero() {
        Salon salon = new Salon();
        assertEquals(0, salon.getAveragePrice(), 0.001);
    }

    @Test
    void mostExpensiveOfEmptyCatalogIsNull() {
        Salon salon = new Salon();
        assertNull(salon.getMostExpensive());
    }

    @Test
    void averagePriceIsCalculatedCorrectly() {
        Salon salon = createSalonWithTwoVehicles();
        assertEquals(4000000, salon.getAveragePrice(), 0.001);
    }

    @Test
    void mostExpensiveIsTruck() {
        Salon salon = createSalonWithTwoVehicles();
        assertEquals("Volvo", salon.getMostExpensive().getBrand());
    }

    @Test
    void sellingSoldVehicleAgainReturnsFalse() {
        Salon salon = createSalonWithTwoVehicles();
        assertTrue(salon.sellVehicle(0, 0));
        assertFalse(salon.sellVehicle(0, 0));
    }

    @Test
    void soldCountGrowsAfterSale() {
        Salon salon = createSalonWithTwoVehicles();
        assertEquals(0, salon.getSoldCount());
        salon.sellVehicle(0, 0);
        assertEquals(1, salon.getSoldCount());
    }

    @Test
    void testDriveForSoldVehicleIsNotAllowed() {
        Salon salon = createSalonWithTwoVehicles();
        salon.sellVehicle(0, 0);
        assertFalse(salon.bookTestDrive(0, 0));
    }

    @Test
    void testDriveForAvailableVehicleIsAllowed() {
        Salon salon = createSalonWithTwoVehicles();
        assertTrue(salon.bookTestDrive(1, 0));
    }

    @Test
    void sellWithWrongIndexReturnsFalse() {
        Salon salon = createSalonWithTwoVehicles();
        assertFalse(salon.sellVehicle(5, 0));
        assertFalse(salon.sellVehicle(0, 5));
    }

    @Test
    void vehicleWithFutureYearIsNotCreated() {
        Salon salon = new Salon();
        boolean errorHappened = false;
        try {
            salon.addVehicle(new PassengerCar("Ford", "Focus", 2035, 500000, 5));
        } catch (IllegalArgumentException e) {
            errorHappened = true;
        }
        assertTrue(errorHappened);
        assertEquals(0, salon.getCatalog().size());
    }

    @Test
    void searchByUnknownBrandReturnsEmptyList() {
        Salon salon = createSalonWithTwoVehicles();
        assertTrue(salon.search("BMW").isEmpty());
    }

    @Test
    void searchIgnoresUpperAndLowerCase() {
        Salon salon = createSalonWithTwoVehicles();
        assertEquals(1, salon.search("toyota").size());
        assertEquals(1, salon.search("FH").size());
    }

    @Test
    void searchWithEmptyTextReturnsEmptyList() {
        Salon salon = createSalonWithTwoVehicles();
        assertTrue(salon.search("").isEmpty());
    }

    @Test
    void filterReturnsOnlyCheaperVehicles() {
        Salon salon = createSalonWithTwoVehicles();
        assertEquals(1, salon.findCheaperThan(3000000).size());
    }

    @Test
    void filterWithTooSmallLimitReturnsEmptyList() {
        Salon salon = createSalonWithTwoVehicles();
        assertTrue(salon.findCheaperThan(1000).isEmpty());
    }
}