package com.development.challenge.model;

import java.util.Objects;

public class AccessModel {

	public AccessModel() {
	}

	private String url;
	
	private int timesAccessed;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimesAccessed() {
		return timesAccessed;
	}

	public void setTimesAccessed(int timesAccessed) {
		this.timesAccessed = timesAccessed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(timesAccessed, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessModel other = (AccessModel) obj;
		return Objects.equals(timesAccessed, other.timesAccessed) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "AccessModel [url=" + url + ", timesAccessed=" + timesAccessed + "]";
	}

	public AccessModel(String url, int timesAccessed) {
		super();
		this.url = url;
		this.timesAccessed = timesAccessed;
	}
}
