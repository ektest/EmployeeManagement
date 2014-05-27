package com.emre.staffmanagement.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Note")
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String note;
	
	public Note(){
		// This is for JPA not for us!
	}

	public Note (String text){
		this.date = new Date();
		this.note = text;
	}

	@Override
	public String toString() {
		return "Note [date=" + date + ", note=" + note + "]";
	}

}
