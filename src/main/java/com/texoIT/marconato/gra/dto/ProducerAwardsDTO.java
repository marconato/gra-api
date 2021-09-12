package com.texoIT.marconato.gra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducerAwardsDTO {
	
	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;

}
