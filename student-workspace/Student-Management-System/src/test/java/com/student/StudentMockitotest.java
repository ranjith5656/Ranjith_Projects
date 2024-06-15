package com.student;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domain.StudentDomain;
import com.entity.StudentEntity;
import com.repository.StudentEntityRepository;
import com.service.StudentServiceImp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class StudentMockitotest {
	
	@InjectMocks
	StudentServiceImp studentServiceImp;
	
	@Mock
	StudentDomain studentDomain;
	
	@Mock
	StudentEntity studentEntity;
	
	@MockBean
	StudentEntityRepository studentEntityDao;
	
  long id = 1;
  @Test
	public void createStudent() throws Exception{

	studentEntity.setFirstName("R");
	studentEntity.setLastName("Indluru");
	studentEntity.setStudentClass("Third");
	studentEntity.setStudentPhone(12345678);
	studentEntity.setStudnetAge(12);
	
	//when(studentEntityDao.saveAndFlush(studentEntity).thenReturn(studentEntity));
studentServiceImp.createStudent(studentDomain);
	
}
  
  @Test
	public void deleteStudent() {
		
		//boolean b = studentServiceImp.deleteStudent((long) 1006);

		studentServiceImp.deleteStudent(1);
      verify(studentEntityDao,times(1)).delete(null);;
	}
}
