package com.xjtu.Landlord;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.xjtu.poke.Card;

public class RuleOfLandlord {
	/*********************************
	 (1) ���ţ�ǰ���ᵽ������С˳���3(��С)�����(���)��
����(2) һ�ԣ����Ŵ�С��ͬ���ƣ���3(��С)��2(���)��
����(3) ���ţ����Ŵ�С��ͬ���ƣ�
����(4) ���Ŵ�һ�ţ����Ų���������һ���ƣ�����6-6-6-8���������ŵĴ�С���Ƚϣ�����9-9-9-3�ǹ�8-8-8-A��
����(5) ���Ŵ�һ�ԣ����Ų�����һ�ԣ������˿��еĸ�·(Full House)���������ŵĴ�С���Ƚϣ�
				����Q-Q-Q-6-6�ǹ�10-10-10-K-K��
����(6) ˳�ӣ�����5��������С(��3��A��2�͹ֲ�����)���ƣ�����8-9-10-J-Q��
����(7) ���ԣ�����3��������С(��3��A��2�͹ֲ�����)�Ķ��ӣ�����10-10-J-J-Q-Q-K-K��
����(8) ���ŵ�˳�ӣ�����2��������С(��3��A)�����ţ�����4-4-4-5-5-5��
����(9) ���Ŵ�һ�ŵ�˳�ӣ�ÿ�����Ŷ����϶����һ�����ţ�����7-7-7-8-8-8-3-6��
				��������2�����ã����ܹ����ϵ���2�͹֣�
����(10) ���Ŵ�һ�Ե�˳�ӣ�ÿ�����Ŷ����϶����һ�ԣ�ֻ��Ҫ�����������ľ��У�
				����8-8-8-9-9-9-4-4-J-J����������2�����ã����ܹ�����һ��2�����ܴ�һ�Թ֣���Ϊ���ŹֵĴ�С��һ������
				����Ҫע�⣬���Ŵ��ϵĵ��ź�һ�Բ����ǻ�ϵģ�����3-3-3-4-4-4-6-7-7���ǲ��Ϸ��ģ�
����(11) ը�������Ŵ�С��ͬ���ƣ�ը���ܸǹ����������������ͣ����ը���ܸǹ�С��ը����
����(12) �����һ�Թ֣�����������ϣ��ܹ��ǹ�����ը�����ڵ��κ����ͣ�
����(13) ������·(�Ĵ���)�����������ͣ�һ�����Ŵ����������ţ�����6-6-6-6-8-9��
				��һ�����Ŵ������ԣ�����J-J-J-J-9-9-Q-Q���Ĵ���ֻ�������ŵĴ�С���Ƚϣ�
				ֻ�ܸǹ����Լ�С��ͬ�����͵��Ĵ��������Ŵ����ź����Ŵ��������ڲ�ͬ�����ͣ����ܱ˴˸ǹ�����
				���ܸǹ��������ͣ��Ĵ����ܱ����Լ�С��ը���ǹ���
				
				
				
	NONE,
	SOLO,//����     A      1��
	PAIR,//һ��       44        2�� 
	TRIO,//����       555          3��
	TRIOWITHSOLO,//����һ   5556             4��
	TRIOWITHPAIR,//������    55566              5��
	CHAIN,//˳��      45678   ��������            ����5��  5=<count<=20
	PAIRSCHAIN,//����/˫˳    ����3��������С   ������2�͹�    445566       6=<count<=20
	AIRPLANE,//��˳ /�ɻ�   ������2�͹�        444555     ���6��          6��
	AIRPLANEWITHSMALLSWINGS,//�ɻ���С��   44455578                 8��
	AIRPLANEWITHLARGESWINGS,//�ɻ�������   4445557788            10��
	FOURWITHTWOSOLO,//�Ĵ���   444467                     6��
	FOURWITHTWOPAIRS,//�Ĵ�����  44446677               8��
	SPACESHUTTLE,//����ɻ�    33334444                       8��
	SPACESHUTTLEWITHSMALLSWINGS,//����ɻ���С��     333344446789              12��
	SPACESHUTTLEWITHLARGESWINGS,//����ɻ�������     3333444466778899        16��
	BOMB,//ը��  4444             4��
	KINGBOMB,//��ը   ����С��        2��
	 */
	
	

