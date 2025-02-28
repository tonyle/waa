package edu.miu.lab1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Exception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDate date;

    private Long time;

    private String principle;

    private String operation;

    private String exceptionType;

    public Exception() {
        this.principle = "static_user";
        this.date = LocalDate.now();
        this.time = System.currentTimeMillis();
    }
}