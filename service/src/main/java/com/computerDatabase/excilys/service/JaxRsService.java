package com.computerDatabase.excilys.service;

import java.util.List;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerDatabase.excilys.config.Authenticator;
import com.computerDatabase.excilys.model.Computer;

@Service
public class JaxRsService {
	
	private static final String REST_CREATE_COMPUTER_URI = "http://127.0.0.1:8080/webapp/addComputer-rest/";
	private static final String REST_DELETE_COMPUTER_URI = "http://127.0.0.1:8080/webapp/deleteComputer-rest/";
	private static final String REST_LIST_COMPUTERS_URI  = "http://127.0.0.1:8080/webapp/dashboard-rest";
	private static final String REST_DETAIL_COMPUTER_URI = "http://127.0.0.1:8080/webapp/getComputer-rest";
	private static final String REST_UPDATE_COMPUTER_URI = "http://127.0.0.1:8080/webapp/editComputer-rest/";

	@Autowired
	public JaxRsService() {}

	public List<?> listJSONComputers() {
		Client client = ClientBuilder.newClient().register(new Authenticator("Sylvie", "novembre1"));
		
		return client.target(REST_LIST_COMPUTERS_URI).request(MediaType.APPLICATION_JSON).get(List.class);
	}
	
	public List<?> listJSONDetailsComputer() {
		Client client = ClientBuilder.newClient().register(new Authenticator("Sylvie", "novembre1"));
		
		return client.target(REST_DETAIL_COMPUTER_URI).request(MediaType.APPLICATION_JSON).get(List.class);
	}
	
	public Response deleteJSONComputer(long id) {
		Client client = ClientBuilder.newClient().register(new Authenticator("Sylvie", "novembre1"));
		
		return client.target(REST_DELETE_COMPUTER_URI + id).request(MediaType.APPLICATION_JSON).delete();
	}

	public Response createJSONComputer(Computer computer) {
		Client client = ClientBuilder.newClient().register(new Authenticator("Sylvie", "novembre1"));
		
		return client.target(REST_CREATE_COMPUTER_URI).request(MediaType.APPLICATION_JSON).put(Entity.json(computer));
	}
	
	public Response updateJSONComputer(Computer computer) {
		Client client = ClientBuilder.newClient().register(new Authenticator("Sylvie", "novembre1"));
		
		return client.target(REST_UPDATE_COMPUTER_URI + computer.getId()).request(MediaType.APPLICATION_JSON).put(Entity.json(computer));
	}
}
