package com.ecommerce.globalData;

import java.util.List;

import com.ecommerce.dto.CartDTO;

public class GlobalData {

	public static int grandTotal(List<CartDTO> cartItem) {

		int total = 0, count = 0;
		while (count < cartItem.size()) {

			total = total + (cartItem.get(count).getQuantity() * cartItem.get(count).getPrice());
			count++;
		}
		return total;
	}


}