	private List<Card> cards = null;
	private int value = 0;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean ifWin(List<Card> cards,List<Card> desktopCards){
		this.cards = cards;
		return false;
	}
	public void sort(List<Card> cards){
		Collections.sort(cards);
	}
	
	public void showSort(List<Card> cards){
		
	}
	@Test
	public void Test(){
		List<Card> cards = new ArrayList<>();
		Card c = new Card();
		c = new Card();c.setKey(1);c.setValue(12);cards.add(c);
		c = new Card();c.setKey(2);c.setValue(13);cards.add(c);	
		c = new Card();c.setKey(3);c.setValue(1);cards.add(c);
		c = new Card();c.setKey(4);c.setValue(2);cards.add(c);
		c = new Card();c.setKey(5);c.setValue(3);cards.add(c);
		c = new Card();c.setKey(6);c.setValue(4);cards.add(c);
		c = new Card();c.setKey(7);c.setValue(5);cards.add(c);	
		c = new Card();c.setKey(8);c.setValue(6);cards.add(c);
		c = new Card();c.setKey(9);c.setValue(7);cards.add(c);
		c = new Card();c.setKey(10);c.setValue(8);cards.add(c);
		c = new Card();c.setKey(11);c.setValue(9);cards.add(c);
		c = new Card();c.setKey(12);c.setValue(10);cards.add(c);
		c = new Card();c.setKey(13);c.setValue(11);cards.add(c);
		c = new Card();c.setKey(14);c.setValue(14);cards.add(c);
		c = new Card();c.setKey(15);c.setValue(15);cards.add(c);

		Scanner reader=new Scanner(System.in);
		while(true){
			List<Card> inputCard = new ArrayList<>();
			int key = 0;
			do{
			key = reader.nextInt();
				for(Card cccc:cards){
					if(cccc.getKey() == key){
						inputCard.add(cccc);
					}
				}
			}while(key != -1);
			sort(inputCard);
			System.out.println(judgeType(inputCard));
			System.out.println(value);
		}
	}
	//�ж�����
	public LandlordType judgeType(List<Card> cards){
		int count = cards.size();
		LandlordType pokeType = LandlordType.NONE;
		if(count == 1){//һ����   ����  
			value = getValue(0,cards);
			pokeType = LandlordType.SOLO;
		}else if(count == 2){//������   �Ի���ը 
			if(isPair(cards)){
				pokeType = LandlordType.PAIR;
			}else if(isKingBomb(cards)){
				pokeType = LandlordType.KINGBOMB;
			}
		}else if(count == 3){//������  ����
			if(isTrio(cards)) pokeType = LandlordType.TRIO;
		}else if(count >=4){//���ڵ�������   ����һ����������˳�ӡ����ԡ����ַɻ����Ĵ������Ĵ����ԡ����ֺ���ɻ�
			if(isTrioWithSolo(cards)){//����һ
				pokeType = LandlordType.TRIOWITHSOLO;
			}else if(isTrioWithPair(cards)){//������
				pokeType = LandlordType.TRIOWITHPAIR;
			}else if(isChain(cards)){//˳��
				pokeType = LandlordType.CHAIN;
			}else if(isPairsChain(cards)){//����
				pokeType = LandlordType.PAIRSCHAIN;
			}else{//�ɻ� �� ����ɻ�
				if((pokeType = isAirplane(cards)) == LandlordType.NONE){
					pokeType = isFour(cards);
				}
			}
		}
		return pokeType;
	}
	//һ��
	private boolean isPair(List<Card> cards){//2
		if(getKey(0,cards) == getKey(1,cards)){
			value = getValue(0,cards);
			return true;
		}
		return false;
	}
	//����
	private boolean isTrio(List<Card> cards){//3
		if(getKey(0,cards) == getKey(1,cards) && getKey(0,cards) == getKey(2,cards)){
			value = getValue(0,cards);
			return true;
		}
		return false;
	}
	//����һ
	private boolean isTrioWithSolo(List<Card> cards){//4   1+3/3+1
		if(cards.size() != 4)
			return false;
		if(getKey(0,cards) == getKey(1,cards) && getKey(0,cards) == getKey(2,cards) && getKey(0,cards) != getKey(3,cards)){
			value = getValue(0,cards);
			return true;
		}
		else if(getKey(1,cards) == getKey(2,cards) && getKey(1,cards) == getKey(3,cards) && getKey(0,cards) != getKey(1,cards)){
			value = getValue(1,cards);
			return true;
		}
			
		return false;
	}
	//������
	private boolean isTrioWithPair(List<Card> cards){//5   2+3/3+2
		if(cards.size() == 5){
			if(getKey(0,cards) == getKey(1,cards) && getKey(0,cards) == getKey(2,cards) 
					&& getKey(3,cards) == getKey(4,cards) && getKey(0,cards) != getKey(3,cards)){
				value = getValue(0,cards);//44455
				return true;
			}
			else if(getKey(2,cards) == getKey(3,cards) && getKey(2,cards) == getKey(4,cards) 
					&& getKey(0,cards) == getKey(1,cards) && getKey(0,cards) != getKey(2,cards)){
				value = getValue(2,cards);//33444
				return true;
			}
		}
		return false;
	}
	//˳��  45678   ��������     ����5��  5=<count<=20  ������2�͹�
	private boolean isChain(List<Card> cards){
		if(check2AndGhost(cards) || (cards.size() == 0))
			return false;
		for(int i = 1; i < cards.size(); i++){
			if(getValue(i,cards) - getValue(i-1,cards) != 1)
				return false;
		}
		value = getValue(0,cards);
		return true;
	}

