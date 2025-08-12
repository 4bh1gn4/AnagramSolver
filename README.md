# 🔠 Anagram Solver

JavaFX app that finds anagrams from local word lists via file reading. Features a user-friendly UI, efficient generation logic, real-time error handling, and JDK 21 compatibility. Demonstrates Java development, file handling, and API integration skills.

---

## 🚀 Features

- ⌨️ **Instant search**: type a word and see matching anagrams
- 📁 **File-based dictionary** loading (fast local lookup)
- 🧼 **Input normalization** (case-insensitive; trims spaces)
- ⚠️ **Real-time error handling** (missing file, bad input)
- 🎵 **Background music** and customizable themes
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

#### ☕ Install JDK 21

```bash
# Verify Java is installed and version is 21.x
java -version
```

#### 📦 Install JavaFX SDK

```bash
# Set the JavaFX SDK path (update this to your install location)
# Example:
# macOS/Linux:
export PATH_TO_FX="/path/to/javafx-sdk/lib"
# Windows (PowerShell):
# $env:PATH_TO_FX="C:\path\to\javafx-sdk\lib"
```

#### 🧰 (Optional) Install Eclipse

```text
Install "Eclipse IDE for Java Developers" and import this project.
```

---

### ▶️ 3. Run (Command Line)

If you run without a build tool, provide the JavaFX module path:

```bash
# Compile
javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -d out $(find src -name "*.java")

# Run
java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -cp out application.Main
```

---

### 🧩 4. Run (Eclipse)

1. **File → Import → Existing Projects into Workspace**  
2. Select the `AnagramSolver/` folder.  
3. Add JavaFX to your **Run Configuration**:  

   **VM arguments:**
   ```bash
   --module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml
   ```
4. **Run** the `application.Main` class.

---

## 📁 Repository Structure

```
AnagramSolver/
├── src/
│   └── application/
│       ├── Main.java
│       ├── AnagramUtils.java
│       ├── styles.css
│       ├── settings.png
│       ├── background music.mp3
│       ├── three-letters.txt
│       ├── four-letters.txt
│       ├── five-letters.txt
│       └── six-letters.txt
└── README.md
```

---

## 🧠 Key Files

| File                      | Purpose                                                            |
|---------------------------|--------------------------------------------------------------------|
| `Main.java`               | JavaFX application entry point; manages scenes, game flow, and UI  |
| `AnagramUtils.java`       | Core game logic: word loading, jumbling, and correctness checking  |
| `three-letters.txt`       | Word list for Level 1 (3-letter words)                             |
| `four-letters.txt`        | Word list for Level 2 (4-letter words)                             |
| `five-letters.txt`        | Word list for Level 3 (5-letter words)                             |
| `six-letters.txt`         | Word list for Level 4 (6-letter words)                             |
| `styles.css`              | JavaFX stylesheet for UI appearance                                |
| `settings.png`            | Icon for the settings button                                       |
| `background music.mp3`    | Background music for the game                                      |

---

## 🔐 Configuration

- **Word list files**: `three-letters.txt`, `four-letters.txt`, `five-letters.txt`, `six-letters.txt`  
- **Encoding**: UTF-8 recommended for dictionary files

---

## 🌩️ Future Improvements

- 📦 Configurable dictionary path (file chooser, recent files)
- 🔎 Live filtering and performance metrics on large lists
- 🧪 Unit tests for the anagram engine
- 🎨 CSS polish + dark mode

---

## 🙌 Credits

Created by [@4bh1gn4](https://github.com/4bh1gn4)

---

## 📜 License

MIT License
