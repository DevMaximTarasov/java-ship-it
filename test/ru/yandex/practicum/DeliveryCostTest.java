package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.FragileParcel;
import ru.yandex.practicum.delivery.parcel.ParcelBox;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;
import ru.yandex.practicum.delivery.parcel.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {
    private static ParcelBox<StandardParcel> standardParcelBox;
    private static ParcelBox<FragileParcel> fragileParcelBox;
    private static ParcelBox<PerishableParcel> perishableParcelBox;
    private static StandardParcel standardParcel;
    private static FragileParcel fragileParcel;
    private static PerishableParcel perishableParcel;

    @BeforeEach
    public void setUp() {
        standardParcelBox = new ParcelBox<>(50);
        fragileParcelBox = new ParcelBox<>(50);
        perishableParcelBox = new ParcelBox<>(50);
        standardParcel =
                new StandardParcel("Standard Parcel", 10, "Moscow", 11);
        fragileParcel =
                new FragileParcel("Fragile Parcel", 10, "Praga", 5);
        perishableParcel =
                new PerishableParcel("Perishable Parcel", 10, "Milan", 3, 14);

    }

    @Test
    public void deliveryCostForStandardWithWeight10ShouldBe20() {
        assertEquals(20, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void deliveryCostForFragileWithWeight10ShouldBe40() {
        assertEquals(40, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void deliveryCostForPerishableWithWeight10ShouldBe30() {
        assertEquals(30, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void checkPerishableParcelShouldBeExpired() {
        perishableParcel.setTimeToLive(15);
        perishableParcel.setSendDay(3);
        assertTrue(perishableParcel.isExpired(19));
    }

    @Test
    public void checkPerishableParcelNotShouldBeExpired() {
        perishableParcel.setTimeToLive(15);
        perishableParcel.setSendDay(3);
        assertFalse(perishableParcel.isExpired(18));
    }

    @Test
    public void standardParcelShouldBeAddedToBoxIfWeightLessThanMaximum() {
        int startSize = standardParcelBox.getAllParcels().size();
        standardParcelBox.addParcel(standardParcel);
        assertEquals(startSize + 1, standardParcelBox.getAllParcels().size());
    }

    @Test
    public void standardParcelShouldNotBeAddedToBoxIfWeightMoreThanMaximum() {
        int startSize;
        StandardParcel standardParcelTwo =
                new StandardParcel("Standard Parcel", 41, "Moscow", 10);
        standardParcelBox.addParcel(standardParcel);
        startSize = standardParcelBox.getAllParcels().size();
        standardParcelBox.addParcel(standardParcelTwo);
        assertEquals(startSize, standardParcelBox.getAllParcels().size());

    }

    @Test
    public void fragileParcelShouldBeAddedToBoxIfWeightLessThanMaximum() {
        int startSize = fragileParcelBox.getAllParcels().size();
        fragileParcelBox.addParcel(fragileParcel);
        assertEquals(startSize + 1, fragileParcelBox.getAllParcels().size());
    }

    @Test
    public void fragileParcelShouldNotBeAddedToBoxIfWeightMoreThanMaximum() {
        int startSize = fragileParcelBox.getAllParcels().size();
        fragileParcel.setWeight(51);
        fragileParcelBox.addParcel(fragileParcel);
        assertEquals(startSize, fragileParcelBox.getAllParcels().size());

    }

    @Test
    public void perishableParcelShouldBeAddedToBoxIfWeightLessThanMaximum() {
        int startSize = perishableParcelBox.getAllParcels().size();
        perishableParcelBox.addParcel(perishableParcel);
        assertEquals(startSize + 1, perishableParcelBox.getAllParcels().size());
    }

    @Test
    public void perishableParcelShouldNotBeAddedToBoxIfWeightMoreThanMaximum() {
        int startSize = perishableParcelBox.getAllParcels().size();
        perishableParcel.setWeight(51);
        perishableParcelBox.addParcel(perishableParcel);
        assertEquals(startSize, perishableParcelBox.getAllParcels().size());
    }
}
