package com.liaojiexin.videoweb.service.impl;

import com.liaojiexin.videoweb.entity.Contact;
import com.liaojiexin.videoweb.mapper.ContactMapper;
import com.liaojiexin.videoweb.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired  //注入DAO
    private ContactMapper contactMapper;

    @Override
    public void insertContact(String name, String email, String message) {       //插入留言
            Contact contact=new Contact();
            contact.setName(name);
            contact.setEmail(email);
            contact.setMessage(message);
            contactMapper.insert(contact);
    }
}
