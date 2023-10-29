package com.capstone.network.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RelatedNetworkElement {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parent_network_element_id")
	private NetworkElement parentNetworkElement;

	@ManyToOne
	@JoinColumn(name = "related_network_element_id")
	private NetworkElement relatedNetworkElement;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 


}
