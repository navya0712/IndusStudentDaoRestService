package com.indus.training.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indus.training.persist.dao.StudentDao;
import com.indus.training.persist.entity.Student;
import com.indus.training.persist.exceptions.StudentHibernateJPAException;
import com.indus.training.persist.impl.StudentDaoImpl;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentDaoController {

	private StudentDao studentDao = new StudentDaoImpl();

	@PostMapping("/insert")
	public ResponseEntity<String> insertStudent(@RequestBody Student student) {
		try {
			boolean status = studentDao.insertStudent(student);
			if (status) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Student inserted successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to insert student.");
			}
		} catch (StudentHibernateJPAException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An exception occurred: " + ex.getMessage());
		}
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Object> fetchStudent(@PathVariable("id") Integer studentId) {
		try {
			Student student = studentDao.fetchStudent(studentId);
			if (student != null) {
				return ResponseEntity.ok(student);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
			}
		} catch (StudentHibernateJPAException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An exception occurred: " + ex.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer studentId) {
		try {
			boolean status = studentDao.deleteStudent(studentId);
			if (status) {
				return ResponseEntity.ok("Student deleted successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete student.");
			}
		} catch (StudentHibernateJPAException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An exception occurred: " + ex.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("id") Integer studentId,
            @RequestParam(name="firstName",required = false) String firstName,
            @RequestParam(name="lastName",required = false) String lastName) {
        try {
            boolean status = (firstName == null || studentDao.updateStudentFirstName(studentId, firstName)) &&
                             (lastName == null || studentDao.updateStudentLastName(studentId, lastName));

            if (status) {
                return ResponseEntity.ok("Student updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update student.");
            }
        } catch (StudentHibernateJPAException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An exception occurred: " + ex.getMessage());
        }
    }

}
