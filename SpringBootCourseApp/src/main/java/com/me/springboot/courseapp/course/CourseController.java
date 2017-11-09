package com.me.springboot.courseapp.course;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.springboot.courseapp.exceptions.CourseNotFoundException;
import com.me.springboot.courseapp.exceptions.IllegalCourseJsonArgumentException;
import com.me.springboot.courseapp.topic.Topic;
import com.me.springboot.courseapp.util.ErrorDescription;

@RestController
public class CourseController {

	@Autowired
	private CourseService service;

	@GetMapping("/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable Integer id) {
		return service.fetchAllCourses(id);
	}

	@PostMapping("/topics/{id}/courses")
	public Object registerCourse(@PathVariable("id") Integer id,@RequestBody @Valid Course topic, BindingResult result) {
		System.out.println("ffffffffffffffffffffffffff "+topic);
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			List<String> message = new ArrayList<>();

			for (FieldError e : errors) {
				message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}

			throw new IllegalCourseJsonArgumentException(message.toString());
		}
		topic.setTopic(new Topic(id,"",""));
		service.insertCourse(topic);
		ErrorDescription desc = new ErrorDescription();
		desc.setErrorcode(0);
		desc.setDescripton("Inserted successfully");
		return desc;
	}

	@GetMapping("/topics/{id}/courses/{courseId}")
	public Course getCourse(@PathVariable("id") Integer id,@PathVariable("courseId") Integer courseId) {
		Course topic = service.fetchCourseById(courseId);
		if (topic == null) {
			throw new CourseNotFoundException("Topic does not exist");
		}
		return topic;
	}

	@PutMapping("/topics/{id}/courses/{courseId}")
	public Object putCourse(@PathVariable("id") Integer id, @RequestBody @Valid Course topic, BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			List<String> message = new ArrayList<>();

			for (FieldError e : errors) {
				message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}

			throw new IllegalCourseJsonArgumentException(message.toString());
		}
		Topic t = new Topic(id,"","");
		topic.setTopic(t);
		service.updateCourse(topic);
		ErrorDescription desc = new ErrorDescription();
		desc.setErrorcode(1);
		desc.setDescripton("Updated successfully");
		return desc;

	}

	@DeleteMapping("/topics/{id}/courses/{courseId}")
	public ErrorDescription deleteCourse(@PathVariable("courseId") Integer id) {
		service.deleteCourse(id);
		ErrorDescription desc = new ErrorDescription();
		desc.setErrorcode(1);
		desc.setDescripton("Deleted successfully");
		return desc;
	}

}
