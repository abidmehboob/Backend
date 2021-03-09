package com.alseyahat.app.feature.attachment.facade.impl;

import com.alseyahat.app.commons.Constants;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.attachment.dto.AttachmentResponse;
import com.alseyahat.app.feature.attachment.dto.AttachmentUploadRequest;
import com.alseyahat.app.feature.attachment.dto.AttachmentUploadResponse;
import com.alseyahat.app.feature.attachment.facade.AttachmentFacade;
import com.alseyahat.app.feature.attachment.properties.FileStorageProperties;
import com.alseyahat.app.feature.attachment.service.AttachmentService;
import com.alseyahat.app.feature.employee.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentFacadeImpl implements AttachmentFacade {

    FileStorageProperties fileStorageProperties;

    AttachmentService attachmentService;
    
    ModelMapper modelMapper;
       
    EmployeeService employeeService;
    
//    @SneakyThrows
    @Override
    public AttachmentResponse createAttachment(final String module, final MultipartFile file, final String fileExtension) {
//    	 final String username = AppUtils.getUserNameFromAuthentication();
//	        final Optional<Employee> employee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
//	        if(employee.isPresent()){
	        	try {
    	        FTPClient client = createSession();
	            String directory = getDirectory(module);
	            String fileName = generateFileName(fileExtension);
	            boolean isSwitched = switchDirectory(directory, client);
	            log.trace("Uploading file [{}] in directory [{}]", fileName, directory);
	            if(!isSwitched){
	                throw new ServiceException(ErrorCodeEnum.FILE_UPLOAD_FAILED, "error.directory_not_created");
	            }
	            client.enterLocalPassiveMode(); // important!
	            client.setFileType(FTP.BINARY_FILE_TYPE);

	            boolean result = client.storeFile(fileName, file.getInputStream());
	            
	            
	            
//	            byte[] bytes = Base64.getDecoder().decode(file.getBytes());
//	            boolean isUploaded = client.storeFile(fileName, new ByteArrayInputStream(bytes));
	            client.logout();
//	            if(!result){
//	                throw new ServiceException(ErrorCodeEnum.FILE_UPLOAD_FAILED, "error.file_not_uploaded");
//	            }
	            log.trace("File uploaded [{}] in directory [{}]", fileName, directory);
	            AttachmentResponse attachmentResponse = new AttachmentResponse();
	            directory= directory.replace("public_html/","");
	            attachmentResponse.setPath(generateHTTPPath(directory, fileName));
	            return attachmentResponse;
	        	}catch (Exception e) {
					System.out.println(e);
				}
	        	return null;
//	        }else
//	        	 throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
        
    }

    @SneakyThrows
    private String getDirectory(String module) {
        String directory = null;
        switch (module) {
            case Constants.HOTEL_IMAGE:
                directory = fileStorageProperties.getDirectory().getHotelImages();
                break;
            case Constants.DEAL_IMAGE:
                directory = fileStorageProperties.getDirectory().getDealImages();
                break;
            case Constants.SIGHT_SEEING_IMAGE:
                directory = fileStorageProperties.getDirectory().getSightSeeingImages();
                break;
            case Constants.PRIVATE_HIRED_IMAGE:
                directory = fileStorageProperties.getDirectory().getSightSeeingImages();
                break;
            default:
                throw new IllegalStateException("Unexpected attachment module type: " + module);
        }
        return directory;
    }

    @SneakyThrows
    public boolean switchDirectory(String dirPath, FTPClient client) {
        String[] pathElements = dirPath.split("/");
        if (pathElements != null && pathElements.length > 0) {
            for (String singleDir : pathElements) {
                boolean existed = client.changeWorkingDirectory(singleDir);
                if (!existed) {
                    boolean created = client.makeDirectory(singleDir);
                    if (created) {
                        log.trace("Directory created with name [{}]", singleDir);
                        client.changeWorkingDirectory(singleDir);
                    } else {
                        log.trace("Directory couldn't be created with name [{}]", singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String generateFileName(String fileExtension){
        return UUID.randomUUID().toString() + Constants.DOT + fileExtension;
    }

    public String generateHTTPPath(String directory, String fileName){
        return fileStorageProperties.getAccessUrl() + directory + Constants.SLASH + fileName;
    }

    @SneakyThrows
    public FTPClient createSession(){
        FTPClient client = new FTPClient();
        client.connect("168.235.86.155");
        boolean login = client.login("admin_web", "web123");
        if(!login){
            throw new ServiceException(ErrorCodeEnum.FILE_UPLOAD_FAILED, "error.ftp_login_failed");
        }
        client.setFileType(FTP.BINARY_FILE_TYPE);
        return client;
    }

	@Override
	public AttachmentUploadResponse uploadAttachment(AttachmentUploadRequest attachmentUploadRequest) {
		log.trace("File Upload Feign Client Request {[]}", attachmentUploadRequest);
//		 final String username = AppUtils.getUserNameFromAuthentication();
//	        final Optional<Employee> employee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
//	        if(employee.isPresent()){
	        	 return buildAttachmentUploadResponse(attachmentService.uploadFile(attachmentUploadRequest));
//	        }else
//           	 throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
	}
	
	private AttachmentUploadResponse buildAttachmentUploadResponse(Map<String, Object> response) {
		log.trace("File Upload Feign Client Response {[]}", response);
		return modelMapper.map(response, AttachmentUploadResponse.class);
	}

	@Override
	public AttachmentUploadResponse uploadAttachment(String module, MultipartFile file) {
		log.trace("File Upload Feign Client Request {[]}", module, file);
//		 final String username = AppUtils.getUserNameFromAuthentication();
//	        final Optional<Employee> employee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
//	        if(employee.isPresent()){
	        	 return buildAttachmentUploadResponse(attachmentService.uploadFile(buildAttachmentUploadRequest(module, file)));
//	        }else
//           	 throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
	}
	
	@SneakyThrows
	private AttachmentUploadRequest buildAttachmentUploadRequest(final String module,final MultipartFile file) {
		AttachmentUploadRequest response = new AttachmentUploadRequest();
		response.setModule(module);
		final String ext = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
		response.setBase64image("data:image/"+ext+";base64,"+Base64.getEncoder().encodeToString(file.getBytes()));
		return response;
	}
}
