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
public class CartItem  {
	private String id;
	private int quantity;
	private long unitPrice;
	private Product product;
	private Cart cart;

}
