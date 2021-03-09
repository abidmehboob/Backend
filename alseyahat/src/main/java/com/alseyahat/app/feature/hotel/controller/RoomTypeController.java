package com.alseyahat.app.feature.hotel.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alseyahat.app.feature.hotel.dto.RoomTypeCreateRequest;
import com.alseyahat.app.feature.hotel.dto.RoomTypeCreateResponse;
import com.alseyahat.app.feature.hotel.dto.RoomTypeDetailResponse;
import com.alseyahat.app.feature.hotel.dto.RoomTypeUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.RoomTypeUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.RoomTypeFacade;
import com.alseyahat.app.feature.hotel.repository.entity.RoomType;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "RoomType")
@RequestMapping("/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeController {

	RoomTypeFacade roomTypeFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create roomType", nickname = "createRoomType", notes = "Create roomType")
	    public ResponseEntity<RoomTypeCreateResponse> createRoomType(@Valid @RequestBody final RoomTypeCreateRequest roomTypeRequest) {
	        return new ResponseEntity<>(roomTypeFacade.createRoomType(roomTypeRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update roomType", nickname = "updateRoomType", notes = "Update roomType")
	    public ResponseEntity<RoomTypeUpdateResponse> updateRoomType(@Valid @RequestBody final RoomTypeUpdateRequest roomTypeUpdateRequest) {
	        return ResponseEntity.ok(roomTypeFacade.updateRoomType(roomTypeUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all RoomTypes", nickname = "getAllRoomTypes", notes = "Get all RoomTypes")
	    public ResponseEntity<Page<RoomTypeDetailResponse>> getAllRoomTypes(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = RoomType.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(roomTypeFacade.findAllRoomType(predicate, pageable));
	    }

	    @GetMapping("/{roomTypeId}")
	    @ApiOperation(value = "Get RoomType by id", nickname = "getRoomTypeById", notes = "Get RoomType by id")
	    public ResponseEntity<RoomTypeDetailResponse> getRoomTypeById(@PathVariable final String roomTypeId) {
	        return ResponseEntity.ok(roomTypeFacade.findRoomTypeId(roomTypeId));
	    }

	    @DeleteMapping("/{roomTypeId}")
	    @ApiOperation(value = "Delete RoomType", nickname = "deleteRoomType", notes = "Delete roomType")
	    public ResponseEntity<Void> deleteRoomType(@PathVariable final String roomTypeId) {
	    	roomTypeFacade.deleteRoomType(roomTypeId);
	        return ResponseEntity.ok().build();
	    }

}
