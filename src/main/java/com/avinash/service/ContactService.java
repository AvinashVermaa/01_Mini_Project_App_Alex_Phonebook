package com.avinash.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avinash.entities.ContactInfo;

@Service
public interface ContactService {

	//to save the contact data into db:
	public String saveContact(ContactInfo contact);
	
	//to get the contact data based on the id:
	public ContactInfo getContactDataBasedOnId(Integer sno);
	
	//to get all the contacts data:
	public List<ContactInfo> getAllContacts();
	
	//to update the contactInfo based on id and data:
	public void updateContactsData(Integer sno,ContactInfo contact);
	
	//to delete the contactInfo based on the id:
	public void deleteContactsData(Integer sno);
	
	//to update the current activeStatus:
	public void updateCurrentActiveStatus(Integer sno,String status);
}
