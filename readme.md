# **ShareIt**

## 📌 **Project Description**
**ShareIt** is a simple and efficient file-sharing platform that allows users to upload files and generate shareable download links for seamless sharing. Built using **Spring Boot**, **Thymeleaf**, and **MySQL**, it provides a user-friendly interface to manage and share files securely.

**Project Demo and ScreenShots:** [ShareIt](https://drive.google.com/drive/folders/11BNfYOKomBhde1yZ_bQ0aHfdagJbQyMA?usp=sharing)

## 🛠 **Tech Stack**
- **Backend:** Java (**Spring Boot**)
- **Frontend:** **HTML, CSS, Thymeleaf**
- **Database:** **MySQL**
- **Additional Tools/Libraries:** **Lombok, Spring Security**

## 🚀 **Features**
- 📂 **File Upload** - Users can upload files easily.
- 🔗 **Link Sharing** - Generates a shareable link for each file.
- 🔐 **User Authentication** Google and Github Oauth authentication
- 🗑 **File Deletion** - Remove uploaded files when no longer needed.
- 📄 **List Files** - Displays all uploaded files for easy management.
- 🗑 **Automatic File Deletion after 24 hrs** - Deletes expired files after 24 hours.

## 📥 **Installation Guide**

### **Prerequisites**
Ensure you have the following installed before setting up the project:
- **Java 17**
- **Maven**
- **MySQL Database**

### **Steps to Run Locally**
1. **Clone the Repository**
   ```sh
   git clone https://github.com/pandey-prakhar/ShareIt.git
   cd ShareIt
   ```
2. **Configure Database**
    - Update `application.properties` with your MySQL credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/shareit_db
      spring.datasource.username=root
      spring.datasource.password=yourpassword
      ```
3. **Build and Run the Project**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access the Application**
    - Open your browser and go to: `http://localhost:8080/files/login`

## 📡 **API Endpoints**

| **Method** | **Endpoint** | **Description** |
|------------|-------------|-----------------|
| **GET** | `/` | Display list of uploaded files |
| **POST** | `/upload` | Upload a file |
| **GET** | `/share/{id}` | Generate a shareable link for a file |
| **POST** | `/delete/{id}` | Delete a file |
| **GET** | `/login` | Redirect to login page |
| **GET** | `/list` | Show list of files |
| **GET** | `/share` | Show share file page |




## 📞 **Contact**
- **Author:** **Prakhar Pandey**
- **GitHub:** [pandey-prakhar](https://github.com/pandey-prakhar)
- **Repository:** [ShareIt](https://github.com/pandey-prakhar/ShareIt)


