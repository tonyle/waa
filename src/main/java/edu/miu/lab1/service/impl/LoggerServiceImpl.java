package edu.miu.lab1.service.impl;

import edu.miu.lab1.entity.Logger;
import edu.miu.lab1.repo.LoggerRepo;
import edu.miu.lab1.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private final LoggerRepo loggerRepo;

    @Override
    public void logOperation(String operation, Long executionTime) {
        Logger logger = new Logger();
        logger.setOperation(operation);
        logger.setTime(executionTime);
        loggerRepo.save(logger);
    }
}
