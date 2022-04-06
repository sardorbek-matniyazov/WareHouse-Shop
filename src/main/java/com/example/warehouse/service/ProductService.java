package com.example.warehouse.service;

import com.example.warehouse.entity.AttachmentContent;
import com.example.warehouse.entity.Product;
import com.example.warehouse.payload.ProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;
    private final AttachmentRepository photoRepo;
    private final AttachmentContentRepository contentRepository;
    private final MeasurementRepository measurementRepo;

    public Result add(ProductDto dto) {
        if (!categoryRepo.existsById(dto.getCategoryId()))
            return Result.builder()
                    .message("there is not any category")
                    .status(false)
                    .build();
        if (!photoRepo.existsById(dto.getPhotoId()))
            return Result.builder()
                    .message("there is not any photo")
                    .status(false)
                    .build();
        if (!measurementRepo.existsById(dto.getMeasurementId()))
            return Result.builder()
                    .message("there is not any measurement")
                    .status(false)
                    .build();
        if (repo.existsByPhotoId(dto.getPhotoId()))
            return Result.builder()
                    .message("there photo has already used")
                    .status(false)
                    .build();
        if (
                repo.existsByNameAndCategoryIdAndPhotoIdAndMeasurementId(
                        dto.getName(),
                        dto.getCategoryId(),
                        dto.getPhotoId(),
                        dto.getMeasurementId()
                )
        ) return Result.builder()
                .message("the product is already created")
                .status(false)
                .build();

        Product product = Product.builder()
                .category(
                        categoryRepo.getById(
                                dto.getCategoryId()
                        ))
                .measurement(
                        measurementRepo.getById(
                                dto.getMeasurementId()
                        ))
                .photo(
                        photoRepo.getById(
                                dto.getPhotoId()
                        )
                )
                .build();
        product.setName(dto.getName());
        product.setCode(getCode());
        product.setActive(true);
        repo.save(product);

        return Result.builder()
                .message("the product has successfully created")
                .status(true)
                .build();
    }
    private String getCode() {
        Long code = repo.getLastCode();
        return String.format("00%d", code == null ? 1 : code + 1);
    }

    public Result edit(Long id, ProductDto dto) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there is not any product")
                    .status(false)
                    .build();
        if (!categoryRepo.existsById(dto.getCategoryId()))
            return Result.builder()
                    .message("there is not any category")
                    .status(false)
                    .build();
        if (!measurementRepo.existsById(dto.getMeasurementId()))
            return Result.builder()
                    .message("there is not any measurement")
                    .status(false)
                    .build();
        if (repo.existsByPhotoId(dto.getPhotoId()) && !repo.existsByPhotoIdAndId(dto.getPhotoId(), id))
            return Result.builder()
                    .message("there photo has already used")
                    .status(false)
                    .build();
        if (
                repo.existsByNameAndCategoryIdAndPhotoIdAndMeasurementId(
                        dto.getName(),
                        dto.getCategoryId(),
                        dto.getPhotoId(),
                        dto.getMeasurementId()
                )
        ) return Result.builder()
                .message("the product is already created")
                .status(false)
                .build();

        Product product = Product.builder()
                .category(
                        categoryRepo.getById(
                                dto.getCategoryId()
                        ))
                .measurement(
                        measurementRepo.getById(
                                dto.getMeasurementId()
                        ))
                .photo(
                        photoRepo.getById(
                                dto.getPhotoId()
                        )
                )
                .code(getCode())
                .build();
        product.setId(id);
        product.setName(dto.getName());
        product.setActive(true);
        repo.save(product);

        return Result.builder()
                .message("the product has successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there is not any product")
                    .status(false)
                    .build();

        try {
            repo.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            Product byId = repo.getById(id);
            byId.setActive(false);
            repo.save(byId);
            return Result.builder()
                    .message("the product hasn't deleted, but its status was changed to false instead of true")
                    .status(true)
                    .build();
        }

        return Result.builder()
                .message("the product has successfully deleted")
                .status(true)
                .build();
    }
    @SneakyThrows
    public void getPhoto(HttpServletResponse response, Long id){
        Optional<Product> byId = repo.findById(id);
        if (byId.isPresent()){
            Optional<AttachmentContent> byAttachmentId = contentRepository.findByAttachmentId(
                    byId.get().getPhoto().getId()
            );

            if (byAttachmentId.isPresent()) {
                AttachmentContent attachmentContent = byAttachmentId.get();
                response.addHeader("Content-Disposition", "attachment; filename=\"" +
                        attachmentContent.getAttachment().getName() + "\"");
                response.setContentType(attachmentContent.getAttachment().getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }

        }
    }
}
