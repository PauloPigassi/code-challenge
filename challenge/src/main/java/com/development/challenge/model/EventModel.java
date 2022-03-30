package com.development.challenge.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "event")
public class EventModel {
	
	public EventModel() {
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String userToken;
    
    private String url;
    
    private LocalDateTime accessDate;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public LocalDateTime getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(LocalDateTime accessDate) {
		this.accessDate = accessDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessDate, eventId, url, userToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventModel other = (EventModel) obj;
		return Objects.equals(accessDate, other.accessDate) && Objects.equals(eventId, other.eventId)
				&& Objects.equals(url, other.url) && Objects.equals(userToken, other.userToken);
	}

	@Override
	public String toString() {
		return "EventModel [eventId=" + eventId + ", userToken=" + userToken + ", url=" + url + ", accessDate="
				+ accessDate + "]";
	}

	public EventModel(Long eventId, String userToken, String url, LocalDateTime accessDate) {
		super();
		this.eventId = eventId;
		this.userToken = userToken;
		this.url = url;
		this.accessDate = accessDate;
	}   
}
