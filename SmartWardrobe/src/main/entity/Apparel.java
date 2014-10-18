package main.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.constants.Category;
import main.constants.Tags;

public class Apparel {

	private static long idCounter = 0;

	private static long getNextId() {
		return idCounter++;
	}

	private Long id;
	private String imagePath;
	private Category category;
	private Boolean inWash;
	private Integer howWarm;
	private Integer wear;
	private Date lastWahsedDate;
	private Date lastWornDate;
	private Set<Long> tags;

	public Apparel(Long id) {
		this.id = id;
	}

	public Apparel(String imagePath, Category category, Integer howWarm,
			List<String> tags) {
		super();
		this.id = getNextId();
		this.imagePath = imagePath;
		this.category = category;
		this.inWash = false;
		this.howWarm = howWarm;
		this.wear = 0;
		this.lastWahsedDate = null;
		this.lastWornDate = null;
		this.tags = new HashSet<Long>();
		for (String tag : tags) {
			this.tags.add(Tags.getOrCreateTagId(tag));
		}
	}

	public Long getId() {
		return id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getInWash() {
		return inWash;
	}

	public void setInWash(Boolean inWash) {
		this.inWash = inWash;
		if (inWash) {
			wear = 0;
			lastWahsedDate = new Date();
			lastWornDate = null;
		}
	}

	public Integer getHowWarm() {
		return howWarm;
	}

	public void setHowWarm(Integer howWarm) {
		this.howWarm = howWarm;
	}

	public Integer getWear() {
		return wear;
	}

	public void setWear(Integer wear) {
		this.wear = wear;
	}

	public void incWear() {
		wear++;
	}

	public Date getLastWahsedDate() {
		return lastWahsedDate;
	}

	public void setLastWahsedDate(Date lastWahsedDate) {
		this.lastWahsedDate = lastWahsedDate;
	}

	public Date getLastWornDate() {
		return lastWornDate;
	}

	public void setLastWornDate(Date lastWornDate) {
		this.lastWornDate = lastWornDate;
	}

	public Set<Long> getTags() {
		return tags;
	}

	public void setTags(Set<Long> tags) {
		this.tags = tags;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Apparel ap) {
		return id == ap.id;
	}

}
