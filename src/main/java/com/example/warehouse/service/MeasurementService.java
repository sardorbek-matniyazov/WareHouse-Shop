package com.example.warehouse.service;

import com.example.warehouse.entity.Measurement;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeasurementService {

    private final MeasurementRepository repo;

    public Result add(Measurement measurement) {
        if (repo.existsByName(measurement.getName())){
            return Result.builder()
                    .message("the measurement has already created !")
                    .status(false)
                    .build();
        }
        repo.save(measurement);
        return Result.builder()
                .message("the measurement has successfully created !")
                .status(true)
                .build();
    }

    public Result edit(Long id, Measurement measurement) {
        if (!repo.existsById(id))
            return Result.builder()
                .message("there is not any measurement")
                .status(false)
                .build();
        if (repo.existsByName(measurement.getName())){
            return Result.builder()
                    .message("the measurement has already created !")
                    .status(false)
                    .build();
        }
        measurement.setId(id);
        repo.save(measurement);
        return Result.builder()
                .message("the measurement has successfully edited !")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there is not any measurement")
                    .status(false)
                    .build();
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.builder()
                    .message("you can't delete , cause of there have many active products ")
                    .status(false)
                    .build();
        }
        return Result.builder()
                .message("the measurement successfully deleted")
                .status(true)
                .build();
    }
}
