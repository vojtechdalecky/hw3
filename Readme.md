## Standard deviation

Given a vector in the form of a list of numbers, calculate the standard deviation of that vector.

```
def standardDeviation(vector: List[Double]): Double = ???
```  

## Letter frequency ranking

Calculate the letter frequency in a corpus and return its letter frequency ranking. 

The corpus is a string representing some text in some language

The letter frequency ranking is a string of letters. A letter is a member of a frequency ranking if, and only if, it can 
be found in the corpus. the letters are sorted according to how often they occur in the corpus. The leftmost letter in 
the ranking is the letter that occurs the most in the corpus. The rightmost letter in the ranking is the letter that 
occurs the least in the corpus. If two letters occur the same number of times, they are arranged alphabetically.

The frequency rating ignores case, spaces, and punctuation.

For instance, for the string 

```
Sic semper tyranis.
``` 

the letter frequency rating is the following string:

```      
seiracmnpty
```

Implement this calculation as the following function:

``` scala 
def letterFrequencyRanking(corpus: List[String]): String = ???
```

## Katakana to romanji

Implement a function that converts a word in katakana into a western-style alphabet called Romanji.

Katakana is a Japanese writing system that consists of syllabograms: one symbol represents one syllable. Here is a
simplified guide on how it works (the system is simplified for the purpose of the exercise).

Katakana has the following syllabograms representing single vowels:

```
ア    a
イ    i
ウ    u
エ    e
オ    o
```

Most katakana syllabograms represent two sounds, one consonant and one vowel and can be constructed form this table:

```
      a    i     u     e    o
k    カ    キ    ク    ケ    コ
g    ガ    ギ    グ    ゲ    ゴ
s    サ    シ    ス    セ    ソ
z    ザ    ジ    ズ    ゼ    ゾ
t    タ    チ    ツ    テ    ト
d    ダ    ヂ    ヅ    デ    ド
n    ナ    ニ    ヌ    ネ    ノ
h    ハ    ヒ    フ    ヘ    ホ
b    バ    ビ    ブ    ベ    ボ
p    パ    ピ    プ    ペ    ポ
m    マ    ミ    ム    メ    モ
y    ヤ          ユ          ヨ
r    ラ    リ    ル    レ    ロ
w    ワ    ヰ         ヱ     ヲ
```

For example

```
キ    ki
ガ    ga
ロ    ro
```

The symbol ン represents a nasal n and is transcribed as follows:

```
ン   n
```

Symbols: ャ, ュ or ョ occur after a syllabogram that ends in "i". They modify the second sound of the syllabogram to end
in ya, yu, or yo instead:

```
キ    ki
キャ  kya
キュ  kyu
キョ  kyo
```

Symbol ッ doubles the following consonant:

```
サカ   saka
サッカ sakka
```

Symbol ン doubles the following consonant in the case of na, ni, nu, ne, no syllables:

```
ナノ   nano
ナンノ nanno
```

Symbol ー lengthens the preceding vowel. A long vowel is written with a line over it. For instance ē represents long e.

```
メル    meru
メール  mēru
```

Implement the following function that translated katakana to romanji:

``` scala
  def romanji(katakana: String): String = ???
```

## Gray code

The reflected binary code is a way of sequencing numbers invented by Frank Gray which orders binary numbers so that 
every two successive values differ by only one bit. It has applications in electronics.

For instance, the numbers 2 and 3 differ by 1 bit, but 1 and 2 differ by 2 bits, and 3 and 4 normally differ by 3 bits: 

```
0: 0000
1: 0001
2: 0010
3: 0011
4: 0100   
```

In gray code the numbers are constructed as follows:

```
0: 0000
1: 0001
2: 0011
3: 0010
4: 0110
```

Find more information on constructing Gray codes here: https://en.wikipedia.org/wiki/Gray_code#Constructing_an_n-bit_Gray_code

Implement a function that generates gray codes for a given number of bits:

``` scala
  def gray(bits: Int): List[String] = ???
```

