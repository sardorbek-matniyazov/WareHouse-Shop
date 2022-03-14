package com.example.warehouse.controller;

import com.example.warehouse.payload.Result;
import com.example.warehouse.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("attachment")
@AllArgsConstructor
public class AttachmentController {
    private final AttachmentService service;

    @PostMapping(value = "/upload")
    public Result uploadFile(MultipartHttpServletRequest request) {
        return service.upload(request);
    }

    @GetMapping(value = "/download/{id}")
    public void download(HttpServletResponse response, @PathVariable Long id) {
        response = service.download(id, response);
    }
}
