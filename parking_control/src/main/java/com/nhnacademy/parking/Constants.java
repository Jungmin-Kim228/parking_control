package com.nhnacademy.parking;

public interface Constants {
    int FEE_FIRST_30MIN = 1000;
    int FEE_10MIN = 500;
    int FEE_1DAY = 10000;

    int MIN_TO_SEC = 60;
    int MIN10_TO_SEC = 600;
    int MIN30_TO_SEC = 1800;
    int HOUR_TO_SEC = 3600;
    int DAY_TO_SEC = 86400;

    int MAX_DAY_FEE_SEC = 12600; // 1일 최대 요금치 3시간 30분 -> 12600초
}
