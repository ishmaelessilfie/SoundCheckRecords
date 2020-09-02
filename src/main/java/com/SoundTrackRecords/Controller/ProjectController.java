/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Controller;

import com.SoundTrackRecords.DTO.ArtisteListDto;
import com.SoundTrackRecords.DTO.InvoiceDto;
import com.SoundTrackRecords.DTO.InvoiceList;
import com.SoundTrackRecords.DTO.SongDetailDto;
import com.SoundTrackRecords.Model.ActivityType;
import com.SoundTrackRecords.Model.AppResponse;
import com.SoundTrackRecords.Model.Invoice;
import com.SoundTrackRecords.Model.Project;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.SoundTrackRecords.Model.ProjectType;
import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.BookingRepository;
import com.SoundTrackRecords.Repository.InvoiceRepository;
import com.SoundTrackRecords.Repository.ProjectRepository;
import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.SerialNumber;
import com.SoundTrackRecords.Service.ValidataionErrorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.log.LoggerFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import com.itextpdf.text.log.Logger;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ish
 */
@RestController
public class ProjectController {
    
    private final ProjectRepository projectRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserRepository usersRepository;
    private final ObjectMapper mapper;
    private final SerialNumber serialNumber;
    private final BookingRepository bookingRepository;  
    private final ValidataionErrorService validataionErrorService;
   @Autowired
    public ProjectController(ProjectRepository projectRepository, InvoiceRepository invoiceRepository, UserRepository usersRepository, ObjectMapper mapper, SerialNumber serialNumber, BookingRepository bookingRepository, ValidataionErrorService validataionErrorService) {
        this.projectRepository = projectRepository;
        this.invoiceRepository = invoiceRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.serialNumber = serialNumber;
        this.bookingRepository = bookingRepository;
        this.validataionErrorService = validataionErrorService;
    }
    Logger log = LoggerFactory.getLogger(ProjectController.class);
//TOTAL NUMBER OF PROJECTS, TOTAL NUMBER OF WRITINGS, AND TOTAL NUMBER OF VOCALS TO INDEX PAGE

    @RequestMapping("/projectStatics")
//    @Cacheable(cacheNames = {"projectCache"})
    public ObjectNode stats() {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("ProjectCount", projectRepository.getProjectCount());
        objectNode.put("ProjectVocalCount", projectRepository.getProjectVocalRecording());
        objectNode.put("ProjectEngineeringCount", projectRepository.getProjectEngineering());
        objectNode.put("ProjectMixingCount", projectRepository.getProjectMixing());
        objectNode.put("ProjectProductionCount", projectRepository.getProjectProduction());
        objectNode.put("ProjectWritingCount", projectRepository.getProjectWriting());
        objectNode.put("booking", bookingRepository.getBooking());
// List<ProjectDto> projectList = projectService.getProjectList();
// Long prjectCount = projectList.stream().mapToLong(proj->proj.getId()).count();
        return objectNode;
    }
    
    @RequestMapping("/bookingdata")
    public ObjectNode bookingdata() {
        ObjectNode objectNode = mapper.createObjectNode();       
        objectNode.put("booking", bookingRepository.getBooking());
        return objectNode;
    }

    @RequestMapping("/userProfile")
//    @Cacheable(cacheNames = {"projectCache"})
    public Users userProfile(Principal principal) {
        String un = principal.getName();
        return usersRepository.findByUsername(un);
    }

    @GetMapping("/projecttypeList")
    public ResponseEntity<ProjectType[]> projectType() {
        return new ResponseEntity(ProjectType.values(), HttpStatus.OK);
    }
    @CrossOrigin(origins= {"http://soundcheckgh.com", "http://localhost"})
    @GetMapping("/activitytypeList")
    public ResponseEntity<ActivityType[]> activityType() {
        return new ResponseEntity(ActivityType.values(), HttpStatus.OK);
    }
     @GetMapping("/combination")
     public ResponseEntity<Set<String>> listCombination(){
         return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Single Artiste", "Combinattion")), HttpStatus.OK);
     }
     
