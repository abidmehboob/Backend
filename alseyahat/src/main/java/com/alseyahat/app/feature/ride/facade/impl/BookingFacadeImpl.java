package com.alseyahat.app.feature.ride.facade.impl;


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
import com.alseyahat.app.feature.ride.dto.BookingCreateRequest;
import com.alseyahat.app.feature.ride.dto.BookingCreateResponse;
import com.alseyahat.app.feature.ride.dto.BookingDetailResponse;
import com.alseyahat.app.feature.ride.dto.BookingUpdateRequest;
import com.alseyahat.app.feature.ride.dto.BookingUpdateResponse;
import com.alseyahat.app.feature.ride.facade.BookingFacade;
import com.alseyahat.app.feature.ride.repository.entity.QBooking;
import com.alseyahat.app.feature.ride.repository.entity.Booking;
import com.alseyahat.app.feature.ride.service.BookingService;
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
public class BookingFacadeImpl implements BookingFacade {

	BookingService bookingService;
	ModelMapper modelMapper;
	CustomerService customerService;
	EmployeeService employeeService;
	
	@Override
	public void deleteBooking(String bookingId) {
		Booking booking = bookingService.findOne(QBooking.booking.bookingId.eq(bookingId));
		if (booking.isEnabled())
			booking.setEnabled(Boolean.FALSE);
		else
			booking.setEnabled(Boolean.TRUE);

		bookingService.save(booking);
		log.trace("Booking deleted with id [{}]", bookingId);
	}

	@Override
	public BookingDetailResponse findBookingId(String bookingId) {
		Booking booking;
		final String username = AppUtils.getUserNameFromAuthentication();
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
			booking = bookingService.findOne(QBooking.booking.bookingId.eq(bookingId));
		}else {
			final Customer customer = customerService.findOne(QCustomer.customer.personalKey.eq(username));
			booking = bookingService.findOne(QBooking.booking.bookingId.eq(bookingId));
			if(!booking.getCustomer().getCustomerId().equals(customer.getCustomerId()))
				throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
		}
		return buildBookingDetailResponse(booking);
	}

	@Override
	public BookingCreateResponse createBooking(BookingCreateRequest request) {
		
		final String username = AppUtils.getUserNameFromAuthentication();
		if(customerService.exist(QCustomer.customer.personalKey.eq(username))) {
			final Booking savedBooking = bookingService.save(buildBooking(request));
			return BookingCreateResponse.builder().BookingId(savedBooking.getBookingId()).build();
		}else
			throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
	}

	@Override
	public BookingUpdateResponse updateBooking(BookingUpdateRequest request) {
		
		final String username = AppUtils.getUserNameFromAuthentication();
		Booking booking;
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
			booking = bookingService.findOne(QBooking.booking.bookingId.eq(request.getBookingId()));
		final Booking savedBooking = bookingService.save(buildBooking(booking, request));
		return BookingUpdateResponse.builder().BookingId(savedBooking.getBookingId()).build();
		}else {
			final Customer customer = customerService.findOne(QCustomer.customer.personalKey.eq(username));
			booking = bookingService.findOne(QBooking.booking.bookingId.eq(request.getBookingId()));
			if(!booking.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
				throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
			}else {
				final Booking savedBooking = bookingService.save(buildBooking(booking, request));
				return BookingUpdateResponse.builder().BookingId(savedBooking.getBookingId()).build();
			}
				
		}
		
	}

	@Override
	public Page<BookingDetailResponse> findAllBooking(Predicate predicate, Pageable pageable) {
		final String username = AppUtils.getUserNameFromAuthentication();
		final Optional<Employee> employee = employeeService
				.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
			return bookingService.findAll(predicate, pageable).map(this::buildBookingDetailResponse);
		}else {
			final Customer customer = customerService.findOne(QCustomer.customer.personalKey.eq(username));
			return bookingService.findAll(ExpressionUtils.allOf(predicate, QBooking.booking.customer.customerId.eq(customer.getCustomerId())), pageable).map(this::buildBookingDetailResponse);
		}
	}
	
	private BookingDetailResponse buildBookingDetailResponse(final Booking booking) {
		BookingDetailResponse response = new BookingDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<Booking, BookingDetailResponse> typeMap = modelMapper.typeMap(Booking.class, BookingDetailResponse.class);
		typeMap.map(booking, response);
		response.setCustomer(booking.getCustomer());

		return response;
	}
	
	private Booking buildBooking(final BookingCreateRequest request) {
		Booking booking = new Booking();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<BookingCreateRequest, Booking> typeMap = modelMapper.typeMap(BookingCreateRequest.class, Booking.class);
		typeMap.addMappings(mapper -> mapper.skip(Booking::setBookingId));
		typeMap.addMappings(mapper -> mapper.skip(Booking::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Booking::setLastUpdated));
		typeMap.map(request, booking);
		String username = AppUtils.getUserNameFromAuthentication();
		booking.setCustomer(customerService.findOne(QCustomer.customer.personalKey.eq(username)));
//        category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return booking;
	}
	
	private Booking buildBooking(Booking booking, final BookingUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<BookingUpdateRequest, Booking> typeMap = modelMapper.typeMap(BookingUpdateRequest.class, Booking.class);
		typeMap.addMappings(mapper -> mapper.skip(Booking::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Booking::setLastUpdated));
		typeMap.map(request, booking);
		// category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return booking;
	}


}
