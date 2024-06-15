package com.file.entity;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public byte[] getImageData() {
//		return imageData;
//	}
//	public void setImageData(byte[] imageData) {
//		this.imageData = imageData;
//	}
//	public ImageData(Long id, String name, String type, byte[] imageData) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.type = type;
//		this.imageData = imageData;
//	}
//	public ImageData() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
    
    
}
