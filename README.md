# 📚 IIUC Study Repository

A comprehensive collection of **study materials** for students at **International Islamic University Chittagong (IIUC)**. This repository serves as a central hub where students can access:

- 📖 **Lecture Sheets**  
- 📝 **Notes**  
- ❓ **Question Papers**  
- 🎓 **Exam Materials**  

Materials are organized by **department** and **semester**, making it easy to find relevant resources.

---

## 🔗 Repository Highlights

- **Well-Organized Structure:**  
  Study materials are categorized by semester and department for easy navigation.
  
- **Wide Range of Resources:**  
  Access lecture sheets, solved questions, and notes for various subjects.
  
- **Collaborative Contribution:**  
  Contributions are welcome! Feel free to add more resources to help your peers.

---

## 📁 Repository Structure


This is a Kotlin Multiplatform project targeting Android, iOS, Web, and Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

> I used Backend as Google Sheets 
> https://docs.google.com/spreadsheets/d/1ywZd_mWRZ6N4K2E6hg5B8ZbYunEj5OxVtxn2Jpt-NYY/edit?usp=sharing

## Design
Followed by the Material3 UI design pattern
#### IIUC Study App Android Screenshots

<table>
  <tr>
    <td>Home Page</td>
     <td>Semester</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/f462260b-739a-49a9-8773-264a9bb6122d" width=270 height=600></td>
    <td><img src="https://github.com/user-attachments/assets/7051de40-dfcb-40c9-a110-72f6498cb3eb" width=270 height=600></td>
  </tr>

  
  <tr>
    <td>Coureses</td>
     <td>Course Details</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/c28910b2-2a6f-4177-a0d4-2fbbf72d5423" width=270 height=600></td>
    <td><img src="https://github.com/user-attachments/assets/85a61a8d-489d-4dd6-a362-60e3eab7ec40" width=270 height=600></td>
  </tr>
 </table>


#### IIUC Study App Windows Screenshots

<table>
  <tr>
    <td>Home Page</td>
     <td>Semester</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/117bc31a-4a34-4bae-8c9f-77e60953880a" width=500 height=270></td>
    <td><img src="https://github.com/user-attachments/assets/af1fd595-830a-4792-a3ca-88dd19995ae8" width=500 height=270></td>
  </tr>

  
  <tr>
    <td>Coureses</td>
     <td>Course Details</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/5f69e9d5-0d82-4ac9-9dd1-5a3c5060e8bd" width=500 height=270></td>
    <td><img src="https://github.com/user-attachments/assets/c31e2529-c720-4f99-bfa0-1965ec79c68b" width=500 height=270></td>
  </tr>

  
  <tr>
    <td>Loading Screen</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/994e8d6f-a652-434d-a95e-19f32c48df90" width=500 height=270></td>
  </tr>
 </table>