      @GetMapping("/genreList")
     public ResponseEntity<Set<String>> getgenreList(){
         return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Afro-pop","Hi-life","R&B","Dancehall","Hiphop",
 "Jazz","Instrumentals","Contemporary Gospel","Gospel","Praise and Worship","Christian Rap","Christian Pop","Christian Rock",
 "Christian Rock","Country","Christian Country","Clasic Country","Contemporary Country","Country Pop","Country Rap","Country rock",
 "Country Soul","Contemporary Classical","Choral",
 "Modern Classical","Opera","Orchestral","Romantic","Symphonic","Symphony"
  )), HttpStatus.OK);
     }
     
     
    
//    @RequestMapping(value="/project") //ADD NEW PROJECT...............
//    public ResponseEntity createCategory(@Valid   Project project, BindingResult result){
//        ResponseEntity errors = validataionErrorService.validate(result);
//       if(errors!=null) return errors;
//        project.setNumber(serialNumber.generateRegistrationNumber());
//  //      Map<String,String> msg = new HashMap<String,String>();
////        msg.put("message","Project added successfuly");
//        projectRepository.save(project);
//        return new ResponseEntity("Project added successfuly", HttpStatus.OK);
//    
//   }
     @RequestMapping(value = "/project", method = {RequestMethod.POST}) //ADD NEW PROJECT...............
   public ResponseEntity createCategory(@Valid @RequestBody Project project, BindingResult result) throws URISyntaxException {
        ResponseEntity errors = validataionErrorService.validate(result);
       if(errors!=null) return errors;
        project.setNumber(serialNumber.generateRegistrationNumber());
        projectRepository.save(project);
        return new ResponseEntity("Project added successfuly", HttpStatus.OK);
    }
//LIST OF ALL PROJECTS
    @CrossOrigin(origins= "*")
    @GetMapping(value = "/projectlist")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<Project> projectList() {
        return projectRepository.findAllByOrderByProjectstartdate();
    }
    
//LIST OF ALL SONGS
    @GetMapping("/songlist")
    //  @Cacheable(cacheNames = { "projectCache" })
    public List<SongDetailDto> songlist() {
        return projectRepository.getSongDetail();
    }
//LIST OF ALL ARTISTES

    @GetMapping("/artistlist")
//   @Cacheable(cacheNames = { "projectCache" })
    public List<ArtisteListDto> artiste() {
        return projectRepository.getArtisteDetail();
    }

