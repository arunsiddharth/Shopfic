package com.shopfic.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class tester {

	//Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C:\\Users\\aruna\\EclipseMarsWorkspace\\Eagle\\src\\main\\webapp\\resources\\maintheme\\themes\\images\\products\\";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void singleFileUpload(@RequestParam("files") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) {
    	for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            try {

                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);

                System.out.println("Uploaded");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Hey");
    }
}