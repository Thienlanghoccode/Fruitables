package com.haui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor	
@Data
@Getter
@Setter
@ToString
public class Product {
	private int id;
	private String name;
	private long price;
	private String image;
	private String des;
	private Category category; //cate_id

}
