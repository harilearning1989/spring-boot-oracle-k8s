package com.web.demo.services;

import com.web.demo.dtos.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> readStudentDetails();
}
