package com.example.demo.controller;


import java.nio.file.Path;
import java.util.List;
import static java.nio.file.Paths.get;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.contrat;
import com.example.demo.entities.dCondidature;

import com.example.demo.services.dCondidatureService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/dCondidature")
public class dCondidatureController {
	 public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";
	@Autowired
	private dCondidatureService dCondidatureService;
	
	@GetMapping("/all")
    public List<dCondidature> AlldCondidature() {
        List<dCondidature> conts = dCondidatureService.getallDCondidature();
        return conts;
    }
    
    
	@GetMapping("/all/{page}/{size}")
    public Page<dCondidature> AlldCondidatures(@PathVariable int page , @PathVariable int size) {
    	Page<dCondidature> cnts = dCondidatureService.getallDCondidatures(page, size);
        return cnts;
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addtypedCondidature(@RequestBody dCondidature c) {
    return dCondidatureService.saveDCondidature(c);
}
    @DeleteMapping("/delete/{id}")
    public void deletDCondidaturebyid(@PathVariable long id) {
    	dCondidatureService.deletDCondidaturebyid(id);
  }

  @GetMapping("/get/{id}")
  public dCondidature findcontrat(@PathVariable long id) {
      return dCondidatureService.getDCondidature(id);
  }
  
  @GetMapping("download/{filename}")
  public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
      Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
      if(!Files.exists(filePath)) {
          throw new FileNotFoundException(filename + " was not found on the server "+filePath);
      }
      Resource resource = new UrlResource(filePath.toUri());
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("File-Name", filename);
    
	httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment:File-Name=" + resource.getFilename());
      return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
              .headers(httpHeaders).body(resource);
  }

}
