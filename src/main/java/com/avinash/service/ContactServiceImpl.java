package com.avinash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinash.entities.ContactInfo;
import com.avinash.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepo;

	@Autowired
	public ContactServiceImpl(ContactRepository contactRepo) {
		System.out.println("ContactServiceImpl :: constructor");
		this.contactRepo = contactRepo;
	}

	@Override
	public String saveContact(ContactInfo contact) {
		System.out.println(contact);
		ContactInfo savedContactData = contactRepo.save(contact);
		if (savedContactData.getSno() != null) {
			
			return "Record Updated succesfully";
		} else {
			contact.setActiveStatus("Y");
			return "Record Saved Successfully";
		}
	}

	@Override
	public ContactInfo getContactDataBasedOnId(Integer sno) {
		System.out.println(sno);
		Optional<ContactInfo> optional = contactRepo.findById(sno);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<ContactInfo> getAllContacts() {
		List<ContactInfo> lists = contactRepo.findAll();
		return lists;
	}

	@Override
	public void updateContactsData(Integer sno, ContactInfo contact) {
		boolean result = contactRepo.existsById(sno);
		if (result) {
			Optional<ContactInfo> optional = contactRepo.findById(sno);
			if (optional.isPresent()) {
				contactRepo.save(contact);
			}
		}

	}

	@Override
	public void deleteContactsData(Integer sno) {
		boolean result = contactRepo.existsById(sno);
		if (result) {
			contactRepo.deleteById(sno);
		}

	}

	@Override
	public void updateCurrentActiveStatus(Integer sno, String status) {
		contactRepo.setActiveStatus(status,sno);
	}

}
