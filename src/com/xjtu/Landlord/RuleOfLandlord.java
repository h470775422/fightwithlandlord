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
	 (1) 单张：前面提到过，大小顺序从3(最小)到大怪(最大)；
　　(2) 一对：两张大小相同的牌，从3(最小)到2(最大)；
　　(3) 三张：三张大小相同的牌；
　　(4) 三张带一张：三张并带上任意一张牌，例如6-6-6-8，根据三张的大小来比较，例如9-9-9-3盖过8-8-8-A；
　　(5) 三张带一对：三张并带上一对，类似扑克中的副路(Full House)，根据三张的大小来比较，
				例如Q-Q-Q-6-6盖过10-10-10-K-K；
　　(6) 顺子：至少5张连续大小(从3到A，2和怪不能用)的牌，例如8-9-10-J-Q；
　　(7) 连对：至少3个连续大小(从3到A，2和怪不能用)的对子，例如10-10-J-J-Q-Q-K-K；
　　(8) 三张的顺子：至少2个连续大小(从3到A)的三张，例如4-4-4-5-5-5；
　　(9) 三张带一张的顺子：每个三张都带上额外的一个单张，例如7-7-7-8-8-8-3-6，
				尽管三张2不能用，但能够带上单张2和怪；
　　(10) 三张带一对的顺子：每个三张都带上额外的一对，只需要三张是连续的就行，
				例如8-8-8-9-9-9-4-4-J-J，尽管三张2不能用，但能够带上一对2【不能带一对怪，因为两张怪的大小不一样】，
				这里要注意，三张带上的单张和一对不能是混合的，例如3-3-3-4-4-4-6-7-7就是不合法的；
