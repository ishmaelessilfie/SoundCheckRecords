package com.SoundTrackRecords.Controller;

import com.SoundTrackRecords.Exception.FileStorageException;
import java.io.IOException;

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
import com.SoundTrackRecords.Util.AppResponse;
import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.ApplicationService;
import com.SoundTrackRecords.Service.CommonService;
import com.SoundTrackRecords.Service.FileStorageService;
import com.SoundTrackRecords.Util.AppConstants;
import java.security.Principal;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersRestController {

    @Autowired
    ApplicationService applicationService;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    CommonService commonservice;
    @Autowired
    UserRepository usersRepository;
    
   //ADD USER....................................
   @RequestMapping(value = AppConstants.USER_URI, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   @ResponseBody
    public AppResponse createUsers(
            @RequestParam(value = AppConstants.USER_JSON_PARAM, required = true) String empJson,
            @RequestParam(required = true, value = AppConstants.USER_FILE_PARAM) MultipartFile file)
            throws JsonParseException, JsonMappingException, IOException{
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
                .path(fileName).toUriString();
        Users users = objectMapper.readValue(empJson, Users.class);
        
        users.setRole("ADMIN");
        users.setPassword(encoder.encode(users.getPassword()));
        users.setPhoto(fileDownloadUri);
        users.setDatecreated(new Date());
        users.setFilename(fileName);
        applicationService.createUser(users ,file);
        return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
    }
   // END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

    //UPDATE USER........................
    @RequestMapping(value = AppConstants.USERUPDATE_URI, method = {RequestMethod.GET, RequestMethod.PUT}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public AppResponse updateUser(
            @RequestParam(value = AppConstants.USER_JSON_PARAM, required = true) String empJson,
            @RequestParam(required = true, value = AppConstants.USER_FILE_PARAM) MultipartFile file)
            throws JsonParseException, JsonMappingException, IOException {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
                .path(fileName).toUriString();
        Users users = objectMapper.readValue(empJson, Users.class);
        String inputpassword = usersRepository.getPassword(users.getUsername());
        String inputphotos = usersRepository.getPhotos(users.getUsername());
       
        if(users.getPassword().equals(inputpassword)){
            users.setPassword(users.getPassword());
        }else {
          users.setPassword(encoder.encode(users.getPassword()));
        }
        if(!file.isEmpty()){
            users.setPhoto(fileDownloadUri);
        }else{
            users.setPhoto(inputphotos);
        }
        users.setRole("ADMIN");
      users.setFilename(fileName);
        applicationService.createUser(users,file);
        return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
    }
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    
    //GET PROFILE PICTURE....................
    @ModelAttribute("profilepic")
    public String profile(Principal principal, String username) {
        String un = principal.getName();
        return usersRepository.getUserPhoto(un);
    }
    //END,,,,,,,,,,,,,,,,,,,,,,
    
    //USER LIST................
    @GetMapping(value = "/userlist")
    public String getAllUsers(Model model) {
        model.addAttribute("userlist", usersRepository.findAll());
        return "userlist";
    }
    //END.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    
    //EDIT USER...................
    @GetMapping("/edit_user")
    @ResponseBody
    public Users getUser(Long id) {
        return usersRepository.getOne(id);
    }
    //END,,,,,,,,,,,,,,,,,,,,,,

    //DELETE USER....................
     @GetMapping(value = "/delete_user/{id}")
	public @ResponseBody String addressIvoice(@PathVariable Long id) {
		return commonservice.deleteuser(id);
       }
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,    
        
    //DOWNLOAD FILE....................
    @RequestMapping(value = AppConstants.DOWNLOAD_URI, method = RequestMethod.GET)
    //@ResponseBody
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