	//����  ����3��������С   ������2�͹�    445566       6=<count<=20
	private boolean isPairsChain(List<Card> cards){
		int count = cards.size();
		if(check2AndGhost(cards))
			return false;
		for(int i = 0; i < count-1; i++){
			if(i%2 == 0){//ż��λ
				if(getValue(i,cards) != getValue(i+1,cards)){
					return false;
				}
			}else{//����λ
				if(getValue(i+1,cards) - getValue(i,cards) != 1){
					return false;
				}
			}		
		}
		value = getValue(0,cards);

		return true;
	}
	//AIRPLANE,//��˳ /�ɻ�   ������2�͹�    444555  444555666   ���6��          6<count<20
	//  44455567     34445556   34555666   4445556677   3344455566    3344555666
	private LandlordType  isAirplane(List<Card> cards){
		int count = cards.size();
		List<Card> airplane = new ArrayList<>();
		int count3 = 0, count2 = 0,count1 = 0;//count3  ��˳������   count2  ���Ƶ�����  count1 ���ŵ�����
		int key = getKey(0,cards); 
		int num = 0;
		Card lastCard = getCard(0,cards);
		for(int i = 0; i < count; i++){
			if(key == getKey(i,cards)){
				num++;
				lastCard = getCard(i,cards);
			}else if(key != getKey(i,cards)){
				if(num == 3){ count3++; airplane.add(lastCard);
				}else if(num == 2){count2++;
				}else if(num == 1){count1++;
				}else return LandlordType.NONE;
				key = getKey(i,cards);
				num = 1;
			}
			if(i == count-1){
				if(num == 3){ count3++; airplane.add(lastCard);
				}else if(num == 2){count2++;
				}else if(num == 1){count1++;
				}else return LandlordType.NONE;
			}
		}
		if(!isChain(airplane)){
			return LandlordType.NONE;
		}
		value = getValue(0,airplane);
		if(count3 == count1 && count2 == 0){//�ɻ���С��
			return LandlordType.AIRPLANEWITHSMALLSWINGS;
		}else if(count3 == count2 && count1 == 0){//�ɻ�������
			return LandlordType.AIRPLANEWITHLARGESWINGS;
		}else if(count2 == 0 && count1 == 0){
			return LandlordType.AIRPLANE;
		}
		value = 0;
		return LandlordType.NONE;
	}
	
