package com.alseyahat.app.feature.attachment.service;

import java.util.Map;

import com.alseyahat.app.feature.attachment.dto.AttachmentUploadRequest;

public interface AttachmentService {

	Map<String, Object> uploadFile(final AttachmentUploadRequest attachmentUploadRequest);
}
