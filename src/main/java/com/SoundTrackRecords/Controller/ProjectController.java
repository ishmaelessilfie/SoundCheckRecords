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
import com.SoundTrackRecords.Model.Booking;
import com.SoundTrackRecords.Model.CheeseType;
import com.SoundTrackRecords.Model.Combination;
import com.SoundTrackRecords.Model.Genre;
import com.SoundTrackRecords.Model.Invoice;
import com.SoundTrackRecords.Model.Project;
import com.SoundTrackRecords.Repository.ActivityTypeRepository;
import com.SoundTrackRecords.Repository.CombinationRepository;
import com.SoundTrackRecords.Repository.GenreRepository;
import com.SoundTrackRecords.Repository.ProjectTypeRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.SoundTrackRecords.Model.ProjectType;
import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.BookingRepository;
import com.SoundTrackRecords.Repository.InvoiceRepository;
import com.SoundTrackRecords.Repository.ProjectRepository;
import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.SerialNumber;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.log.LoggerFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import com.itextpdf.text.log.Logger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.springframework.http.ResponseEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ish
 */
@RestController
public class ProjectController {

    private final ProjectTypeRepository projectTypeRepository;
    private final ActivityTypeRepository activityTypeRepository;
    private final GenreRepository genreRepository;
    private final CombinationRepository combinationRepository;
    private final ProjectRepository projectRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserRepository usersRepository;
    private final ObjectMapper mapper;
    private final SerialNumber serialNumber;
    private final BookingRepository bookingRepository;
    
//   @Autowired

    public ProjectController(BookingRepository bookingRepository,ProjectTypeRepository projectTypeRepository, SerialNumber serialNumber, ActivityTypeRepository activityTypeRepository, GenreRepository genreRepository, CombinationRepository combinationRepository, ProjectRepository projectRepository, InvoiceRepository invoiceRepository, UserRepository usersRepository, ObjectMapper mapper) {
        this.projectTypeRepository = projectTypeRepository;
        this.activityTypeRepository = activityTypeRepository;
        this.genreRepository = genreRepository;
        this.combinationRepository = combinationRepository;
        this.projectRepository = projectRepository;
        this.invoiceRepository = invoiceRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.serialNumber = serialNumber;
         this.bookingRepository = bookingRepository;
    }
    Logger log = LoggerFactory.getLogger(ProjectController.class);
//TOTAL NUMBER OF PROJECTS, TOTAL NUMBER OF WRITINGS, AND TOTAL NUMBER OF VOCALS TO INDEX PAGE

    @RequestMapping("/projectStatics")
//    @Cacheable(cacheNames = {"projectCache"})
    public ObjectNode stats() {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("ProjectCount", projectRepository.getProjectCount());
        objectNode.put("ProjectVocalCount", projectRepository.getProjectVocalRecording());
        objectNode.put("ProjectWritingCount", projectRepository.getProjectWriting());
// List<ProjectDto> projectList = projectService.getProjectList();
// Long prjectCount = projectList.stream().mapToLong(proj->proj.getId()).count();
        return objectNode;
    }

    @RequestMapping("/userProfile")
//    @Cacheable(cacheNames = {"projectCache"})
    public Users userProfile(Principal principal) {

        String un = principal.getName();
        return usersRepository.findByUsername(un);
    }
//LIST OF ALL PROJECT TYPE   

    @RequestMapping(value = "/projecttypeList")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<ProjectType> getProjectTypeList() {
        return projectTypeRepository.findAll();
    }
//LIST OF ALL ACTIVITIES

    @RequestMapping("/activitytypeList")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<ActivityType> getActivityTypeList() {
        return activityTypeRepository.findAll();
    }
//LIST OF ALL GENRES

    @RequestMapping("/genreList")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<Genre> getgenreList() {
        return genreRepository.findAll();
    }
//LIST OF ALL COMBINATIONS

    @RequestMapping("/combinationList")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<Combination> combinationList() {
        return combinationRepository.findAll();
    }
//ADD NEW PROJECT

