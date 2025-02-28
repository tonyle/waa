package edu.miu.lab1.service.impl;

import edu.miu.lab1.entity.Exception;
import edu.miu.lab1.repo.ExceptionRepo;
import edu.miu.lab1.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {

    private final ExceptionRepo exceptionRepo;

    @Override
    public void logException(String operation, String exceptionType) {
        Exception exception = new Exception();
        exception.setOperation(operation);
        exception.setExceptionType(exceptionType);
        exceptionRepo.save(exception);
    }
}
