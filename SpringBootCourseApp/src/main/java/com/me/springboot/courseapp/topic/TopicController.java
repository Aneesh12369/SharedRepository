package com.me.springboot.courseapp.topic;

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
import com.me.springboot.courseapp.exceptions.IllegalTopicJsonArgumentException;
import com.me.springboot.courseapp.util.ErrorDescription;

@RestController
public class TopicController {

	@Autowired
	private TopicService service;

	@GetMapping("/topics")
	public List<Topic> getAllTopics() {
		return service.fetchAllTopics();
	}

	@PostMapping("/topics")
	public Object registerTopic(@Valid @RequestBody Topic topic, BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			List<String> message = new ArrayList<>();

			for (FieldError e : errors) {
				message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}

			throw new IllegalTopicJsonArgumentException(message.toString());
		}
		service.insertTopic(topic);
		ErrorDescription desc = new ErrorDescription();
		desc.setErrorcode(0);
		desc.setDescripton("Inserted successfully");
		return desc;
	}

	@GetMapping("/topics/{id}")
	public Topic getTopic(@PathVariable("id") Integer id) {
		Topic topic = service.fetchTopicById(id);
		if (topic == null) {
			throw new CourseNotFoundException("Topic does not exist");
		}
		return topic;
	}

	@PutMapping("/topics/{id}")
	public Object putTopic(@PathVariable("id") Integer id, @RequestBody @Valid Topic topic, BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			List<String> message = new ArrayList<>();

			for (FieldError e : errors) {
				message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}

			throw new IllegalTopicJsonArgumentException(message.toString());
		}
		topic.setId(id);
		service.updateTopic(topic);
		ErrorDescription desc = new ErrorDescription();
		desc.setErrorcode(1);
		desc.setDescripton("Updated successfully");
		return desc;

	}

	@DeleteMapping("/topics/{id}")
	public ErrorDescription deleteTopic(@PathVariable Integer id) {
		service.deleteTopic(id);
		ErrorDescription desc = new ErrorDescription();
		desc.setErrorcode(1);
		desc.setDescripton("Deleted successfully");
		return desc;
	}

}
