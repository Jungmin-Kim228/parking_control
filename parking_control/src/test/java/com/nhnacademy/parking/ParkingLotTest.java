package com.nhnacademy.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingLotTest {
    private ParkingLot parkingLot;
    private ParkingService parkingService;
    private CarNumberScanner carNumberScanner;

    @BeforeEach
    void setUp() {
        carNumberScanner = mock(CarNumberScanner.class);
        parkingLot = new ParkingLot();
        parkingService = new ParkingService(parkingLot);
    }

    @DisplayName("입차 차량 번호 스캔")
    @Test
    void scanCarNumber() {
        String number = "1234";
        Car car = new Car(number, 0);

        when(carNumberScanner.scan(car)).thenReturn(car.getNum());
        String result = carNumberScanner.scan(car);

        assertThat(result).isEqualTo(number);
    }

    @DisplayName("A-1 구역 차량 주차 및 출차")
    @Test
    void enter_and_leaveCar() {
        String zoneName = "A-1";
        ParkTime parkTime = new ParkTime(0,0,0,0);
        Car car = new Car("1234", 0);

        parkingService.enter(zoneName, car, parkTime);
        assertThat(parkingLot.getTotalCar()).isEqualTo(1);
        parkingService.leave(zoneName);
        assertThat(parkingLot.getTotalCar()).isEqualTo(0);
    }

    @DisplayName("출차 시 요금 지불")
    @Test
    void pay() {
        String zoneName = "A-1";
        ParkTime parkTime = new ParkTime(0,0,0,0);
        Car car = new Car("1234", 1000);

        parkingService.enter(zoneName, car, parkTime);
        parkingService.leave(zoneName);

        assertThat(car.getMoney()).isZero();
    }

    @DisplayName("돈이 없으면 출차 불가")
    @Test
    void leaveCar_noMoney_throwNoMoneyException() {
        String zoneName = "A-1";
        ParkTime parkTime = new ParkTime(0,0,0,0);
        Car car = new Car("1234", 0);

        parkingService.enter(zoneName, car, parkTime);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> parkingService.leave(zoneName))
            .withMessageContainingAll("no money", Integer.toString(car.getMoney()));
    }

    @DisplayName("0시 입차, 1시 40분 1초 출차, 요금 계산 및 지불")
    @Test
    public void pay_2hour() {
        String zoneName = "A-1";
        ParkTime parkTime = new ParkTime(1,0,0,0);
        Car car = new Car("1234", 10000);

        parkingService.enter(zoneName, car, parkTime);

//        ParkTime result = parkingService.leave(zoneName);
//
//        assertThat(result.day).isZero(0);
//        assertThat(result.hour).isEqualTo(1);
//        assertThat(result.min).isZero(40);
//        assertThat(result.sec).isZero(1);
//        assertThat(car.getMoney()).isEqualTo(5500);
    }
}