# 🔐 EncryptWebsite

**EncryptWebsite** is a Java Swing-based GUI tool that allows users to securely **encrypt** and **decrypt** text using a **randomized character substitution cipher**. This application is ideal for experimenting with basic cryptography techniques and providing a hands-on learning experience.

---

## ✨ Features

- 🔒 Encrypt plaintext using a substitution cipher
- 🔓 Decrypt ciphertext using the same key
- 🔁 Reset the encryption key (generates a new random key)
- 💾 Save and Load keys for future use
- 📁 Load input text from a file
- 💽 Save encrypted/decrypted text to a file
- 🧹 Clear input and output text areas
- 📊 Character count display
- 🧭 Simple navigation bar with About and Contact

---

## 🛠️ How It Works

This tool uses a **character substitution cipher**:
- It shuffles a predefined list of characters.
- Each character in the plaintext is replaced with the corresponding character in the shuffled list.
- The same key must be used for decryption.

> ⚠️ **Resetting the key** will generate a new key, making previously encrypted messages undecryptable unless the original key was saved.

---

## 🧪 Tech Stack

- Java (JDK 8+)
- Swing for GUI
- File IO for key storage

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java JDK 8 or later
- Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools

### 🔧 Running the App

1. Clone the repository:

```bash
git clone https://github.com/your-username/EncryptWebsite.git
