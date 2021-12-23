package com.app.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.springcloud.entity.Coupon;
import com.app.springcloud.repository.CouponRepo;

@RestController
@RequestMapping("/couponapi")
public class CouponController {
	
	Logger logger = LoggerFactory.getLogger(CouponController.class.getName());
	
	@Autowired
	private CouponRepo couponRepo;

	@RequestMapping(value = "/coupon", method = RequestMethod.POST)
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
		ResponseEntity<Coupon> responseEntity = null;
		Coupon created = null;
		try {
			if(coupon != null) {
				created = couponRepo.save(coupon);
				responseEntity = new ResponseEntity<Coupon>(created, HttpStatus.OK);
			}
		} catch(Exception exception) {
			logger.error("Exception while saving Coupon Details : " + exception.getMessage());
			exception.printStackTrace();
		}		
		return responseEntity;
	}
	
	@RequestMapping(value = "/coupons/{code}", method = RequestMethod.GET)
	public ResponseEntity<Coupon> getCoupon(@PathVariable("code") String code) {
		Coupon coupon = null;
		ResponseEntity<Coupon> responseEntity = null;
		try {
			coupon = couponRepo.findByCode(code);
			responseEntity  = new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		} catch(Exception exception) {
			logger.error("Exception while saving Coupon Details : " + exception.getMessage());
			exception.printStackTrace();
		}		
		return responseEntity;
	}
}
