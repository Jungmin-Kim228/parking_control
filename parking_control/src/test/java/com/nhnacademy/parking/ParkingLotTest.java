package com.nhnacademy.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingLotTest {
    private ParkingLot parkingLot;
    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        parkingLot = mock(ParkingLot.class);
        parkingService = new ParkingService(parkingLot);
    }

    @DisplayName("입차 차량 번호 스캔")
    @Test
    void scanCarNumber() {
        Car car = new Car(1234, 1);

        assertThat(parkingService.scan(car)).isEqualTo(1234);
    }

    @DisplayName("A-1 주차")
    @Test
    void enter() {
        String zoneName = "A-1";
        Car car = new Car(1234, 1);

        parkingService.enter(zoneName, car);

        verify(parkingLot).inputCar(zoneName, car);
    }

    @DisplayName("출차")
    @Test
    void leaveCar() {
        String zoneName = "A-1";
        Car car = new Car(1234, 1);

        int fee = parkingService.leave(car);

        assertThat(fee).isEqualTo(100);
    }

//    @DisplayName("돈이 없으면 출차 불가")
//    @Test
//    void leaveCar_noMoney_throwNoMoneyException() {
//        Car car = new Car(1234, 0);
//        ParkingZone zone = new ParkingZone("A-1", car);
//
//        when(parkingLot.outCar(car)).thenReturn(car);
//
//        assertThatIllegalArgumentException()
//            .isThrownBy(() -> parkingService.leave(car))
//            .withMessageContainingAll("no money", Integer.toString(car.getMoney()));
//    }
}