//DELETE A PROJECT
    @GetMapping(value = "/delete_project/{id}")
    public void addressProject(@PathVariable UUID id) {
        projectRepository.deleteById(id);
    }
    @GetMapping(value = "/getProject/{id}")
    public Project getProject(@PathVariable UUID id) {
       return projectRepository.getOne(id);
    }

    //UPDATE PROJECT
    @RequestMapping(value = "/update_project/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    ResponseEntity<Project> updateCategory(@Valid @RequestBody Project project, BindingResult result) throws URISyntaxException {
        ResponseEntity errors = validataionErrorService.validate(result);
       if(errors!=null) return errors;
        project.setNumber(serialNumber.generateRegistrationNumber());
        projectRepository.save(project);
        return new ResponseEntity("Project updated successfuly", HttpStatus.OK);
    }
    
    //ADD NEW INVOICE
    @RequestMapping(value = "/save-invoice", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public AppResponse saveInvoice(Invoice invoice, @RequestParam UUID id) {
        boolean  pdf =projectRepository.getIspdfexcelcreated(id);
       if( pdf ==true) {          
           return new AppResponse("Invoice Already generated", "201"); 
       }else{                
        Double studioTimeCost = invoice.getStudiotimecost();
        Integer timeInHr = invoice.getTimeinhr();
        Double mixingCost = invoice.getMixingcost();
        Double masteringCost = invoice.getMasteringcost();
        Double costOfIntruments = invoice.getCostofintruments();
        Double totalStudioCost = timeInHr * studioTimeCost;
        Double partCalc = mixingCost + masteringCost + costOfIntruments;
        Double totalCost = totalStudioCost + partCalc;
        
        invoice.setTotalstudicost(totalStudioCost);
        invoice.setProject(projectRepository.getOne(id)); //FK
//        invoice.setDatecreated(new Date());
        invoice.setInvoiceno(serialNumber.gebrateInvoice());
        invoice.setTotalcost(totalCost);
        invoiceRepository.save(invoice);
        projectRepository.getupdated(id);
        return new AppResponse("Invoice Created Successfully", "200");
        }
    }

  @GetMapping(value = "/invoicelist")
    public List<InvoiceList> invoiceList() {
     return    invoiceRepository.invoiceList();
        
    }
       
    //GET INVOICE BY ID
    @GetMapping("/edit_invoice/{id}")
//    @Cacheable(value = "projectCache", key = "#id", unless = "#result==null")
    public Invoice editInvoice(@PathVariable UUID id) {
        return invoiceRepository.getOne(id);
    }

    @RequestMapping(value = "/update_invoice/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    @Transactional
    public void updateinvoice(Invoice invoice,@PathVariable UUID id) {
        Double studiotimecost = invoice.getStudiotimecost();
        Integer timeinhr = invoice.getTimeinhr();
        Double mixingcost = invoice.getMixingcost();
        Double masteringcost = invoice.getMasteringcost();
        Double costofintruments = invoice.getCostofintruments();
        Double Totalcost = (studiotimecost * timeinhr) + (mixingcost + masteringcost + costofintruments);
        invoice.setTotalstudicost(timeinhr * studiotimecost);
        invoice.setProject(invoice.getProject()); //FK
        invoice.setTotalcost(Totalcost);
        invoiceRepository.save(invoice);
//        projectRepository.getupdated(id);
    }

//DELETE INVOICE
    @GetMapping(value = "/delete_invoice/{id}")
//    @CacheEvict(value = "projectCache", allEntries = true)
    public AppResponse addressIvoice(@PathVariable UUID id) {
        projectRepository.updatedelete(id);
        invoiceRepository.deleteById(id);
        return new AppResponse("Invoice deleted Successfully", "200");
    }

//GENERATE INVOICE AS PDF
    @GetMapping("/invoicePDF/{id}")
    public void soundcheckInvoicePDF(HttpServletResponse response, @PathVariable UUID id) throws NullPointerException, SQLException, IOException, JRException {
        List<InvoiceDto> invoice = invoiceRepository.getInoiceForPdf(id);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoice);
        InputStream jrxmlInput = this.getClass().getResourceAsStream("/report/InvoicePDF.jrxml");
        JasperDesign design = JRXmlLoader.load(jrxmlInput);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();
        response.setContentType("application/pdf");
        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
        response.addHeader("Content-Disposition", "inline; filename = SCR-Invoice__" + id + ".pdf");
        OutputStream responseOutputSteam = response.getOutputStream();
        responseOutputSteam.write(pdfReportStream.toByteArray());
        responseOutputSteam.close();
        pdfReportStream.close();
    }
    
    
       
//    @PostMapping("client")
//    public ResponseEntity<byte[]> export(List<ClientJsonDto> datas, HttpServletResponse response) {
//
//        System.out.println(datas);
//        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(datas);
//
//        /* Map to hold Jasper report Parameters */
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("ItemDataSource", itemsJRBean);
//
//        byte[] bytes = generatePDFReport("refclient", parameters);
//        return ResponseEntity
//                .ok()
//                // Specify content type as PDF
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                // Tell browser to display PDF if it can
//                .header("Content-Disposition", "attachment;inline; filename=\"client.pdf\"")
//                .body(bytes);
//
//    }
//
//    public byte[] generatePDFReport(String inputFileName, Map<String, Object> params) {
//        return generatePDFReport(inputFileName, params, new JREmptyDataSource());
//    }
//
//    public File loadJasperFile(String file) {
//
//        try {
//            return ResourceUtils.getFile("classpath:static/reports/" + file + ".jasper");
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        };
//        return null;
//    }
//
//    public byte[] generatePDFReport(String inputFileName, Map<String, Object> params,
//            JRDataSource dataSource) {
//        byte[] bytes = null;
//        JasperReport jasperReport = null;
//        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
//            // Check if a compiled report exists
//
//            jasperReport = (JasperReport) JRLoader.loadObject(loadJasperFile(inputFileName));
//
//            // Compile report from source and save
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//            // return the PDF in bytes
//            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
//        } catch (JRException | IOException e) {
//        }
//        return bytes;
//    }


}
