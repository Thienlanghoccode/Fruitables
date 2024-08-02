package com.haui.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CartDetails {
	private String id;
	private String buyer;
	private String email;
	private long sumToTal;
	private Date orderDate;
}
