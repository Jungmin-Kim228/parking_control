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
    private CarScanner carScanner;

    @BeforeEach
    void setUp() {
        carScanner = mock(CarScanner.class);
        parkingLot = new ParkingLot();
        parkingService = new ParkingService(parkingLot);
    }

    @DisplayName("입차 차량 번호 스캔")
    @Test
    void scanCarNumber() {
        String number = "1234";
        Car car = new Car(number, 0);

        when(carScanner.scan(car)).thenReturn(car.getNum());
        String result = carScanner.scan(car);

        assertThat(result).isEqualTo(number);
    }

    @DisplayName("A-1 구역 차량 주차 및 출차")
    @Test
    void enter_and_leaveCar() {
        String zoneName = "A-1";
        ParkTime parkTime = new ParkTime(0,0,0,0);
        Car car = new Car("1234", 0);

        parkingService.enter(zoneName, car);
        assertThat(parkingLot.getTotalCar()).isEqualTo(1);
        parkingService.leave(zoneName, new ParkTime());
        assertThat(parkingLot.getTotalCar()).isEqualTo(0);
    }

    @DisplayName("출차 시 1000원 요금 지불(시간 계산X)")
    @Test
    void pay() {
        String zoneName = "A-1";
        Car car = new Car("1234", 1000);

        parkingService.enter(zoneName, car);
        parkingService.leave(zoneName, new ParkTime());

        assertThat(car.getMoney()).isZero();
    }

    @DisplayName("돈이 없으면 출차 불가")
    @Test
    void leaveCar_noMoney_throwNoMoneyException() {
        String zoneName = "A-1";
        Car car = new Car("1234", 0);

        parkingService.enter(zoneName, car);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> parkingService.leave(zoneName, new ParkTime()))
            .withMessageContainingAll("no money", Integer.toString(car.getMoney()));
    }

    @DisplayName("주차 일, 시간, 분, 초 -> 초 변환")
    @Test
    void ParkingService_getTotalSec() {
        String zoneName = "A-1";
        ParkTime leaveTime = new ParkTime(0, 1, 40, 1);
        Car car = new Car("1234", 10000);

        parkingService.enter(zoneName, car);

        assertThat(parkingService.getTotalSec(leaveTime)).isEqualTo(6001);
    }

    @DisplayName("초로 변환된 주차 시간으로 요금 계산")
    @Test
    void calculateFee() {
        ParkTime leaveTime1 = new ParkTime(0,0,30,1);
        ParkTime leaveTime2 = new ParkTime(0,0,50,0);
        ParkTime leaveTime3 = new ParkTime(0,1,1,0);
        ParkTime leaveTime4 = new ParkTime(0,6,0,0);
        ParkTime leaveTime5 = new ParkTime(1,2,0,0);
        ParkTime leaveTime6 = new ParkTime(2,0,1,0);

        assertThat(parkingService.calculateFee(parkingService.getTotalSec(leaveTime1))).isEqualTo(1500);
        assertThat(parkingService.calculateFee(parkingService.getTotalSec(leaveTime2))).isEqualTo(2000);
        assertThat(parkingService.calculateFee(parkingService.getTotalSec(leaveTime3))).isEqualTo(3000);
        assertThat(parkingService.calculateFee(parkingService.getTotalSec(leaveTime4))).isEqualTo(10000);
        assertThat(parkingService.calculateFee(parkingService.getTotalSec(leaveTime5))).isEqualTo(15500);
        assertThat(parkingService.calculateFee(parkingService.getTotalSec(leaveTime6))).isEqualTo(21000);
    }

    @DisplayName("30분 주차 요금 1000원이 없어 출차 불가")
    @Test
    void parked30minute_noMoney_throwNoMoneyException() {
        Car car = new Car("1234", 500);
        String zoneName = "A-1";
        ParkTime leaveTime = new ParkTime(0,0,20,0);

        parkingService.enter(zoneName, car);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> parkingService.leave(zoneName, leaveTime))
            .withMessageContainingAll("no money", Integer.toString(car.getMoney()));
    }
}