　　(11) 炸弹：四张大小相同的牌，炸弹能盖过除火箭外的其他牌型，大的炸弹能盖过小的炸弹；
　　(12) 火箭：一对怪，这是最大的组合，能够盖过包括炸弹在内的任何牌型；
　　(13) 四张套路(四带二)：有两种牌型，一个四张带上两个单张，例如6-6-6-6-8-9，
				或一个四张带上两对，例如J-J-J-J-9-9-Q-Q，四带二只根据四张的大小来比较，
				只能盖过比自己小的同样牌型的四带二【四张带二张和四张带二对属于不同的牌型，不能彼此盖过】，
				不能盖过其他牌型，四带二能被比自己小的炸弹盖过。
				
				
				
	NONE,
	SOLO,//单张     A      1张
	PAIR,//一对       44        2张 
	TRIO,//三张       555          3张
	TRIOWITHSOLO,//三带一   5556             4张
	TRIOWITHPAIR,//三带二    55566              5张
	CHAIN,//顺子      45678   最少五张            最少5张  5=<count<=20
	PAIRSCHAIN,//连对/双顺    至少3个连续大小   不能有2和怪    445566       6=<count<=20
	AIRPLANE,//三顺 /飞机   不能有2和怪        444555     最多6连          6张
	AIRPLANEWITHSMALLSWINGS,//飞机带小翼   44455578                 8张
	AIRPLANEWITHLARGESWINGS,//飞机带大翼   4445557788            10张
	FOURWITHTWOSOLO,//四带二   444467                     6张
	FOURWITHTWOPAIRS,//四带两对  44446677               8张
	SPACESHUTTLE,//航天飞机    33334444                       8张
	SPACESHUTTLEWITHSMALLSWINGS,//航天飞机带小翼     333344446789              12张
	SPACESHUTTLEWITHLARGESWINGS,//航天飞机带大翼     3333444466778899        16张
	BOMB,//炸弹  4444             4张
	KINGBOMB,//王炸   大王小王        2张
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
	//判断牌型
	public LandlordType judgeType(List<Card> cards){
		int count = cards.size();
		LandlordType pokeType = LandlordType.NONE;
		if(count == 1){//一张牌   单张  
			value = getValue(0,cards);
			pokeType = LandlordType.SOLO;
		}else if(count == 2){//两张牌   对或王炸 
			if(isPair(cards)){
				pokeType = LandlordType.PAIR;
			}else if(isKingBomb(cards)){
				pokeType = LandlordType.KINGBOMB;
			}
		}else if(count == 3){//三张牌  三张
			if(isTrio(cards)) pokeType = LandlordType.TRIO;
		}else if(count >=4){//大于等于四张   三带一、三带二、顺子、连对、各种飞机、四带二、四带两对、各种航天飞机
			if(isTrioWithSolo(cards)){//三带一
				pokeType = LandlordType.TRIOWITHSOLO;
			}else if(isTrioWithPair(cards)){//三带二
				pokeType = LandlordType.TRIOWITHPAIR;
			}else if(isChain(cards)){//顺子
				pokeType = LandlordType.CHAIN;
			}else if(isPairsChain(cards)){//连对
				pokeType = LandlordType.PAIRSCHAIN;
			}else{//飞机 和 航天飞机
				if((pokeType = isAirplane(cards)) == LandlordType.NONE){
					pokeType = isFour(cards);
				}
			}
		}
		return pokeType;
	}
	//一对
	private boolean isPair(List<Card> cards){//2
		if(getKey(0,cards) == getKey(1,cards)){
			value = getValue(0,cards);
			return true;
		}
		return false;
	}
	//三张
	private boolean isTrio(List<Card> cards){//3
		if(getKey(0,cards) == getKey(1,cards) && getKey(0,cards) == getKey(2,cards)){
			value = getValue(0,cards);
			return true;
		}
		return false;
	}
	//三带一
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
	//三带二
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
	//顺子  45678   最少五张     最少5张  5=<count<=20  不能有2和怪
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

	//连对  至少3个连续大小   不能有2和怪    445566       6=<count<=20
	private boolean isPairsChain(List<Card> cards){
		int count = cards.size();
		if(check2AndGhost(cards))
			return false;
		for(int i = 0; i < count-1; i++){
			if(i%2 == 0){//偶数位
				if(getValue(i,cards) != getValue(i+1,cards)){
					return false;
				}
			}else{//奇数位
				if(getValue(i+1,cards) - getValue(i,cards) != 1){
					return false;
				}
			}		
		}
		value = getValue(0,cards);

		return true;
	}
	//AIRPLANE,//三顺 /飞机   不能有2和怪    444555  444555666   最多6连          6<count<20
	//  44455567     34445556   34555666   4445556677   3344455566    3344555666
	private LandlordType  isAirplane(List<Card> cards){
		int count = cards.size();
		List<Card> airplane = new ArrayList<>();
		int count3 = 0, count2 = 0,count1 = 0;//count3  三顺的数量   count2  对牌的数量  count1 单排的数量
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
		if(count3 == count1 && count2 == 0){//飞机带小翼
			return LandlordType.AIRPLANEWITHSMALLSWINGS;
		}else if(count3 == count2 && count1 == 0){//飞机带大翼
			return LandlordType.AIRPLANEWITHLARGESWINGS;
		}else if(count2 == 0 && count1 == 0){
			return LandlordType.AIRPLANE;
		}
		value = 0;
		return LandlordType.NONE;
	}
	
	//FOURWITHTWOSOLO,//四带二   444467     345555     344445           6张
	//FOURWITHTWOPAIRS,//四带两对  44446677      33445555              8张
	//SPACESHUTTLE,//航天飞机    33334444                       8张
	//SPACESHUTTLEWITHSMALLSWINGS,//航天飞机带小翼     333344446789              |12张
	//SPACESHUTTLEWITHLARGESWINGS,//航天飞机带大翼   3333444466778899         |16张
	private LandlordType isFour(List<Card> cards){
		int count = cards.size();
		List<Card> four = new ArrayList<>();
		int count4 = 0, count2 = 0,count1 = 0;//count4  四张的数量   count2  对牌的数量  count1 单排的数量
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
			if(count1 == 0 && count2 == 0){//炸弹
				return LandlordType.BOMB;
			}else if(count1 == 2 && count2 == 0){//四带二   444456
				return LandlordType.FOURWITHTWOSOLO;
			}else if(count1 == 0 && count2 == 2){//四带两对   44445566
				return LandlordType.FOURWITHTWOPAIRS;
			}
		}else if(count4 > 1){
			if(!isChain(four)){
				return LandlordType.NONE;
			}
			value = getValue(0,four);
			if(count1==0 && count2 == 0){//SPACESHUTTLE,航天飞机    33334444
				return LandlordType.SPACESHUTTLE;
			}else if(count1 == count4*2 && count2 == 0){//SPACESHUTTLEWITHSMALLSWINGS,//航天飞机带小翼     333344446789              |12张
				return LandlordType.SPACESHUTTLEWITHSMALLSWINGS;
			}else if(count2 == count4*2 && count1 == 0){//SPACESHUTTLEWITHLARGESWINGS,//航天飞机带大翼   3333444466778899         |16张
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
	//查看是否有某个键
	public boolean findKey(int key,List<Card> cards){
		for(Card c:cards){
			if(c.getKey() == key){
				return true;
			}
		}
		return false;
	}
	//查看是否有2和王
	public boolean check2AndGhost(List<Card> cards){
		for(Card c:cards){
			if(c.getKey() == 2 || c.getKey() == 14 || c.getKey() == 15){
				return true;
			}
		}
		return false;
	}
	
	
}
