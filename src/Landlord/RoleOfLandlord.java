package Landlord;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.xjtu.poke.Card;

public class RoleOfLandlord {
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
	
	
	private LandlordType pokeType = LandlordType.NONE;
	private List<Card> handCards = null;
	private int count = 0;
	
	
	public boolean ifWin(List<Card> handCards,List<Card> desktopCards){
		this.handCards = handCards;
		count = handCards.size();
		return false;
	}
	public void sort(){
		Collections.sort(handCards);
	}

	public LandlordType judgeType(List<Card> cards){
		switch(count){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			
		}
		return LandlordType.NONE;
	}

	private boolean isPair(){//2
		if(getKey(0) == getKey(1))
			return true;
		return false;
	}
	private boolean isTrio(){//3
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2))
			return true;
		return false;
	}
	private boolean isTrioWithSolo(){//4   1+3/3+1
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2) && getKey(0) != getKey(3))
			return true;
		else if(getKey(1) == getKey(2) && getKey(1) == getKey(3) && getKey(0) != getKey(1))
			return true;
		return false;
	}
	private boolean isTrioWithPair(){//5   2+3/3+2
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2) && getKey(3) == getKey(4))
			return true;
		else if(getKey(2) == getKey(3) && getKey(2) == getKey(4) && getKey(0) == getKey(1))
			return true;
		return false;
	}
	private boolean isChain(){
		if(findKey(2,handCards))
			return false;
		for(int i = 1; i < handCards.size(); i++){
			if(getKey(i) - getKey(i-1) != 1)
				return false;
		}
		return true;
	}
	private boolean isPairsChain(){
		if(findKey(2,handCards))
			return false;
		if(count % 2 == 0){
			for(int i = 0; i < count-1; i=i+2){
				if(getKey(i) != getKey(i+1))
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean  isAirplane(){
		if(findKey(2,handCards))
			return false;
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2) &&
				getKey(3) == getKey(4) && getKey(3) == getKey(5))
			return true;
		return false;
	}
	
	
	private int getKey(int i){
		return handCards.get(i).getKey();
	}
	
	private boolean findKey(int key,List<Card> cards){
		for(Card c:cards){
			if(c.getKey() == key){
				return true;
			}
		}
		return false;
	}
	
	
}
