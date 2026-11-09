package com.example.demo;

import java.io.IOException;
import java.nio.file.FileSystems;

import com.example.demo.entities.employee;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.nio.file.Path;

public class Qrcodegenerator {
	public static void generateQrCode(employee employee) throws WriterException, IOException {
		String qrCodePath ="C:\\Users\\pc\\Desktop\\rh\\gestionRH\\src\\assets\\images\\qrcode\\";
		String qrCodeName=qrCodePath+employee.getNom()+employee.getCin()+"-QRCODE.png";
		var qrCodeWriter=new QRCodeWriter();
		BitMatrix bitMatrix=qrCodeWriter.encode("ID: "+employee.getId()+"\n"
				+"NOM: "+employee.getNom() +"\n"
				+"Prenom: "+employee.getPrenom()+"\n"
				+"email: "+employee.getEmail()+"\n"
				+"cin: "+employee.getCin()+"\n"
				+"date de naissance: "+employee.getDatenais()+"\n"
				+"poste: "+employee.getPoste()+"\n"
				, BarcodeFormat.QR_CODE, 600, 600);
		Path path=FileSystems.getDefault().getPath(qrCodeName);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		
	}
	

}
