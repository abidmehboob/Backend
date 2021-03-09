package com.alseyahat.app.feature.attachment.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alseyahat.app.feature.attachment.dto.AttachmentUploadRequest;
import com.alseyahat.app.feature.attachment.feign.AttachmentUploadFeignClient;
import com.alseyahat.app.feature.attachment.service.AttachmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AttachmentServiceImpl implements AttachmentService{
	
	AttachmentUploadFeignClient attachmentUploadFeignClient;

	@Override
	public Map<String, Object> uploadFile(AttachmentUploadRequest attachmentUploadRequest) {
		log.trace("Uploading file request [{}] ", attachmentUploadRequest.getBase64image());
		return attachmentUploadFeignClient.uploadImage(attachmentUploadRequest); 
		
	}

}
