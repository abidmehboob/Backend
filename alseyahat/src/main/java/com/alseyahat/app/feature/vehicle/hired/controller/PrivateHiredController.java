package com.alseyahat.app.feature.vehicle.hired.controller;

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
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredCreateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredCreateResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredDetailResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredUpdateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredUpdateResponse;
import com.alseyahat.app.feature.vehicle.hired.facade.PrivateHiredFacade;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHired;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "PrivateveVehicle")
@RequestMapping("/privatevehicles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrivateHiredController {
	PrivateHiredFacade privateHiredFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create privateHired", nickname = "createPrivateHired", notes = "Create privateHired")
	    public ResponseEntity<PrivateHiredCreateResponse> createPrivateHired(@Valid @RequestBody final PrivateHiredCreateRequest privateHiredRequest) {
	        return new ResponseEntity<>(privateHiredFacade.createPrivateHired(privateHiredRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update privateHired", nickname = "updatePrivateHired", notes = "Update privateHired")
	    public ResponseEntity<PrivateHiredUpdateResponse> updatePrivateHired(@Valid @RequestBody final PrivateHiredUpdateRequest privateHiredUpdateRequest) {
	        return ResponseEntity.ok(privateHiredFacade.updatePrivateHired(privateHiredUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all privateHires", nickname = "getAllPrivateHired", notes = "Get all privateHires")
	    public ResponseEntity<Page<PrivateHiredDetailResponse>> getAllPrivateHired(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = PrivateHired.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(privateHiredFacade.findAllPrivateHired(predicate, pageable));
	    }

	    @GetMapping("/{privateHiredId}")
	    @ApiOperation(value = "Get review by id", nickname = "getPrivateHiredById", notes = "Get privateHired by id")
	    public ResponseEntity<PrivateHiredDetailResponse> getPrivateHiredById(@PathVariable final String privateHiredId) {
	        return ResponseEntity.ok(privateHiredFacade.findPrivateHiredId(privateHiredId));
	    }

	    @DeleteMapping("/{privateHiredId}")
	    @ApiOperation(value = "Delete review", nickname = "deleteReview", notes = "Delete review")
	    public ResponseEntity<Void> deletePrivateHired(@PathVariable final String privateHiredId) {
	    	privateHiredFacade.deletePrivateHired(privateHiredId);
	        return ResponseEntity.ok().build();
	    }

}
