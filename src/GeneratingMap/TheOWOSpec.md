# The .owo file format for rasterized map data
Ladies and lases, may I present you, the one and only, the [ÒwÓ](https://www.urbandictionary.com/define.php?term=%C3%92w%C3%93) file format:

 - UTF8
 - lists are newline-separated
 - individual entities are newline-separated
 - pixels are represented by their category number
 - pixels are in row-major order
 - categpry number can be used for indexing into category labels

entities:
```
comment................<string>
number of catLabels....<uint>
number of categories...<uint>
size x.................<uint>
size y.................<uint>
catLabels........<list of string codes>
categories.....<list of shorts, with values [0-len(category labels)], represent individual pixels>
category metadata <list of base64 encoded strings, optional displayed directly to the user, same cardinality as category labels>
```