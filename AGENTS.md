# AGENTS.md - Agent Coding Guidelines

This file provides context for AI agents working in this repository.

## Project Overview

- **Project**: quick-chinese-transfer
- **Type**: Java Maven multi-module library
- **Purpose**: Chinese Simplified/Traditional character conversion and Chinese character stroke rendering
- **Java Version**: 1.8
- **License**: Apache License 2.0

## Modules

| Module | Description |
|--------|-------------|
| `transfer-core` | Chinese Simplified/Traditional/Hong Kong/Taiwan conversion library |
| `hanzi-writer` | Chinese character stroke rendering and SVG generation |

## Build Commands

### Full Build
```bash
mvn clean install
```

### Run All Tests
```bash
mvn test
```

### Run Tests with Coverage (Travis CI)
```bash
mvn cobertura:cobertura
```

### Run Single Test Class
```bash
# In transfer-core module
cd transfer-core && mvn test -Dtest=Issue31Fix

# In hanzi-writer module
cd hanzi-writer && mvn test -Dtest=HanZiWriterTest
```

### Run Single Test Method
```bash
mvn test -Dtest=Issue31Fix#fix31
```

### Package Only (Skip Tests)
```bash
mvn package -DskipTests
```

### Build Specific Module
```bash
mvn install -pl transfer-core
mvn install -pl hanzi-writer
```

### Run From Root
All Maven commands can be run from the root directory to build all modules.

## Code Style Guidelines

### General
- **Language**: Java 8 (target 1.8)
- **Encoding**: UTF-8 for all source files
- **Line endings**: Follows system default (LF on Linux/Mac, CRLF on Windows)

### Naming Conventions
- **Classes**: PascalCase (e.g., `ChineseUtils`, `TrieNode`)
- **Methods**: camelCase (e.g., `s2t()`, `preLoad()`)
- **Variables**: camelCase (e.g., `content`, `text`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `TransType.SIMPLE_TO_TRADITIONAL`)
- **Packages**: lowercase (e.g., `com.github.liuyueyi.quick.transfer`)

### JavaDoc
- Use Chinese comments for public API documentation (consistent with existing codebase)
- Include `@param`, `@return`, `@author`, `@date` tags
- Example:
  ```java
  /**
   * 简体转繁体
   *
   * @param content 输入的中文内容
   * @return 转换后的繁体中文
   */
  public static String s2t(String content) { ... }
  ```

### Code Structure
- **Imports**: Standard Java import statements, organized alphabetically
- **Class structure**: Fields → Constructors → Public Methods → Private Methods
- **Braces**: K&R style (same line opening brace)

### Error Handling
- Use `try-catch` for operations that may throw checked exceptions
- Return empty strings or null appropriately (check existing patterns in ChineseUtils)
- Do not suppress exceptions silently

### Testing
- Test classes placed in `src/test/java` mirroring main package structure
- Issue-specific tests named `Issue{Number}Fix.java` (e.g., `Issue31Fix.java`)
- Feature tests in `test/feat/` directory
- Tests can be plain main() methods or JUnit-style

### Common Test Patterns
```java
// Simple test with main
public class Issue31Fix {
    public static void main(String[] args) {
        String text = "test string";
        System.out.println("result:" + ChineseUtils.s2t(text));
    }
}
```

## Project Structure

```
quick-chinese-transfer/
├── pom.xml                    # Parent POM
├── .travis.yml                # Travis CI configuration
├── transfer-core/             # Chinese conversion module
│   ├── pom.xml
│   └── src/
│       ├── main/java/         # Source code
│       └── test/java/         # Test code
├── hanzi-writer/              # Character stroke rendering module
│   ├── pom.xml
│   └── src/
│       ├── main/java/
│       └── test/java/
└── .github/                   # GitHub configuration
```

## Key Classes

### transfer-core
- `ChineseUtils` - Main entry point for conversion operations
- `TransType` - Enum for conversion types (SIMPLE_TO_TRADITIONAL, etc.)
- `DictionaryContainer` - Dictionary management singleton
- `Trie<T>` - Trie data structure for efficient text matching

### hanzi-writer
- `HanZiSvgGenerator` - SVG generation builder
- `HanZiRenderResultVo` - Result object containing SVG and stroke data
- `RenderStyleEnum` - Rendering style options (NORMAL, STROKE_ANIMATE, TOTAL)

## Development Notes

1. **Dictionary files**: Located in resources under `tc/` directory
2. **Factory patterns**: Use static factory methods (e.g., `HanZiSvgGenerator.newGenerator()`)
3. **Singleton**: DictionaryContainer uses singleton pattern
4. **Thread safety**: `preLoad()` supports async loading with daemon threads

## CI/CD

- **Travis CI**: Runs `mvn cobertura:cobertura` on every push
- **Codecov**: Coverage reports uploaded automatically
- **Maven Central**: Published via Sonatype OSSRH

## Pre-commit Checklist

- [ ] Code compiles: `mvn compile`
- [ ] Tests pass: `mvn test`
- [ ] No new warnings introduced
- [ ] JavaDoc updated for public API changes
