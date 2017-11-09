package com.me.springboot.courseapp.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.springboot.courseapp.exceptions.IllegalCourseJsonArgumentException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

	public List<Course> fetchAllCourses(Integer id) {

		Iterable<Course> allTopics = null;
		List<Course> listOfTopics = null;
		listOfTopics = new ArrayList<Course>();
		allTopics = repository.findByTopicId(id);
		for (Course topic : allTopics) {
			listOfTopics.add(topic);
		}
		return listOfTopics;
	}
	
	public void insertCourse(Course topic){
		Course save = repository.save(topic);
		System.out.println(save);
		if(save == null){
			throw new IllegalCourseJsonArgumentException("Parameters are not correct Form Course");
		}
	}
	
	
	public Course fetchCourseById(Integer id){
		return repository.findOne(id);
	}
	
	public void updateCourse(Course topic){
		Course update = repository.save(topic);
		System.out.println(update);
		if(update == null){
			throw new IllegalCourseJsonArgumentException("Parameters are not correct Form Course");
		}
	}
	
	public void deleteCourse(Integer id){
		repository.delete(id);
	}
	
	

}
