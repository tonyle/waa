package edu.miu.lab1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDate date;

    private Long time;

    private String principle;

    private String operation;

    public Logger() {
        this.principle = "static_user";
        this.date = LocalDate.now();
    }
}