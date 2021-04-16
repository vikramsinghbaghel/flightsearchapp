package com.nagarro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.nagarro.entities.CSVData;

@Component
public class CSVDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int save(CSVData temp)
	{
		return (Integer) this.hibernateTemplate.save(temp);
		
	}
	
	public List<CSVData> getAll()
	{
		
		return	this.hibernateTemplate.loadAll(CSVData.class);
	
	}
	
}