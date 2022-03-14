package com.example.warehouse.service;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.AttachmentContent;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.AttachmentContentRepository;
import com.example.warehouse.repository.AttachmentRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttachmentService {
    private final AttachmentRepository repo;
    private final AttachmentContentRepository contentRepo;

    @SneakyThrows
    public Result upload(MultipartHttpServletRequest request){
        MultipartFile file = request.getFile(request.getFileNames().next());
        assert file != null;

        // it will save file properties
        Attachment attachment = Attachment.builder().
                name(file.getOriginalFilename())
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();
        attachment = repo.save(attachment);

        // it will save file contents
        contentRepo.save(
                AttachmentContent.builder()
                        .attachment(attachment)
                        .bytes(file.getBytes())
                        .build()
        );

        return Result.builder()
                .message("the attachment successfully saved")
                .status(true)
                .object(attachment.getId())
                .build();
    }

    @SneakyThrows
    public HttpServletResponse download(Long id, HttpServletResponse response) {
        Optional<AttachmentContent> byAttachmentId = contentRepo.findByAttachmentId(id);
        if (byAttachmentId.isPresent()) {
            AttachmentContent attachmentContent = byAttachmentId.get();
            response.addHeader("Content-Disposition", "attachment; filename=\"" +
                    attachmentContent.getAttachment().getName() + "\"");
            response.setContentType(attachmentContent.getAttachment().getContentType());
            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        }
        return response;
    }
}