	//FOURWITHTWOSOLO,//�Ĵ���   444467     345555     344445           6��
	//FOURWITHTWOPAIRS,//�Ĵ�����  44446677      33445555              8��
	//SPACESHUTTLE,//����ɻ�    33334444                       8��
	//SPACESHUTTLEWITHSMALLSWINGS,//����ɻ���С��     333344446789              |12��
	//SPACESHUTTLEWITHLARGESWINGS,//����ɻ�������   3333444466778899         |16��
	private LandlordType isFour(List<Card> cards){
		int count = cards.size();
		List<Card> four = new ArrayList<>();
		int count4 = 0, count2 = 0,count1 = 0;//count4  ���ŵ�����   count2  ���Ƶ�����  count1 ���ŵ�����
		int key = getKey(0,cards); 
		int num = 0;
		Card lastCard = getCard(0,cards);
		for(int i = 0; i < count; i++){
			if(key == getKey(i,cards)){
				num++;
				lastCard = getCard(i,cards);
			}else if(key != getKey(i,cards)){
				if(num == 4){ count4++; four.add(lastCard);
				}else if(num == 2){count2++;
				}else if(num == 1){count1++;
				}else return LandlordType.NONE;
				key = getKey(i,cards);
				num = 1;
			}
			if(i == count-1){
				if(num == 4){ count4++; four.add(lastCard);
				}else if(num == 2){count2++;
				}else if(num == 1){count1++;
				}else return LandlordType.NONE;
			}
		}
		if(count4 == 1){
			value = getValue(0,four);
			if(count1 == 0 && count2 == 0){//ը��
				return LandlordType.BOMB;
			}else if(count1 == 2 && count2 == 0){//�Ĵ���   444456
				return LandlordType.FOURWITHTWOSOLO;
			}else if(count1 == 0 && count2 == 2){//�Ĵ�����   44445566
				return LandlordType.FOURWITHTWOPAIRS;
			}
		}else if(count4 > 1){
			if(!isChain(four)){
				return LandlordType.NONE;
			}
			value = getValue(0,four);
			if(count1==0 && count2 == 0){//SPACESHUTTLE,����ɻ�    33334444
				return LandlordType.SPACESHUTTLE;
			}else if(count1 == count4*2 && count2 == 0){//SPACESHUTTLEWITHSMALLSWINGS,//����ɻ���С��     333344446789              |12��
				return LandlordType.SPACESHUTTLEWITHSMALLSWINGS;
			}else if(count2 == count4*2 && count1 == 0){//SPACESHUTTLEWITHLARGESWINGS,//����ɻ�������   3333444466778899         |16��
				return LandlordType.SPACESHUTTLEWITHLARGESWINGS;
			}
		}
		value = 0;
		return LandlordType.NONE;
	}
	private boolean isKingBomb(List<Card> cards){
		if(getKey(0, cards) + getKey(1,cards) == 29){
			value = 200;
			return true;
		}
		return false;
	}

	private int getKey(int i,List<Card> cards){
		return cards.get(i).getKey();
	}
	private int getValue(int i,List<Card> cards){
		return cards.get(i).getValue();
	}
	private Card getCard(int i,List<Card> cards){
		return cards.get(i);
	}
	//�鿴�Ƿ���ĳ����
	public boolean findKey(int key,List<Card> cards){
		for(Card c:cards){
			if(c.getKey() == key){
				return true;
			}
		}
		return false;
	}
	//�鿴�Ƿ���2����
	public boolean check2AndGhost(List<Card> cards){
		for(Card c:cards){
			if(c.getKey() == 2 || c.getKey() == 14 || c.getKey() == 15){
				return true;
			}
		}
		return false;
	}
	
	
}
