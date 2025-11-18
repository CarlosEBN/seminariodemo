package co.edu.uniajc.controller;

import co.edu.uniajc.exception.StudentRequestException;
import co.edu.uniajc.model.StudentModel;
import co.edu.uniajc.service.StudentService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/save")
    public StudentModel saveStudent(@RequestBody StudentModel studentModel){
        return studentService.createStudent(studentModel);
    }

    @PutMapping(path = "/update")
     public StudentModel updateStudent(@RequestBody StudentModel studentModel){
        return studentService.updateStudent(studentModel);
    }

    @DeleteMapping(path="/delete")
    public void deleteStudent (@RequestParam(name = "id") Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping(path="/all")
    public List<StudentModel> findAllStudent(){
        return studentService.findAllStudent();
    }

    @GetMapping(path="/all/name")
    public List<StudentModel> findAllStudentByName(@RequestParam(name ="name") String name){
        return studentService.findAllStudentByName(name);
    }

    @GetMapping(path="/all/age")
    public List<StudentModel> findAllStudentByAge(@RequestParam(name ="age") Integer age){
        return studentService.findAllAges(age);
    }

    @GetMapping(path="/id")
  @ApiResponses( value = {
            @ApiResponse(responseCode = "400", description = "Something Went Wrong"),
            @ApiResponse(responseCode = "404", description = "No se encontro"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<StudentModel> findById(@RequestParam(name ="id") Long idStudent) throws  Exception{
        return ResponseEntity.ok(studentService.findById(idStudent).
                orElseThrow(() -> new StudentRequestException("No se encontro"))
        );
    }

}
