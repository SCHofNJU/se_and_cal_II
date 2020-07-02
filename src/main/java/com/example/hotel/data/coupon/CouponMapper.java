package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper {
    int insertCoupon(Coupon coupon);

    List<Coupon> selectByHotelId(Integer hotelId);

    int deleteCouponByHotel(@Param("hotelId") Integer hotelId);
}