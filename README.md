# AnagramSolver
Technologies: Java, JavaFX, CSS, JSON, GitHub, Eclipse
Description: Developed a JavaFX application that allows users to find anagrams for a given word by fetching word lists through file reading. The user-friendly interface enables easy input and dynamic result display. Key features include efficient anagram generation logic, real-time error handling, and compatibility with JDK 21, demonstrating skills in Java development, file handling, and API integration.

# 🔠 Anagram Solver

A desktop app that finds **all anagrams** of a given word using a local word list. Built with **Java + JavaFX**, it features a simple UI for input and dynamically displays results as you type. Compatible with **JDK 21** and developed in **Eclipse**.

---

## 🚀 Features

- ⌨️ **Instant search**: type a word and see matching anagrams
- 📁 **File-based dictionary** loading (fast local lookup)
- 🧼 **Input normalization** (case-insensitive; trims spaces)
- ⚠️ **Real-time error handling** (missing file, bad input)
- ⚡ **Efficient generation** (character-frequency logic)

---

## 🧱 Tech Stack

| Layer     | Technology              |
|-----------|-------------------------|
| Language  | Java (JDK 21)           |
| UI        | JavaFX (controls/FXML)  |
| Tools     | Eclipse, GitHub         |

---

## 🛠️ Setup Instructions (Local Hosting)

### 📦 1. Clone the Repository

```bash
git clone https://github.com/4bh1gn4/AnagramSolver.git
cd AnagramSolver
```

---

### 🧪 2. Install Requirements

- JDK 21 (required)
- JavaFX SDK (matching your JDK version)
- Optional: Eclipse (project was built in Eclipse)

---

### ▶️ 3. Run (Command Line)
If you run without a build tool, provide the JavaFX module path:

```bash
# Example paths — update to your JavaFX SDK location
javac --module-path "/path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml -d out $(find AnagramSolver -name "*.java")
java --module-path "/path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml -cp out your.package.Main
```
Replace your.package.Main with the actual main class.

---

### 🧩 4. Run (Eclipse)
File → Import → Existing Projects into Workspace

Select the AnagramSolver/ folder.

Add JavaFX to your Run Configuration:

VM arguments:

```bash
--module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml
```
Run the main class (likely Main / App depending on your package).

---

## 📁 Repository Structure

```
AnagramSolver/
├── AnagramSolver/                 # Eclipse project
│   ├── src/                       # Java source (JavaFX app)
│   ├── resources/                 # (Optional) word list(s), CSS
│   └── ...                        # Eclipse project files (.project, .classpath)
└── README.md
```

## 🧠 Key Files

| File                     | Purpose                                                           |
|--------------------------|-------------------------------------------------------------------|
| `Main.java`               | JavaFX application entry point; manages scenes, game flow, and UI|
| `AnagramUtils.java`       | Core game logic: word loading, jumbling, checking correctness    |
| `three-letters.txt`       | Word list for Level 1 (3-letter words)                           |
| `four-letters.txt`        | Word list for Level 2 (4-letter words)                           |
| `five-letters.txt`        | Word list for Level 3 (5-letter words)                           |
| `six-letters.txt`         | Word list for Level 4 (6-letter words)                           |
| `styles.css`              | JavaFX stylesheet for customizing UI appearance                 |
| `settings.png`            | Icon for the settings button                                     |
| `background music.mp3`    | Background music for the game                                    |

---

### 🔐 Configuration
Word list file: app reads a local dictionary via file I/O.
Common locations: resources/words.txt or a path set in code.

Encoding: UTF-8 recommended for dictionary files.

---

### 🌩️ Future Improvements
📦 Configurable dictionary path (file chooser, recent files)

🔎 Live filtering and performance metrics on large lists

🧪 Unit tests for the anagram engine

🎨 CSS polish + dark mode

---

## 🙌 Credits
Created by @4bh1gn4

## 📜 License
MIT License
