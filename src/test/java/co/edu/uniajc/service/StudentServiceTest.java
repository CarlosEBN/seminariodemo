package co.edu.uniajc.service;

import co.edu.uniajc.model.StudentModel;
import co.edu.uniajc.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    // Se define un repositorio simulado para que no ejecute la instrucción en la BD
    @Mock
    private StudentRepository studentRepositoryMock;

    // defino el servicio necesario para las pruebas que haré
    private StudentService studentService;

    /* Antes de cada inicio se crea el objeto simulando el llamado desde el controller
    * y entregandole como atributo de entrada el respoitory simulado*/
    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepositoryMock);
    }

    // creamos la prueba
    @Test
    void When_CreateStudent_Expect_CreateStudent_In_BD() {
        // se crea el objeto de prueba que debe ser enviado
        var student = StudentModel.builder()
                .id(1L)
                .name("Student")
                .lastName("Last Name")
                .age(18)
                .state(true).build();

        /*Se configura el comportamiento del mock para que cuando se llame al
         metodo save con el objeto estudiante, devuelva el mismo objeto,
         simulando éxito en la operación de almacenamiento*/
        given(studentRepositoryMock.save(student)).willReturn(student);

        // Llamar al metodo bajo prueba y capturar el resultado
        StudentModel createdStudent = studentService.createStudent(student);

        // Verificar que el metodo save fue llamado exactamente una vez con el estudiante dado
        verify(studentRepositoryMock).save(student);

        // Verificar que el resultado retornado es el mismo que el esperado
        assertEquals(student, createdStudent, "El estudiante creado debe ser el mismo que el devuelto por el repositorio");
    }

    @Test
    void When_CreateStudent_ThrowsException_Expect_ExceptionHandled() {
        var student = StudentModel.builder()
                .id(1L)
                .name("Student")
                .lastName("Last Name")
                .age(18)
                .state(true).build();

        // Simular que el repositorio lanza RuntimeException al llamar save
        given(studentRepositoryMock.save(student)).willThrow(new RuntimeException("Error guardando estudiante"));

        // Verificar que createStudent lanza RuntimeException cuando ocurre el error
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.createStudent(student);
        });

        assertEquals("Error guardando estudiante", exception.getMessage());

        // Verificar que se intentó llamar al metodo save
        verify(studentRepositoryMock).save(student);
    }

    @Test
    void When_UpdateStudent_Expect_UpdateStudent_In_BD() {

        var student = StudentModel.builder()
                .id(1L)
                .name("Student Updated")
                .lastName("Last Name Update")
                .age(19)
                .state(true).build();

        given(studentRepositoryMock.save(student)).willReturn(student);

        StudentModel updatedStudent  = studentService.updateStudent(student);

        verify(studentRepositoryMock).save(student);

        // Verificar que el resultado retornado es el mismo que el esperado
        assertEquals(student, updatedStudent, "El estudiante actualizado debe ser el mismo que el devuelto por el repositorio");
    }

    @Test
    void When_GetAllStudent_Expect_AllStudent_From_BD() {

        // Definir directamente la lista con los objetos StudentModel
        List<StudentModel> students = List.of(
                StudentModel.builder()
                        .id(1L)
                        .name("Student One")
                        .lastName("Last One")
                        .age(20)
                        .state(true).build(),
                StudentModel.builder()
                        .id(2L)
                        .name("Student Two")
                        .lastName("Last Two")
                        .age(21)
                        .state(true).build()
        );

        given(studentRepositoryMock.findAll()).willReturn(students);

        List<StudentModel> result  = studentService.findAllStudent();

        verify(studentRepositoryMock).findAll();

        // Verificar que el resultado retornado es el mismo que el esperado
        assertEquals(students, result, "La lista devuelta debe coincidir con la simulada");
    }
}