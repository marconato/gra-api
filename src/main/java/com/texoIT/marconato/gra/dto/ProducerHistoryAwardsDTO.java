package com.texoIT.marconato.gra.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProducerHistoryAwardsDTO {

	private List<ProducerAwardsDTO> min = new ArrayList<>();

	private List<ProducerAwardsDTO> max = new ArrayList<>();

	public void addMin(ProducerAwardsDTO min) {
		this.getMin().add(min);
	}

	public void addMax(ProducerAwardsDTO max) {
		this.getMax().add(max);
	}
}
