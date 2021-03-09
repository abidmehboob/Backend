package com.alseyahat.app.feature.hotel.facade.impl;

import static com.alseyahat.app.constant.RoleConstant.PERMISSION_DENIED;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.commons.AppUtils;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.customer.repository.entity.QCustomer;
import com.alseyahat.app.feature.customer.service.CustomerService;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.alseyahat.app.feature.employee.repository.entity.QEmployee;
import com.alseyahat.app.feature.employee.service.EmployeeService;
import com.alseyahat.app.feature.hotel.dto.HotelBookingCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelBookingCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelBookingDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelBookingUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelBookingUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelBookingFacade;
import com.alseyahat.app.feature.hotel.repository.entity.HotelBooking;
import com.alseyahat.app.feature.hotel.repository.entity.QHotel;
import com.alseyahat.app.feature.hotel.repository.entity.QHotelBooking;
import com.alseyahat.app.feature.hotel.service.HotelBookingService;
import com.alseyahat.app.feature.hotel.service.HotelService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelBookingFacadeImpl implements HotelBookingFacade {
	
	HotelBookingService hotelBookingService;
	
    ModelMapper modelMapper;
    HotelService hotelService;
    CustomerService customerService;
    EmployeeService employeeService;
	
	@Override
	public void deleteHotelBooking(String hotelBookingId) {
		final String username = AppUtils.getUserNameFromAuthentication();
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
		HotelBooking hotelBooking = hotelBookingService.findOne(QHotelBooking.hotelBooking.hotelBookingId.eq(hotelBookingId));
		if (hotelBooking.isEnabled())
			hotelBooking.setEnabled(Boolean.FALSE);
		else
			hotelBooking.setEnabled(Boolean.TRUE);

		hotelBookingService.save(hotelBooking);
		log.trace("HotelBooking deleted with id [{}]", hotelBookingId);
		} else
			throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
	}

	@Override
	public HotelBookingDetailResponse findHotelBookingId(String hotelBookingId) {
		final String username = AppUtils.getUserNameFromAuthentication();
		HotelBooking hotelBooking;
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
		 hotelBooking = hotelBookingService.findOne(QHotelBooking.hotelBooking.hotelBookingId.eq(hotelBookingId));
		return buildHotelBookingDetailResponse(hotelBooking);
		} else {
			final Customer customer = customerService.findOne(QCustomer.customer.phone.eq(username));
			hotelBooking = hotelBookingService.findOne(QHotelBooking.hotelBooking.hotelBookingId.eq(hotelBookingId));
			if (!hotelBooking.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
				throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
			} else {
				return buildHotelBookingDetailResponse(hotelBooking);
			}
		}
	}

	@Override
	public HotelBookingCreateResponse createHotelBooking(HotelBookingCreateRequest request) {
		final String username = AppUtils.getUserNameFromAuthentication();
		if (customerService.exist(QCustomer.customer.phone.eq(username))) {
			final HotelBooking savedHotelBooking = hotelBookingService.save(buildHotelBooking(request));
			return HotelBookingCreateResponse.builder().hotelBookingId(savedHotelBooking.getHotelBookingId()).build();
		} else
			throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
	}

	@Override
	public HotelBookingUpdateResponse updateHotelBooking(HotelBookingUpdateRequest request) {
		final String username = AppUtils.getUserNameFromAuthentication();
		HotelBooking hotelBooking;
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
		hotelBooking = hotelBookingService.findOne(QHotelBooking.hotelBooking.hotelBookingId.eq(request.getHotelBookingId()));
		final HotelBooking savedHotelBooking = hotelBookingService.save(buildHotelBooking(hotelBooking, request));
		return HotelBookingUpdateResponse.builder().hotelBookingId(savedHotelBooking.getHotelBookingId()).build();
		} else {
			final Customer customer = customerService.findOne(QCustomer.customer.phone.eq(username));
			hotelBooking = hotelBookingService
					.findOne(QHotelBooking.hotelBooking.hotelBookingId.eq(request.getHotelBookingId()));
			if (!hotelBooking.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
				throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
			} else {
				final HotelBooking savedHotelBooking = hotelBookingService.save(buildHotelBooking(hotelBooking, request));
				return HotelBookingUpdateResponse.builder().hotelBookingId(savedHotelBooking.getHotelBookingId()).build();
			}

		}
	}

	@Override
	public Page<HotelBookingDetailResponse> findAllHotelBooking(Predicate predicate, Pageable pageable) {
		final String username = AppUtils.getUserNameFromAuthentication();
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
		return hotelBookingService.findAll(predicate, pageable).map(this::buildHotelBookingDetailResponse);
		}else {
			final Customer customer = customerService.findOne(QCustomer.customer.phone.eq(username));
			return hotelBookingService.findAll(ExpressionUtils.allOf(predicate, QHotelBooking.hotelBooking.customer.customerId.eq(customer.getCustomerId())), pageable).map(this::buildHotelBookingDetailResponse);
		}
	}
	
	private HotelBookingDetailResponse buildHotelBookingDetailResponse(final HotelBooking hotelBooking) {
		HotelBookingDetailResponse response = new HotelBookingDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<HotelBooking, HotelBookingDetailResponse> typeMap = modelMapper.typeMap(HotelBooking.class, HotelBookingDetailResponse.class);
		typeMap.map(hotelBooking, response);
		response.setHotel(hotelBooking.getHotel());
		response.setCustomer(hotelBooking.getCustomer());
		return response;
	}
	
	private HotelBooking buildHotelBooking(final HotelBookingCreateRequest request) {
		HotelBooking hotelBooking = new HotelBooking();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<HotelBookingCreateRequest, HotelBooking> typeMap = modelMapper.typeMap(HotelBookingCreateRequest.class, HotelBooking.class);
		typeMap.addMappings(mapper -> mapper.skip(HotelBooking::setHotelBookingId));
		typeMap.addMappings(mapper -> mapper.skip(HotelBooking::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(HotelBooking::setLastUpdated));
		typeMap.map(request, hotelBooking);
		hotelBooking.setHotel(hotelService.findOne(QHotel.hotel.hotelId.eq(request.getHotelId())));
		String username = AppUtils.getUserNameFromAuthentication();
	 	hotelBooking.setCustomer(customerService.findOne(QCustomer.customer.phone.eq(username)));
//        category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return hotelBooking;
	}
	
	private HotelBooking buildHotelBooking(HotelBooking hotel, final HotelBookingUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<HotelBookingUpdateRequest, HotelBooking> typeMap = modelMapper.typeMap(HotelBookingUpdateRequest.class, HotelBooking.class);
		typeMap.addMappings(mapper -> mapper.skip(HotelBooking::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(HotelBooking::setLastUpdated));
		typeMap.map(request, hotel);
		// category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return hotel;
	}


}

