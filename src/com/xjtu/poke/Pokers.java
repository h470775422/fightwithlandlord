package com.xjtu.poke;

import java.util.ArrayList;
import java.util.List;

public class Pokers {
	private int pokerValueNumber = 15;
	private  List<Card> pokerList;
	
	public Pokers()
	{
		pokerList = new ArrayList<Card>();
		initialize();
		for(int i=0;i<pokerList.size();++i)
		{
			Card card = new Card();
			card = pokerList.get(i);
			System.out.println("ÅÆÖµ£º"+card.getValue()+"»¨É«£º"+card.getColor());
		}
	}
	
	public void initialize()
	{
		for( int i=1; i<=pokerValueNumber; i++ )
		{
			if(i>13)
			{
				Card card_small = new Card();
				card_small.setValue(i);
				card_small.setColor(Card.PokerColor.joker_small);
				pokerList.add(card_small);
				Card card_big = new Card();
				card_big.setValue(i);
				card_big.setValue(++i);
				card_big.setColor(Card.PokerColor.joker_big);
				pokerList.add(card_big);
				break;
			}else
			{	
				for(int  j=0; j<4; j++)
				{
					Card card = new Card();
					card.setValue(i);
					switch(j)
					{
					case 0:
						card.setColor(Card.PokerColor.heart);
						pokerList.add(card);
						break;
					case 1:
						card.setColor(Card.PokerColor.spade);
						pokerList.add(card);
						break;
					case 2:
						card.setColor(Card.PokerColor.club);
						pokerList.add(card);
						break;
					case 3:
						card.setColor(Card.PokerColor.diamond);
						pokerList.add(card);
						break;
					}	
				}
			}	
		}	
	}

	public List<Card> getPokerList() {
		return pokerList;
	}

	public void setPokerList(List<Card> pokerList) {
		this.pokerList = pokerList;
	}
	
}
