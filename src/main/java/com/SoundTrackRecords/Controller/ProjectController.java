/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Controller;

import com.SoundTrackRecords.DTO.InvoiceDto;
import com.SoundTrackRecords.Model.ActivityType;
import com.SoundTrackRecords.Model.Combination;
import com.SoundTrackRecords.Model.Genre;
import com.SoundTrackRecords.Model.Invoice;
import com.SoundTrackRecords.Repository.ActivityTypeRepository;
import com.SoundTrackRecords.Repository.CombinationRepository;
import com.SoundTrackRecords.Repository.GenreRepository;
import com.SoundTrackRecords.Repository.ProjectTypeRepository;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import com.SoundTrackRecords.Model.Project;
import com.SoundTrackRecords.Model.ProjectType;
import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.InvoiceRepository;
import com.SoundTrackRecords.Repository.ProjectRepository;
import com.SoundTrackRecords.Repository.UserRepository;
import com.SoundTrackRecords.Service.CommonService;
import com.SoundTrackRecords.Service.SerialNumber;
import com.itextpdf.text.log.LoggerFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.itextpdf.text.log.Logger;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ish
 */
@Controller
public class ProjectController {

    @Autowired
    ProjectTypeRepository projectTypeRepository;
    @Autowired
    ActivityTypeRepository activityTypeRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    CombinationRepository combinationRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommonService commonservice;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserRepository usersRepository;
    @Autowired
    private final SerialNumber serialNumber;
    public ProjectController(SerialNumber serialNumber) {
        this.serialNumber = serialNumber;
    }
    Logger log = LoggerFactory.getLogger(ProjectController.class);
//TOTAL NUMBER OF PROJECTS, TOTAL NUMBER OF WRITINGS, AND TOTAL NUMBER OF VOCALS TO INDEX PAGE
    @RequestMapping("/")
    public String index(Model m, Principal principal) {
        m.addAttribute("cmd", new Project());
        m.addAttribute("ProjectCount", projectRepository.getProjectCount());
        m.addAttribute("ProjectVocalCount", projectRepository.getProjectVocalRecording());
        m.addAttribute("ProjectWritingCount", projectRepository.getProjectWriting());
        return "index"; //index.html page 
    }
//GET PROFILE PICTURE TO VIEW
    @ModelAttribute("profilepic")
    public String profile(Principal principal, String username) {
        String un = principal.getName();
        return usersRepository.getUserPhoto(un);
    }
//GET LOGGEDIN USER BY USERNAME
    @RequestMapping(value = "/myprofile")
    @ResponseBody
    public Users profiles(Principal principal) {
        String un = principal.getName();
        return usersRepository.findByUsername(un);
    }
//LIST OF ALL PROJECT TYPE 
    @RequestMapping(value = "/projecttypeList")
    @ResponseBody
    public List<ProjectType> getProjectTypeList() {
        return projectTypeRepository.findAll();
    }
//LIST OF ALL ACTIVITIES
    @RequestMapping("/activitytypeList")
    @ResponseBody
    public List<ActivityType> getActivityTypeList() {
        return activityTypeRepository.findAll();
    }
//LIST OF ALL GENRES
    @RequestMapping("/genreList")
    @ResponseBody
    public List<Genre> getgenreList() {
        return genreRepository.findAll();
    }
//LIST OF ALL COMBINATIONS
    @RequestMapping("/combinationList")
    @ResponseBody
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
    //@ResponseBody
    public String projectList(Model model) {
        model.addAttribute("projectList", projectRepository.findAll());
        return "table";
    }
//LIST OF ALL SONGS
    @GetMapping("/songlist")
    public String songlist(Model m) {
        m.addAttribute("songlist", projectRepository.getSongDetail());
        return "songlist"; //html
    }
//LIST OF ALL ARTISTES
    @GetMapping("/artistlist")
    public String artiste(Model m) {
        m.addAttribute("artistlist", projectRepository.getArtisteDetail());
        return "artistlist"; //html
    }
//DELETE A PROJECT
    @GetMapping(value = "/delete_project/{id}")
    public @ResponseBody
    String addressProect(@PathVariable Long id) {
        return commonservice.deleteproj(id);
    }
//LIST OF ALL INVOICE
    @GetMapping(value = "/invoicelist")
    public String invoiceList(Model model) {
        model.addAttribute("invoicelist", invoiceRepository.invoiceList());
        return "invoicelist";
    }
    //GET ONE PROJECT BY ID
    @GetMapping("/get_project")
    @ResponseBody
    public Project getOneProject(Long id) {
        return projectRepository.getOne(id);
    }
    //UPDATE PROJECT
    @RequestMapping(value = "/update_project/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    ResponseEntity<Project> updateCategory(Project project) {
        Project result = projectRepository.save(project);
        return ResponseEntity.ok().body(result);
    }
    //ADD NEW INVOICE
    @GetMapping("/save-invoice")
    public String saveInvoice(@RequestParam Long id, @RequestParam double masteringcost,
            @RequestParam double mixingcost, @RequestParam double studiotimecost, @RequestParam double costofintruments, @RequestParam int timeinhr) {
        {
            commonservice.saveInvoice(id, studiotimecost, mixingcost, timeinhr, costofintruments, masteringcost);
            return "redirect:projectlist";
        }
    }
//LIST ALL PROJECT AS API
    @RequestMapping(value = "/projectlistt", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getact() {
        return projectRepository.findAllByOrderByProjectstartdateAsc();
    }
//GET INVOICE BY ID
    @GetMapping("/edit_invoice")
    @ResponseBody
    public Invoice editInvoice(Long id) {
        return invoiceRepository.getOne(id);
    }
    @RequestMapping(value = "/update_invoice", method = {RequestMethod.PUT, RequestMethod.GET})
    @Transactional
    public String updateinvoice(Invoice invoice, @RequestParam Long id, @RequestParam double masteringcost,
            @RequestParam double mixingcost, @RequestParam double studiotimecost, @RequestParam double costofintruments, @RequestParam int timeinhr) {
        Double Totalcost = (studiotimecost * timeinhr) + (mixingcost + masteringcost + costofintruments);
        invoice.setMixingcost(mixingcost);
        invoice.setStudiotimecost(studiotimecost);
        invoice.setMasteringcost(masteringcost);
        invoice.setTimeinhr(timeinhr);
        invoice.setCostofintruments(costofintruments);
        invoice.setTotalstudicost(timeinhr * studiotimecost);
        invoice.setProject(invoice.getProject()); //FK
        invoice.setDatecreated(new Date());
        invoice.setInvoiceno(serialNumber.gebrateInvoice());
        invoice.setTotalcost(Totalcost);
        invoiceRepository.save(invoice);
        projectRepository.getupdated(id);
        return "redirect:invoicelist";
    }
//DELETE INVOICE
    @GetMapping(value = "/delete_invoice/{id}")
    public @ResponseBody
    String addressIvoice(@PathVariable Long id) {
        projectRepository.updatedelete(id);
        return commonservice.deleteinv(id);
    }

//GENERATE INVOICE AS PDF
    @GetMapping("/invoicePDF")
    public void soundcheckInvoicePDF(HttpServletResponse response, Long id) throws NullPointerException, SQLException, IOException, JRException {        
      List<InvoiceDto> invoice=  invoiceRepository.getInoiceForPdf(id);
      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoice);
      InputStream jrxmlInput = this.getClass().getResourceAsStream("/report/InvoicePDF.jrxml");
      JasperDesign design = JRXmlLoader.load(jrxmlInput);
      JasperReport jasperReport = JasperCompileManager.compileReport(design);
      JasperPrint jasperPrint  =  JasperFillManager.fillReport(jasperReport,null, dataSource);
   
      JRPdfExporter pdfExporter = new JRPdfExporter();
      pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
      pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
      pdfExporter.exportReport();
      
      response.setContentType("application/pdf");
      response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
      response.addHeader("Content-Disposition", "inline; filename=SCR-Invoice.pdf");

      OutputStream responseOutputSteam = response.getOutputStream();
      responseOutputSteam.write(pdfReportStream.toByteArray());
      responseOutputSteam.close();
      pdfReportStream.close();
      
    }
}

