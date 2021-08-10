package com.springrest.springrest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

@RestController
public class MyController {
	
	@Autowired // This will create the object of child class of interface i.e. COurseServiceImpl
	private CourseService courseService;
	
	
	@GetMapping("/home")
	public String home(){
		
		return "This is home page"; 
	}
	
	//1.get the courses
	@GetMapping("/courses")
	public List<Course> getCourses(){
		
	// to get course it contact to service 
		 
		return this.courseService.getCourses();
	} 
	//2. get the single course
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) {
	
		return this.courseService.getCourse(Long.parseLong(courseId));
	}
	// 3. add the course via post method
	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course) {
		
		return this.courseService.addCourse(course);
	}
	//4.update course using Put
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course) {
		
		return this.courseService.updateCourse(course);
	}
	//5.delete course using delete
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
		try {
		this.courseService.deleteCourse(Long.parseLong(courseId));
		return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
