[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)][license]

# NumberConvert

## Description
Convert numeric values into their textual representations in the Hungarian language.

## Dependency
**Maven:**
```xlm
<dependency>
    <groupId>github.magyarzoli</groupId>
    <artifactId>NumberConvert</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Features
**Language and Splitting Configuration:**
- It provides the ability to specify a splitting string (e.g., a space or a hyphen) that can be used to separate different parts of the textual representation.
- The class supports the Hungarian language ("hu").

**Conversion Process:**
- The core functionality is centered around converting numeric values into their textual representations in Hungarian.
- It handles both positive and negative numbers.

**Processing of Digits:**
- The class extracts individual digits of the number and stores them in arrays.
- The numeric values of digits are used to determine the appropriate textual representations for each part of the number.

**Textual Representation Construction:**
- The class constructs the Hungarian text representation of numbers using a combination of predefined arrays to form the words for digits, tens, hundreds, and other numerical values.
- It applies appropriate rules for combining different parts of the number's representation, including the addition of hyphens and other linguistic rules unique to the Hungarian language.

**Error Handling:**
- It performs basic error handling by throwing an IllegalArgumentException if an unsupported language is specified for conversion.

### Authors
Magyar Zoltán

### Contact
magyarz95@gmail.com

[license]: https://www.apache.org/licenses/LICENSE-2.0