package com.SoundTrackRecords.Controller;

import com.SoundTrackRecords.Exception.FileStorageException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.SoundTrackRecords.Model.AppResponse;
import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.ApplicationService;
import com.SoundTrackRecords.Service.FileStorageService;
import com.SoundTrackRecords.utils.AppConstants;
import java.util.Date;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersRestController {

    @Autowired
    ApplicationService applicationService;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    UserRepository usersRepository;
    //ADD USER....................................

    @RequestMapping(value = AppConstants.USER_URI, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AppResponse createUsers (
            @RequestParam(value = AppConstants.USER_JSON_PARAM, required = true) String empJson,
            @RequestParam(required = true, value = AppConstants.USER_FILE_PARAM) MultipartFile file)
            throws JsonParseException, JsonMappingException, IOException {
       
        String fileName = fileStorageService.storeFile(file);
       
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
                .path(fileName).toUriString();
        Users users = objectMapper.readValue(empJson, Users.class);
        users.setRole("ADMIN");
        users.setPassword(encoder.encode(users.getPassword()));
        users.setPhoto(fileDownloadUri);
        users.setDatecreated(new Date());
//        users.setFilename(fileName);
        applicationService.createUser(users);
        users.setPhoto(fileDownloadUri);
        applicationService.createUser(users);
        
        return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
    }
    // END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

    //UPDATE USER........................
    @RequestMapping(value = AppConstants.USERUPDATE_URI, method = {RequestMethod.GET, RequestMethod.PUT}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AppResponse updateUser(
          @RequestParam(value = AppConstants.USER_JSON_PARAM, required = true) String empJson,
            @RequestParam(required = true, value = "file") MultipartFile file)
            throws JsonParseException, JsonMappingException, IOException {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
                .path(fileName).toUriString();
       Users users = objectMapper.readValue(empJson, Users.class);

        String inputpassword = usersRepository.getPassword(users.getUsername());
        String inputphotos = usersRepository.getPhotos(users.getUsername());

        if (users.getPassword().equals(inputpassword)) {
            users.setPassword(users.getPassword());
        } else {
            users.setPassword(encoder.encode(users.getPassword()));
        }
        if (!file.isEmpty()) {
            users.setPhoto(fileDownloadUri);
        } else {
            users.setPhoto(inputphotos);
        }
        users.setRole("ADMIN");
//      users.setFilename(fileName);
        applicationService.createUser(users);
        return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
    }
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

    //USER LIST................
    @GetMapping("/userlist")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    //END.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    

    //DELETE USER....................
    @GetMapping(value = "/delete_user/{id}")
    public void addressIvoice(@PathVariable Long id) {
         usersRepository.deleteById(id);
    }
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,    

    //DOWNLOAD FILE....................
    @RequestMapping(value = AppConstants.DOWNLOAD_URI, method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (contentType == null) {
            contentType = AppConstants.DEFAULT_CONTENT_TYPE;
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
                .body(resource);
    }
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
}
