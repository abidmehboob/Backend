package com.alseyahat.app.feature.attachment.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alseyahat.app.feature.attachment.dto.AttachmentUploadRequest;
import com.alseyahat.app.feature.attachment.properties.FileStorageProperties;

@FeignClient(name = "${attachment.feign-url}", url = "${attachment.feign-url}", configuration = FileStorageProperties.class)
public interface AttachmentUploadFeignClient {

	@PostMapping(value = "/upload/image", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> uploadImage(@RequestBody final AttachmentUploadRequest request);
}
