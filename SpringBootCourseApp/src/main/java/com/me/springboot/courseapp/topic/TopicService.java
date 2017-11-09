package com.me.springboot.courseapp.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.springboot.courseapp.exceptions.IllegalTopicJsonArgumentException;

@Service
public class TopicService {

	@Autowired
	private TopicRepository repository;

	public List<Topic> fetchAllTopics() {

		Iterable<Topic> allTopics = null;
		List<Topic> listOfTopics = null;
		listOfTopics = new ArrayList<Topic>();
		allTopics = repository.findAll();
		for (Topic topic : allTopics) {
			listOfTopics.add(topic);
		}
		return listOfTopics;
	}
	
	public void insertTopic(Topic topic){
		Topic save = repository.save(topic);
		System.out.println(save);
		if(save == null){
			throw new IllegalTopicJsonArgumentException("Parameters are not correct");
		}
	}
	
	
	public Topic fetchTopicById(Integer id){
		return repository.findOne(id);
	}
	
	public void updateTopic(Topic topic){
		Topic update = repository.save(topic);
		System.out.println(update);
		if(update == null){
			throw new IllegalTopicJsonArgumentException("Parameters are not correct");
		}
	}
	
	public void deleteTopic(Integer id){
		repository.delete(id);
	}
	
	

}
