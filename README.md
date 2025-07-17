# 🎵 Music Catalog – Java Swing + MySQL

A desktop application developed in Java to manage **Artists**, **Songs**, and **Playlists**, featuring a graphical interface built with **Swing** and data persistence in **MySQL** via **JDBC**.

## 🖼️ Overview

Built as an academic project for the Programming I course (Senac RJ), this system demonstrates:

- Object‑Oriented Programming (OOP)
- Swing GUI development
- Database access via JDBC
- Modular code organization

---

## 🚀 Features

- ✅ Full CRUD (Create, Read, Update, Delete) for Artists, Songs & Playlists  
- ✅ Interactive tables (`JTable`) supporting double-click editing  
- ✅ Database connectivity using JDBC with a Singleton pattern  
- ✅ Automatic UI refresh after data changes  
- ✅ Input validation and confirmation dialogs for critical actions  
- ✅ Multiple panels managed via `CardLayout` / `JTabbedPane`

---

## 📚 Technologies Used

- Java 21 
- Swing (Java GUI Toolkit)  
- JDBC (Java Database Connectivity)  
- MySQL 8+  
- IDE: Eclipse 



🧠 Technical Learning Outcomes
Implemented a JDBC connection singleton for efficient database reuse

Enforced implicit MVC architecture through modular code separation

Managed Swing component state (enabling/disabling controls per context)

Dynamically handled data with JTable & DefaultTableModel

Robust SQL exception handling with user-facing error messages

📈 Future Improvements
Adopt explicit DAO (Data Access Object) layer for better abstraction

Formalize full MVC or MVVM architecture

Add search and filter capabilities (by name, genre, date)

Export data to CSV or PDF formats

Implement user authentication and role-based access

👨‍🏫 Credits
Academic project developed for Programming I at Senac RJ
Instructor: Reinaldo Freitas
