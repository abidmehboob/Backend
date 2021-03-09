package com.alseyahat.app.feature.attachment.facade;

import org.springframework.web.multipart.MultipartFile;
import com.alseyahat.app.feature.attachment.dto.AttachmentResponse;
import com.alseyahat.app.feature.attachment.dto.AttachmentUploadRequest;
import com.alseyahat.app.feature.attachment.dto.AttachmentUploadResponse;

public interface AttachmentFacade {
    AttachmentResponse createAttachment(final String module,final MultipartFile file,final String fileExtension);
    AttachmentUploadResponse uploadAttachment(final AttachmentUploadRequest attachmentUploadRequest);
    AttachmentUploadResponse uploadAttachment(final String module, final MultipartFile file);
}