    @RequestMapping(value = "/project", method = {RequestMethod.POST})
    ResponseEntity<Project> createCategory(@Valid Project project) throws URISyntaxException {
        project.setNumber(serialNumber.generateRegistrationNumber());
        Project result = projectRepository.save(project);
        return ResponseEntity.created(new URI("/project" + result.getId())).body(result);
    }
//LIST OF ALL PROJECTS

    @GetMapping(value = "/projectlist")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<Project> projectList() {
        return projectRepository.findAll();

    }
   @GetMapping(value = "/booking")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<Booking> projectBooking() {
        return bookingRepository.findAll();

    }
//LIST OF ALL SONGS
    @GetMapping("/songlist")
    //  @Cacheable(cacheNames = { "projectCache" })
    public List<SongDetailDto> songlist() {
        return projectRepository.getSongDetail();
    }
//LIST OF ALL ARTISTES

    @GetMapping("/artistlist")
//    @Cacheable(cacheNames = { "projectCache" })
    public List<ArtisteListDto> artiste() {
        return projectRepository.getArtisteDetail();
    }
    
    @GetMapping("/getCheese")
    public CheeseType[] cheese() {
        return CheeseType.values();
    }
//DELETE A PROJECT
    @GetMapping(value = "/delete_project/{id}")
    public void addressProect(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    //UPDATE PROJECT
    @RequestMapping(value = "/update_project/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    ResponseEntity<Project> updateCategory(Project project) {
        Project result = projectRepository.save(project);
        return ResponseEntity.ok().body(result);
    }
    //ADD NEW INVOICE
    @RequestMapping(value = "/save-invoice", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public AppResponse saveInvoice(Invoice invoice, @RequestParam Long id) {
        boolean  pdf =projectRepository.getIspdfexcelcreated(id);
       if( pdf ==true) {          
           return new AppResponse("Invoice Already generated", "201"); 
       }else{
                
        Double studiotimecost = invoice.getStudiotimecost();
        Integer timeinhr = invoice.getTimeinhr();
        Double mixingcost = invoice.getMixingcost();
        Double masteringcost = invoice.getMasteringcost();
        Double costofintruments = invoice.getCostofintruments();
        invoice.setTotalstudicost(timeinhr * studiotimecost);
        invoice.setProject(projectRepository.getOne(id)); //FK
        invoice.setDatecreated(new Date());
        invoice.setInvoiceno(serialNumber.gebrateInvoice());
        invoice.setTotalcost((studiotimecost * timeinhr) + (mixingcost + masteringcost + costofintruments));
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
    public Invoice editInvoice(@PathVariable Long id) {
        return invoiceRepository.getOne(id);
    }

    @RequestMapping(value = "/update_invoice/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    @Transactional
    public void updateinvoice(Invoice invoice,@PathVariable Long id) {
        Double studiotimecost = invoice.getStudiotimecost();
        Integer timeinhr = invoice.getTimeinhr();
        Double mixingcost = invoice.getMixingcost();
        Double masteringcost = invoice.getMasteringcost();
        Double costofintruments = invoice.getCostofintruments();
        Double Totalcost = (studiotimecost * timeinhr) + (mixingcost + masteringcost + costofintruments);
        invoice.setTotalstudicost(timeinhr * studiotimecost);
        invoice.setProject(invoice.getProject()); //FK
        invoice.setDatecreated(new Date());
        invoice.setInvoiceno(serialNumber.gebrateInvoice());
        invoice.setTotalcost(Totalcost);
        invoiceRepository.save(invoice);
//        projectRepository.getupdated(id);
    }

//DELETE INVOICE
    @GetMapping(value = "/delete_invoice/{id}")
//    @CacheEvict(value = "projectCache", allEntries = true)
    public AppResponse addressIvoice(@PathVariable Long id) {
        projectRepository.updatedelete(id);
        invoiceRepository.deleteById(id);
        return new AppResponse("Invoice deleted Successfully", "200");
    }

//GENERATE INVOICE AS PDF
    @GetMapping("/invoicePDF/{id}")

    public void soundcheckInvoicePDF(HttpServletResponse response, @PathVariable Long id) throws NullPointerException, SQLException, IOException, JRException {
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
        response.addHeader("Content-Disposition", "inline; filename=SCR-Invoice__" + System.currentTimeMillis() + ".pdf");
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
