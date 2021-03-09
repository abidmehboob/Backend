package com.alseyahat.app.feature.attachment.controller;

import com.alseyahat.app.feature.attachment.dto.AttachmentUploadRequest;
import com.alseyahat.app.feature.attachment.dto.AttachmentUploadResponse;
import com.alseyahat.app.feature.attachment.dto.AttachmentResponse;
import com.alseyahat.app.feature.attachment.facade.AttachmentFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@Api(tags = "Attachment")
@RequestMapping("/attachments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentController {

    AttachmentFacade attachmentFacade;

    @PostMapping
    @ApiOperation(value = "Create attachment", nickname = "createAttachment", notes = "Create attachment")
    public ResponseEntity<AttachmentResponse> createAttachment(@RequestParam("module") final String module,
    		@RequestParam("file") final MultipartFile file,@RequestParam("fileExtension") final String fileExtension) {
//        if (!AppUtils.validateFileExtensions(attachmentRequest.getFileExtension())) {
//            throw new ServiceException(ErrorCodeEnum.INVALID_PARAMETER, "Invalid File Type. Supported types (.jpg, .jpeg, .png, .gif)");
//        }
        return ResponseEntity.ok(attachmentFacade.createAttachment(module,file,fileExtension));
    }

    @PostMapping(value = "/upload-base64", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Upload attachment", nickname = "uploadAttachment", notes = "Upload attachment")
    public ResponseEntity<AttachmentUploadResponse> uploadAttachmentBase64(@Valid @RequestBody AttachmentUploadRequest attachmentUploadRequest) {
        return ResponseEntity.ok(attachmentFacade.uploadAttachment(attachmentUploadRequest));
    }
    
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Upload attachment", nickname = "uploadAttachment", notes = "Upload attachment")
    public ResponseEntity<AttachmentUploadResponse> uploadAttachment(@RequestParam("module") final String module,
    		@RequestParam("file") final MultipartFile file) {
        return ResponseEntity.ok(attachmentFacade.uploadAttachment(module, file));
    }

}