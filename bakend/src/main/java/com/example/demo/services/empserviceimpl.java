package com.example.demo.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.departement;
import com.example.demo.entities.employee;
import com.example.demo.repos.emprepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;




import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;



@Service

public class empserviceimpl implements empservice{
	private String QRCODE_PATH="C:\\Users\\pc\\Desktop\\";
	@Autowired
     emprepository employeerepository;
	departement d;
	departement du;
	@Autowired
	deptservice dept;
	@Override
	
	@CrossOrigin
    @Transactional
    public ResponseEntity<Object> saveEmployee(employee newemp,long id) {
		d=dept.getDepartement( id);
        //emp.setDepartement(d);
        employeerepository
                .findById(newemp.getId())
                .ifPresentOrElse(
                	emp1 -> {
                            throw new ResponseStatusException(HttpStatus.FOUND, "emp ID alredy exist");
                        }, 
                	    () -> {
                            	
                            	newemp.setDepartement(d);
                            	newemp.setQrimg(newemp.getNom()+newemp.getCin()+"-QRCODE.png");
                            	
                            	
                            /*	RestTemplate restTemplate = new RestTemplate();

                            	HttpHeaders headers = new HttpHeaders();
                            	headers.setContentType(MediaType.APPLICATION_JSON);

                            	String requestBody = "a";

                            	HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

                            	String response = restTemplate.postForObject("https://www.qrcode-monkey.com/#text", request, String.class);
								System.out.print("r"+response);

                            	
								try {
									BitMatrix bitMatrix = new MultiFormatWriter().encode(response, BarcodeFormat.QR_CODE, 2000, 2000);
									File qrCodeFile = new File("C:\\Users\\pc\\Desktop\\qrcode.png");
									MatrixToImageWriter.writeToFile(bitMatrix, "png", qrCodeFile);
									System.out.print("ok");
								} catch (WriterException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                            	*/

                            	
                            	
                            	
                            	
                            	
                            	
                            	
                            	
                            	/*try {
									newemp.setQrimg(generateQRCodeImage(newemp.getNom(),100,100));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (WriterException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
                            	/*try {
									this.writeQRCode(newemp.getNom());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
                               
                            	employeerepository.save(newemp);
                        	
                        }
                );
//        return ResponseEntity.accepted().body(acte);
        return ResponseEntity.ok(newemp);
    }
	
	
	

	   /* public String generateQRCodeImage(String text, int width, int height) throws IOException, WriterException {
	        Map<EncodeHintType, Object> hints = new HashMap<>();
	        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                int grayValue = (bitMatrix.get(x, y) ? 0 : 1) & 0xff;
	                image.setRGB(x, y, (grayValue == 0 ? Color.BLACK : Color.WHITE).getRGB());
	            }
	        }

	        return image.toString();
	    }*/
	public String writeQRCode(String text)throws Exception{
		String qrcode = this.QRCODE_PATH + text +"_qrcode.png";
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix =writer.encode(text 
				, BarcodeFormat.QR_CODE, 350, 350);
		Path path=FileSystems.getDefault().getPath(qrcode);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return "ok";
	}
	   
	
	@Override
	public employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return employeerepository.findById(id).get();
	}

	@Override

	@Transactional
    public ResponseEntity<Object> updateEmployee(long id, employee newemp ,long iddept) {
		du=dept.getDepartement( iddept);
		newemp.setDepartement(du);
        employeerepository
                .findById(id)
                .ifPresentOrElse(emp -> {
                	emp.setNom(newemp.getNom());
                	emp.setPrenom(newemp.getPrenom());
                	emp.setCin(newemp.getCin());
                	emp.setPoste(newemp.getPoste());
                	emp.setDepartement(newemp.getDepartement());
                	emp.setGender(newemp.getGender());
                	emp.setAdresse(newemp.getAdresse());
                	emp.setCnss(newemp.getCnss());
                	emp.setDateembauche(newemp.getDateembauche());
                	emp.setDatenais(newemp.getDatenais());
                	emp.setEmail(newemp.getEmail());
                	emp.setImage(newemp.getImage());
                	emp.setTel(newemp.getTel());
                	emp.setVille(newemp.getVille());
                	emp.setSalary(newemp.getSalary());
                	emp.setQrimg(newemp.getNom()+newemp.getCin()+"-QRCODE.png");
                	
                
                   
                	employeerepository.save(emp);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "emp to update not found");
                });
//        return ResponseEntity.accepted().body("Successfully updated acte");
        return ResponseEntity.ok(newemp);
    }

	

	@Override
	 @Transactional
	public void deletEmployeebyid(Long id) {
		// TODO Auto-generated method stub
		
		employeerepository
        .findById(id)
        .ifPresentOrElse(emp -> {
        	employeerepository.delete(emp);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "emp to delete not found");
        });
		
	}



	@Override
	public Page<employee> getallEmployees(int page ,int size) {
		// TODO Auto-generated method stub
		//List<employee>employees=new ArrayList<>();
		Pageable pages=PageRequest.of(page, size);
		return employeerepository.findAll(pages);
		// return employees;
	}
	public List<employee> allEmployees() {
		// TODO Auto-generated method stub
		//List<employee>employees=new ArrayList<>();
		
		return employeerepository.findAll();
		// return employees;
	}
	public int femmeEmployee() {
		// TODO Auto-generated method stub
		//List<employee>employees=new ArrayList<>();
		
		return employeerepository.employeesfemme();
		// return employees;
	}
	public int hommeEmployee() {
		// TODO Auto-generated method stub
		//List<employee>employees=new ArrayList<>();
		
		return employeerepository.employeeshomme();
		// return employees;
	}
	@Override
	public Page<employee> findByKeyword( String Keyword, int page, int size) {
		Pageable pages=PageRequest.of(page, size);
		return employeerepository.findByKeyword(Keyword,pages);
	}
	@Override
	public employee findByEmail(String email) {
		// TODO Auto-generated method stub
		return employeerepository.findByEmail(email);
	}

}
